package fr.istic.hbmlh.videogen;

import fr.istic.hbmlh.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.hbmlh.videogen.factory.impl.RandomVideoGenGeneratorFactoryImpl;
import fr.istic.hbmlh.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.hbmlh.videogen.generated.VideoGenHelper;
import fr.istic.hbmlh.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class FFMpegIntegrationTest {

  private RandomVideoGenGeneratorFactory randomVideoGenGeneratorFactory;

  @Before
  public void setUp() throws IOException {
    randomVideoGenGeneratorFactory = new RandomVideoGenGeneratorFactoryImpl(new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl()));
    new ProcessBuilder("rm", "-rf", "target/test-classes/videogen/result.avi").start();
  }

  @Test
  public void shouldDoTheCafe() throws IOException, ExecutionException, InterruptedException {

    String videoGenFilePath = "target/test-classes/videogen/spec.videogen";
    RandomVideoGenGenerator randomVideoGenGenerator = randomVideoGenGeneratorFactory.create(videoGenFilePath);

    List<String> files = randomVideoGenGenerator.generateRandomConfiguration();

    String parent = new File(videoGenFilePath).getParentFile().getAbsolutePath();
    files = files.stream().map(f -> String.format("%s/%s", parent, f)).collect(Collectors.toList());
    files.forEach(f -> assertThat(String.format("%s file doesn't exists", f), new File(f).exists(), equalTo(true)));

    new ProcessBuilder(
      "/usr/bin/ffmpeg",
      "-i",
      String.format("concat:%s", String.join("|", files)),
      "-codec",
      "copy",
      "-y",
      "result.avi"
    ).directory(new File(parent)).start().waitFor();

    assertThat(new File("target/test-classes/videogen/result.avi").exists(), equalTo(true));
  }
}

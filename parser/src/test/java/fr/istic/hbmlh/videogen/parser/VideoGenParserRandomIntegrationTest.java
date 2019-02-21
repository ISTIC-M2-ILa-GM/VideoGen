package fr.istic.hbmlh.videogen.parser;

import fr.istic.hbmlh.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.hbmlh.videogen.factory.VideoGenParserFactory;
import fr.istic.hbmlh.videogen.factory.impl.RandomVideoGenGeneratorFactoryImpl;
import fr.istic.hbmlh.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.hbmlh.videogen.generated.VideoGenHelper;
import fr.istic.hbmlh.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.hbmlh.videogen.model.ParsedMedia;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import lombok.Builder;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static fr.istic.hbmlh.videogen.utils.FileUtils.findFilesFromFolder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class VideoGenParserRandomIntegrationTest {

  private static final String DIRECTORY = "target/test-classes";

  private VideoGenParserFactory videoGenParserFactory;

  @Before
  public void setUp() {
    videoGenParserFactory = new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl());
  }

  @Test
  public void should_test_all_videogen() {

    File file = new File(DIRECTORY);

    assertThat(file.isDirectory(), equalTo(true));

    VideoGenParserFactoryImpl videoGenParserFactory = new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl());
    findFilesFromFolder(file).stream().filter(f -> f.getName().endsWith(".videogen")).forEach(f -> {

      VideoGenParser videoGenParser = videoGenParserFactory.create(f.getPath());
      List<List<ParsedMedia>> parse = videoGenParser.parse();

      assertThat(parse.size(), equalTo(calculate(f)));
    });
  }

  private int calculate(File f) {
    try {
      List<String> strings = Files.readAllLines(f.toPath());
      boolean isAlt = false;
      int i = 0;
      int totalAlternatives = 0;
      int mandatories = 0;
      int optionals = 0;
      for (String s: strings) {
        if (s.contains("mandatory")) {
          mandatories = 1;
        }
        if (s.contains("optional")) {
          optionals++;
        }
        if (s.contains("alternatives")) {
          isAlt = true;
          i = 0;
          continue;
        }
        if (isAlt) {
          if (s.contains("}")) {
            isAlt = false;
            if (totalAlternatives == 0) {
              totalAlternatives = 1;
            }
            totalAlternatives *= i;
            i = 0;
            continue;
          }
          i++;
        }
      }
      int total = 0;
      total += mandatories;
      if (total == 0 && (totalAlternatives != 0 || optionals != 0)) {
        total = 1;
      }
      if (totalAlternatives != 0) {
        total *= totalAlternatives;
      }
      if (optionals != 0) {
        total *= Math.pow(2, optionals);
      }
      return total;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

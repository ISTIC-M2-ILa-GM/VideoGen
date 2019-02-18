package fr.istic.hbmlh.videogen.config;

import fr.istic.hbmlh.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.hbmlh.videogen.factory.impl.RandomVideoGenGeneratorFactoryImpl;
import fr.istic.hbmlh.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.hbmlh.videogen.generated.VideoGenHelper;
import fr.istic.hbmlh.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class Configurator {

  @Bean
  public RandomVideoGenGenerator randomVideoGenGenerator() throws IOException {
    final RandomVideoGenGeneratorFactory randomVideoGenGeneratorFactory = new RandomVideoGenGeneratorFactoryImpl(new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl()));

    final File videoGenFile = new File("./back/videogen_dir/spec.videogen");
    if (!videoGenFile.exists()) {
      throw new RuntimeException("Aucun videogen file dans ./videogen_dir/spec.videogen");
    }

    final List<String> lines = Files.readAllLines(videoGenFile.toPath());

    final String videoGen = lines.stream().reduce((a, b) -> a + "\n" + b)
      .orElseThrow(() -> new RuntimeException("Impossible de reduce les lignes"));

    return randomVideoGenGeneratorFactory.create(videoGen);

  }
}

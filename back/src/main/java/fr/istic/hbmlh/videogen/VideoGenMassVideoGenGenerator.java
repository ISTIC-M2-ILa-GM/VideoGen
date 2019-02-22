package fr.istic.hbmlh.videogen;

import fr.istic.hbmlh.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.hbmlh.videogen.factory.impl.RandomVideoGenGeneratorFactoryImpl;
import fr.istic.hbmlh.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.hbmlh.videogen.generated.VideoGenHelper;
import fr.istic.hbmlh.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import fr.istic.hbmlh.videogen.service.IVideoService;
import fr.istic.hbmlh.videogen.service.impl.VideoService;
import fr.istic.hbmlh.videogen.util.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static fr.istic.hbmlh.videogen.service.IVideoService.VIDEO_CONCAT_PATH;
import static fr.istic.hbmlh.videogen.service.IVideoService.VIDEO_FORMAT;

@Slf4j
public class VideoGenMassVideoGenGenerator {

  private static final String DIRECTORY = "videogen_directory/";
  private static final String VIDEOGEN_EXT = ".videogen";

  private RandomVideoGenGeneratorFactory randomVideoGenGeneratorFactory;
  private IVideoService videoService;

  private String directory;

  public VideoGenMassVideoGenGenerator(String directory) {
    log.info("VideoGenMassVideoGenGenerator: préparation de la generation de {}", directory);
    this.directory = directory;
    this.videoService = new VideoService();
    this.randomVideoGenGeneratorFactory = new RandomVideoGenGeneratorFactoryImpl(new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl()));
    log.info("VideoGenMassVideoGenGenerator: generation initialisé");
  }

  public static void main(String[] args) {
    new VideoGenMassVideoGenGenerator(DIRECTORY).start();
  }

  private void start() {
    int i = 0;
    int j = 0;
    for (File f : FileUtils.findFilesFromFolder(new File(directory))) {
      if (f.getName().endsWith(VIDEOGEN_EXT)) {
        try {
          log.info("VideoGenMassVideoGenGenerator: début de la génération de {}", f.getAbsolutePath());
          RandomVideoGenGenerator randomVideoGenGenerator = randomVideoGenGeneratorFactory.create(f.getPath());
          List<String> files = randomVideoGenGenerator.generateRandomConfiguration().stream().map(v -> String.format("%s/%s", f.getParentFile().getAbsolutePath(), v)).collect(Collectors.toList());
          String uuidFile = videoService.concatVideos(files);
          log.info("VideoGenMassVideoGenGenerator: video généré {}{}{}", VIDEO_CONCAT_PATH, uuidFile, VIDEO_FORMAT);
          i++;
        } catch (Exception e) {
          log.error("VideoGenMassVideoGenGenerator: une erreur est survenue", e);
          j++;
        }
      }
    }
    log.info("VideoGenMassVideoGenGenerator: vidéo générées : {}, vidéo en erreur: {}", i, j);
  }
}

package fr.istic.hbmlh.videogen.controller;

import fr.istic.hbmlh.videogen.dto.ValueWrapper;
import fr.istic.hbmlh.videogen.dto.Video;
import fr.istic.hbmlh.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.hbmlh.videogen.factory.impl.RandomVideoGenGeneratorFactoryImpl;
import fr.istic.hbmlh.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.hbmlh.videogen.generated.VideoGenHelper;
import fr.istic.hbmlh.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import fr.istic.hbmlh.videogen.service.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.istic.hbmlh.videogen.dto.Dto_videoKt.toConfigurator;

@RestController
public class VideoController {

  private static final Logger LOG = LoggerFactory.getLogger(VideoController.class);

  private final IVideoService videoService;

  private RandomVideoGenGenerator randomVideoGenGenerator;
  private String videogenDirAbsolutePath = null;
  private List<List<Video>> configurator = null;

  public VideoController(final IVideoService videoService) {
    this.videoService = videoService;
  }

  /**
   * Permet de configurer le path du videogen
   */
  @PostMapping("config")
  public void configureVideoGen(@RequestBody ValueWrapper<String> pathWrapper) {
    final String absolutePathVideogen = pathWrapper.getValue();

    final File videoGenFile = new File(absolutePathVideogen);
    if (!videoGenFile.exists()) {
      throw new RuntimeException("Aucun videogen file dans " + absolutePathVideogen);
    }
    LOG.info("configuration du serveur avec le path : {}", absolutePathVideogen);


    final VideoGenParserFactoryImpl videoGenParserFactory = new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl());

    final RandomVideoGenGeneratorFactory randomVideoGenGeneratorFactory = new RandomVideoGenGeneratorFactoryImpl(
      videoGenParserFactory
    );

    this.configurator = toConfigurator(videoGenParserFactory.create(absolutePathVideogen).parse());


    this.videogenDirAbsolutePath = videoGenFile.getParentFile().getAbsolutePath();

    randomVideoGenGenerator = randomVideoGenGeneratorFactory.create(videoGenFile.getAbsolutePath());
  }

  /**
   * Récupère la configuration dispo
   */
  @GetMapping("configurator")
  public List<List<Video>> getConfigurator() {
    return this.configurator;
  }

  /**
   * Génère une vidéo aléatoire et retourne son nom unique
   */
  @GetMapping("random")
  public ValueWrapper<String> generateRandomVideo() {
    LOG.info("Demande de génération d'une variante");

    // génère une liste de vidéos aléatoire
    final List<String> videosPath = this.randomVideoGenGenerator.generateRandomConfiguration().stream()
      .map(it -> this.videogenDirAbsolutePath + "/" + it)
      .collect(Collectors.toList());

    // on les concats les vidéos pour obtenir le nom de la variante
    final String videoName = this.videoService.concatVideos(videosPath);

    // on génère le gif de la variante
    this.videoService.generateGif(videoName);

    LOG.info("Le nom de la variante est {}", videoName);

    // on retourne le nom de la variante
    return new ValueWrapper<>(videoName);
  }

  @GetMapping("video/{videoName}")
  public ResponseEntity<UrlResource> show(@PathVariable String videoName) throws MalformedURLException {
    LOG.info("Récupération de la vidéo {}", videoName);
    final File file = new File(IVideoService.VIDEO_CONCAT_PATH + videoName + IVideoService.VIDEO_FORMAT);

    final UrlResource resource = new UrlResource(file.toURI());

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
      .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
      .body(resource);
  }

  @GetMapping("gif/{gifName}")
  public ResponseEntity<UrlResource> showGif(@PathVariable String gifName) throws MalformedURLException {
    LOG.info("Récupération de la gif {}", gifName);
    final File file = new File(IVideoService.GIF_CONCAT_PATH + gifName + IVideoService.GIF_FORMAT);

    final UrlResource resource = new UrlResource(file.toURI());

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
      .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.IMAGE_GIF))
      .body(resource);
  }

}

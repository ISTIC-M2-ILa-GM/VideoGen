package fr.istic.hbmlh.videogen.controller;

import fr.istic.hbmlh.videogen.dto.ValueWrapper;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import fr.istic.hbmlh.videogen.service.IVideoService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class VideoController {


  private final IVideoService videoService;

  private final RandomVideoGenGenerator randomVideoGenGenerator;

  public VideoController(final IVideoService videoService,
                         final RandomVideoGenGenerator randomVideoGenGenerator) {
    this.videoService = videoService;
    this.randomVideoGenGenerator = randomVideoGenGenerator;
  }

  @GetMapping
  public ValueWrapper<String> generateRandomVideo() {
    // génère une liste de vidéos aléatoire
    final List<String> videosPath = this.randomVideoGenGenerator.generateRandomConfiguration();

    // on les concats les vidéos pour obtenir le nom de la variante
    final String videoName = this.videoService.concatVideos(videosPath);

    // on génère le gif de la variante
    this.videoService.generateGif(videoName);

    // on retourne le nom de la variante
    return new ValueWrapper<>(videoName);
  }

  @GetMapping("video/{videoName}")
  public ResponseEntity<UrlResource> show(@PathVariable String videoName) throws MalformedURLException {
    final File file = new File(IVideoService.VIDEO_CONCAT_PATH + videoName + IVideoService.VIDEO_FORMAT);

    final UrlResource resource = new UrlResource(file.toURI());

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
      .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
      .body(resource);
  }

  @GetMapping("gif/{gifName}")
  public ResponseEntity<UrlResource> showGif(@PathVariable String gifName) throws MalformedURLException {
    final File file = new File(IVideoService.GIF_CONCAT_PATH + gifName + IVideoService.GIF_FORMAT);

    final UrlResource resource = new UrlResource(file.toURI());

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
      .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.IMAGE_GIF))
      .body(resource);
  }


}

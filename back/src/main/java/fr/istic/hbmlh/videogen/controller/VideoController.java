package fr.istic.hbmlh.videogen.controller;

import fr.istic.hbmlh.videogen.dto.ValueWrapper;
import fr.istic.hbmlh.videogen.service.IVideoService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
public class VideoController {


  private final IVideoService videoService;

  public VideoController(IVideoService videoService) {
    this.videoService = videoService;
  }

  @PostMapping("generate")
  public ValueWrapper<String> generateVideo(
//    @RequestBody(required = false) ValueWrapper<List<String>> wrapper
  ) {
    // TODO ici il faut générer la video à partir du fichier videogen passé en paramètre

//    final String videoName = this.videoService.concatVideos(wrapper.getValue());

    return new ValueWrapper<>("nufnuf.webm");
  }

  @GetMapping("video/{videoName}")
  public ResponseEntity<UrlResource> show(@PathVariable String videoName) throws MalformedURLException {
    final UrlResource resource = new UrlResource("file:" + IVideoService.VIDEO_CONCAT_PATH + videoName);

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
      .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
      .body(resource);
  }

  @GetMapping("configuration")
  public ValueWrapper<String> getConfig() {
    // TODO envoyer un vrai fichier videogen
    return new ValueWrapper<>("");
  }


}

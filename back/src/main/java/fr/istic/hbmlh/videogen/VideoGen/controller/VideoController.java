package fr.istic.hbmlh.videogen.VideoGen.controller;

import fr.istic.hbmlh.videogen.VideoGen.dto.ValueWrapper;
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
import java.util.List;

@RestController("video/")
public class VideoController {

  @PostMapping
  public ValueWrapper<String> generateVideo(ValueWrapper<List<String>> videos) {
    // TODO ici il faut générer la video à partir du fichier videogen passé en paramètre

    return new ValueWrapper<>("nufnuf.webm");
  }

  @GetMapping("{videoName}")
  public ResponseEntity<UrlResource> show(@PathVariable String videoName) throws MalformedURLException {
    final UrlResource resource = new UrlResource("file:" + videoName);

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

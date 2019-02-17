package fr.istic.hbmlh.videogen.service.impl

import fr.istic.hbmlh.videogen.service.IVideoGenParser
import org.springframework.stereotype.Service

@Service
class VideoGenParser : IVideoGenParser {
  override fun generateRandomConfiguration(): List<String> {

    val avez = "/tmp/video_src/avez_vous_deja_vu.avi"
    val intro = "/tmp/video_src/intro_lutin.avi"

    return listOf(avez, intro)
  }
}

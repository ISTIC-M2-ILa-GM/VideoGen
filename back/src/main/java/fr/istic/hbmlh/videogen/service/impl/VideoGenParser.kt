package fr.istic.hbmlh.videogen.service.impl

import fr.istic.hbmlh.videogen.service.IVideoGenParser
import org.springframework.stereotype.Service

@Service
class VideoGenParser : IVideoGenParser {
  override fun generateRandomConfiguration(): List<String> {

    val avez = "/tmp/titi/avez/avez_vous_deja_vu.avi"
    val intro = "/tmp/titi/intro/intro_lutin.avi"
    val final = "/tmp/titi/maintenant_oui/maintenant_oui.avi"

    return listOf(avez, intro, final)
  }
}

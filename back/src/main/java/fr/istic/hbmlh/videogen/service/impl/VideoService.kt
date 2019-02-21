package fr.istic.hbmlh.videogen.service.impl

import fr.istic.hbmlh.videogen.exception.VideoGenException
import fr.istic.hbmlh.videogen.service.IVideoService
import fr.istic.hbmlh.videogen.util.VideoUtils
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import javax.annotation.PostConstruct

@Service
class VideoService : IVideoService {

  /**
   * A chaque démarrage on prépare une dossier de concaténation propre
   * puis génère les fichiers de
   */
  @PostConstruct
  fun postConstruct() {
    val dirConcat = File(IVideoService.VIDEO_CONCAT_PATH)
    if (dirConcat.exists()) {
      dirConcat.deleteRecursively()
    }

    dirConcat.mkdir()
    File(IVideoService.GIF_CONCAT_PATH).mkdir()
  }


  override fun generateGif(videoName: String) {
    VideoUtils.generateGifCommandLine(videoName)
  }

  override fun concatVideos(videosNames: MutableList<String>?): String {
    if (videosNames == null || videosNames.isEmpty()) {
      throw VideoGenException("Pas de videos à concaténer")
    }

    val videosInnexistante = videosNames.filter { !File(it).exists() }
    if (videosInnexistante.isNotEmpty()) {
      throw VideoGenException("Les videos " + videosInnexistante.reduce { a, b -> "$a \n $b" } + " sont introuvables")
    }

    val idVideo = UUID.randomUUID().toString()

    VideoUtils.generateFfmpegConcatCommand(videosNames, idVideo)


    return idVideo
  }
}

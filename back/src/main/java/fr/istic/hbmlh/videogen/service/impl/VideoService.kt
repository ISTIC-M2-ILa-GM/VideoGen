package fr.istic.hbmlh.videogen.service.impl

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
   */
  @PostConstruct
  fun postConstruct() {
    val dirConcat = File(IVideoService.VIDEO_CONCAT_PATH)
    if (dirConcat.exists()) {
      dirConcat.deleteRecursively()
    }

    dirConcat.mkdir()
  }


  override fun generateGif(videoName: String?): String {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun concatVideos(videosNames: MutableList<String>?): String {

    val idVideo = UUID.randomUUID()
    val command = VideoUtils.generateFfmpegConcatCommand(videosNames, idVideo)

    VideoUtils.executeCommand(command)

    return idVideo.toString()
  }
}

package fr.istic.hbmlh.videogen.util;

import fr.istic.hbmlh.videogen.service.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class VideoUtils {

  private static final Logger LOG = LoggerFactory.getLogger(VideoUtils.class);

  private static String getFormatFromFilePath(final String filePath) {

    final int lastIndex = filePath.lastIndexOf(".");

    return lastIndex == -1 ? "" : filePath.substring(lastIndex);
  }


  /**
   * Génère la commande pour concaténer les vidéos
   *
   * @param videosPath le path des vidéos à concat (ne vérifie pas qu'ils existent)
   */
  public static void generateFfmpegConcatCommand(final List<String> videosPath, final String nomVideo) throws IOException, InterruptedException {
    if (videosPath.isEmpty()) {
      throw new RuntimeException("Aucune vidéo à concatener !");
    }
    videosPath.forEach(LOG::debug);

    final String format = VideoUtils.getFormatFromFilePath(videosPath.get(0));

    final File videoConcatDirectory = new File("/tmp/videogen");

    final String concatCmd = String.format("concat:%s", String.join("|", videosPath));

    final ProcessBuilder processBuilder = new ProcessBuilder(
      "/usr/bin/ffmpeg",
      "-i",
      concatCmd,
      "-codec",
      "copy",
      nomVideo + format
    );

    LOG.info("Début de la concaténation de {} vidéos", videosPath.size());
    processBuilder.directory(videoConcatDirectory).start().waitFor();
    LOG.info("Done");

    // puis conversion en webm (meilleur support des navigateurs)
    final ProcessBuilder convertProcess = new ProcessBuilder(
      "/usr/bin/ffmpeg",
      "-i",
      nomVideo + format,
      "-qscale",
      "0",
      nomVideo + IVideoService.VIDEO_FORMAT
    );

    LOG.info("Conversion du format {} en {}", format, IVideoService.VIDEO_FORMAT);
    convertProcess.directory(videoConcatDirectory).start().waitFor();
    LOG.info("Done");

  }


  public static void generateGifCommandLine(final String videoName) throws IOException, InterruptedException {
    LOG.info("Début de la génération du gif");

    final File videoConcatDirectory = new File("/tmp/videogen");

    final String absoluteVideoPath = IVideoService.VIDEO_CONCAT_PATH + videoName + IVideoService.VIDEO_FORMAT;
    final String absoluteGifPath = IVideoService.GIF_CONCAT_PATH + videoName + IVideoService.GIF_FORMAT;

    final ProcessBuilder gifProcess = new ProcessBuilder(
      "/usr/bin/ffmpeg",
      "-t",
      "2",
      "-i",
      absoluteVideoPath,
      absoluteGifPath
    );
    gifProcess.directory(videoConcatDirectory).start().waitFor();
    LOG.info("Done");
  }
}

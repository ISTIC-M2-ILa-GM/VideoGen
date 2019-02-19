package fr.istic.hbmlh.videogen.util;

import fr.istic.hbmlh.videogen.service.IVideoService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class VideoUtils {

  private static String getFormatFromFilePath(final String filePath) {

    final int lastIndex = filePath.lastIndexOf(".");

    return lastIndex == -1 ? "" : filePath.substring(lastIndex);
  }


  /**
   * Génère la commande pour concaténer les vidéos
   *
   * @param videosPath le path des vidéos à concat (ne vérifie pas qu'ils existent)
   */
  public static void generateFfmpegConcatCommand(final List<String> videosPath, final UUID nomVideo) throws IOException, InterruptedException {
    if (videosPath.isEmpty()) {
      throw new RuntimeException("Aucune vidéo à concat !");
    }

    final String format = VideoUtils.getFormatFromFilePath(videosPath.get(0));

    final File videoConcatDirectory = new File("/tmp/videogen");

    final String concatCmd = String.format("concat:%s", String.join("|", videosPath));

    final ProcessBuilder processBuilder = new ProcessBuilder(
      "/usr/bin/ffmpeg",
      "-i",
      concatCmd,
      "-codec",
      "copy",
      "-y",
      nomVideo + format
    );

    processBuilder.directory(videoConcatDirectory).start().waitFor();

    // puis conversion en webm (meilleur support des navigateurs)
    final ProcessBuilder convertProcess = new ProcessBuilder(
      "/usr/bin/ffmpeg",
      "-i",
      nomVideo + format,
      "-qscale",
      "0",
      nomVideo + IVideoService.VIDEO_FORMAT
    );

    convertProcess.directory(videoConcatDirectory).start().waitFor();

  }


  public static String generateGifCommandLine(final String videoPath) {

    return null;
  }
}

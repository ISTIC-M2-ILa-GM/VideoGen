package fr.istic.hbmlh.videogen.util;

import fr.istic.hbmlh.videogen.service.IVideoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

public class VideoUtils {


    /**
     * Génère la commande pour concaténer les vidéos
     *
     * @param videosPath le path des vidéos à concat (ne vérifie pas qu'ils existent)
     * @return La commande à executer
     */
    public static String generateFfmpegConcatCommand(final List<String> videosPath, final UUID nomVideo) {
        final String initialCommand = "ffmpeg -i \"concat:";

        final String videos = videosPath.stream()
                .reduce((a, b) -> a + "|" + b)
                .orElse("");

        final String endCommand = "\" -codec copy " + IVideoService.VIDEO_CONCAT_PATH + nomVideo + ".webm";

        return initialCommand + videos + endCommand;
    }

    /**
     * Exécute une commande
     *
     * @param command
     * @return
     * @throws IOException
     */
    public static String executeCommand(String command) throws IOException {

        final StringBuilder output = new StringBuilder();

        InputStream inputStream = null;

        try {
            final Process p = Runtime.getRuntime().exec(command);
            final int result= p.waitFor();

            inputStream = p.getInputStream();

            final BufferedReader reader =
                    new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return output.toString();

    }

    public static String generateGifCommandLine(final String videoPath) {

        return null;
    }
}

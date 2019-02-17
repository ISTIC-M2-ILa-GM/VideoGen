package fr.istic.hbmlh.videogen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

public class VideoUtils {


    public static String generateFfmpegConcatCommand(final List<String> videosPath, UUID uuid) {

        return null;
    }


    public static String executeCommand(String command) throws IOException {

        final StringBuilder output = new StringBuilder();

        InputStream inputStream = null;

        try {
            final Process p = Runtime.getRuntime().exec(command);
            p.waitFor();

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

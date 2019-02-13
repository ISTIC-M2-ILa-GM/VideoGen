package fr.istic.hbmlh.videogen;

import fr.istic.hbmlh.videogen.util.VideoUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class VideoUtilsTest {

    @Test
    public void shouldGenerateGoodCommandLineForConcat() {
        final String video1 = "aloha.avi";
        final String video2 = "kenavo.avi";
        final UUID randomName = UUID.randomUUID();

        final String cmdLine = VideoUtils.generateFfmpegConcatCommand(
                Lists.newArrayList(video1, video2), randomName
        );

        final String expectedCmdLine = "ffmpeg -i \"concat:" + video1 + "|" + video2 + "\" -codec copy " + randomName + ".avi";

        Assert.assertEquals(expectedCmdLine, cmdLine);

    }

    @Test
    public void shouldGenerateGoodCommandLineForGifCreation() {
        final String videoPath = "video.avi";

        final String expectedCmdLine = "ffmpeg -t 2 -i " + videoPath + " " + videoPath.replace(".avi", ".gif");

        final String cmdLine = VideoUtils.generateGifCommandLine(videoPath);

        Assert.assertEquals(expectedCmdLine, cmdLine);
    }

    @Test
    public void shouldExecuteCommandLine() {
        final String command = "echo Aloha M2 ILa";

        final String output = VideoUtils.executeCommandLine(command);

        Assert.assertEquals(output, command);
    }


}

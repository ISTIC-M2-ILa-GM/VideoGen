package fr.istic.hbmlh.videogen;

import fr.istic.hbmlh.videogen.util.VideoUtils;
import org.junit.Assert;
import org.junit.Test;

public class VideoUtilsTest {


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

//        final String output = VideoUtils.executeCommand(command);
//        Assert.assertEquals(output, command);
  }


}

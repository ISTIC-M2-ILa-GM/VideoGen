package fr.istic.hbmlh.videogen.wrapper.impl;

import fr.istic.hbmlh.videogen.exception.VideoGenException;
import fr.istic.hbmlh.videogen.factory.ProcessBuilderFactory;
import fr.istic.hbmlh.videogen.wrapper.LengthWrapper;
import fr.istic.hbmlh.videogen.wrapper.ProcessBuilderWrapper;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@AllArgsConstructor
public class LengthWrapperImpl implements LengthWrapper {

  private static final String MATCH = "^\\s+Duration: ([0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{2}), start.+$";

  private ProcessBuilderFactory processBuilderFactory;

  @Override
  public long retrieveLength(String filePath) {

    ProcessBuilderWrapper processBuilderWrapper = processBuilderFactory.create("/tmp", "/usr/bin/ffmpeg", "-i", filePath);
    Process start = processBuilderWrapper.start();
    BufferedReader buff = new BufferedReader(new InputStreamReader(start.getInputStream()));
    BufferedReader error = new BufferedReader(new InputStreamReader(start.getErrorStream()));
    try {
      while (start.isAlive()) {
        String line;
        while ((line = buff.readLine()) != null || (line = error.readLine()) != null) {
          if (line.matches(MATCH)) {
            String[] split = line.replaceAll(MATCH, "$1").split(":");
            String[] split1 = split[2].split("\\.");
            return (Long.valueOf(split[0]) * 3600 + Long.valueOf(split[1]) * 60 + Long.valueOf(split1[0])) * 1000 + Long.valueOf(split1[1]);
          }
        }
      }
    } catch (Exception e) {
      throw new VideoGenException("LengthWrapperImpl: No output", e);
    }
    throw new VideoGenException("LengthWrapperImpl: No output");
  }
}

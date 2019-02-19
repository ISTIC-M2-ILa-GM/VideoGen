package fr.istic.hbmlh.videogen.wrapper.impl;

import fr.istic.hbmlh.videogen.wrapper.FileWrapper;

import java.io.File;

public class FileWrapperImpl implements FileWrapper {

  @Override
  public long sizeOf(String filePath) {
    return new File(filePath).length();
  }
}

package fr.istic.hbmlh.videogen.exception;

public class VideoGenException extends RuntimeException {
  public VideoGenException(String s) {
    super(s);
  }

  public VideoGenException(String s, Throwable throwable) {
    super(s, throwable);
  }
}

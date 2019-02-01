package fr.istic.hbmlh.videogen.VideoGen.dto;

public class ValueWrapper<T> {
  private final T value;


  public ValueWrapper(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}

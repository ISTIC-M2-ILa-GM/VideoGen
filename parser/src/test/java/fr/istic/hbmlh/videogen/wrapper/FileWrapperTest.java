package fr.istic.hbmlh.videogen.wrapper;

import fr.istic.hbmlh.videogen.wrapper.impl.FileWrapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileWrapperTest {

  private FileWrapper fileWrapper;

  @Before
  public void setUp() {
    fileWrapper = new FileWrapperImpl();
  }

  @Test
  public void shouldGetSize() {

    long size = fileWrapper.sizeOf("target/test-classes/alternative.videogen");

    assertThat(size, equalTo(new File("target/test-classes/alternative.videogen").length()));
  }
}

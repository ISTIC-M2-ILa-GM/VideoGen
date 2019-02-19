package fr.istic.hbmlh.videogen.wrapper;

import fr.istic.hbmlh.videogen.factory.impl.ProcessBuilderFactoryImpl;
import fr.istic.hbmlh.videogen.wrapper.impl.LengthWrapperImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

public class LengthWrapperTest {

  private LengthWrapper lengthWrapper;

  @Before
  public void setUp() {

    lengthWrapper = new LengthWrapperImpl(new ProcessBuilderFactoryImpl());
  }

  @Test
  public void shouldRetrieveLength() {

    String filePath = "target/test-classes/nufnuf.webm";

    long length = lengthWrapper.retrieveLength(filePath);

    assertThat(length, equalTo(48080L));
  }
}

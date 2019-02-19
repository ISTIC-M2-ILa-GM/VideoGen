package fr.istic.hbmlh.videogen.factory;

import fr.istic.hbmlh.videogen.exception.VideoGenException;
import fr.istic.hbmlh.videogen.factory.impl.ProcessBuilderFactoryImpl;
import fr.istic.hbmlh.videogen.wrapper.ProcessBuilderWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class ProcessBuilderFactoryTest {

  private ProcessBuilderFactory processBuilderFactory;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() {
    processBuilderFactory = new ProcessBuilderFactoryImpl();
  }

  @Test
  public void shouldThrowExceptionWhenCreateAProcessBuilderWithNullDirectory() {

    thrown.expect(VideoGenException.class);

    processBuilderFactory.create(null, "a-command");
  }

  @Test
  public void shouldThrowExceptionWhenCreateAProcessBuilderWithAWongDirectory() {

    thrown.expect(VideoGenException.class);

    processBuilderFactory.create("not-a-directory", "a-command");
  }

  @Test
  public void shouldThrowExceptionWhenCreateAProcessBuilderWithNullCommand() {

    thrown.expect(VideoGenException.class);

    processBuilderFactory.create("/tmp", null);
  }

  @Test
  public void shouldThrowExceptionWhenCreateAProcessBuilderWithEmptyCommand() {

    thrown.expect(VideoGenException.class);

    processBuilderFactory.create("/tmp", "");
  }

  @Test
  public void shouldCreateAProcessBuilder() {

    ProcessBuilderWrapper processBuilder = processBuilderFactory.create("/tmp", "ls", "test");

    assertThat(processBuilder, notNullValue());
    assertThat(processBuilder.directory().getPath(), equalTo("/tmp"));
    assertThat(processBuilder.command().get(0), equalTo("ls"));
    assertThat(processBuilder.command().get(1), equalTo("test"));
  }
}

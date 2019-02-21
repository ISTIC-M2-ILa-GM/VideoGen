package fr.istic.hbmlh.videogen.analyse;

import fr.istic.hbmlh.videogen.analyse.impl.VideoGenAnalyseImpl;
import fr.istic.hbmlh.videogen.model.AnalysedMedias;
import fr.istic.hbmlh.videogen.model.ParsedMedia;
import fr.istic.hbmlh.videogen.wrapper.FileWrapper;
import fr.istic.hbmlh.videogen.wrapper.LengthWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VideoGenAnalyseTest {

  private VideoGenAnalyse videoGenAnalyse;

  @Mock
  private FileWrapper mockFileWrapper;

  @Mock
  private LengthWrapper mockLengthWrapper;

  @Before
  public void setUp() {

    videoGenAnalyse = new VideoGenAnalyseImpl(mockFileWrapper, mockLengthWrapper);
  }

  @Test
  public void shouldAnalyseSizeFile() {
    List<List<ParsedMedia>> parsedMediaList = new ArrayList<>();
    parsedMediaList.add(Collections.singletonList(ParsedMedia.builder().active(true).fileName("a").build()));
    parsedMediaList.add(Collections.singletonList(ParsedMedia.builder().active(true).fileName("b").build()));
    parsedMediaList.add(asList(ParsedMedia.builder().active(true).fileName("c").build(), ParsedMedia.builder().active(false).fileName("e").build()));
    parsedMediaList.add(Collections.singletonList(ParsedMedia.builder().active(true).fileName("d").build()));

    when(mockFileWrapper.sizeOf("a")).thenReturn(10L);
    when(mockFileWrapper.sizeOf("b")).thenReturn(0L);
    when(mockFileWrapper.sizeOf("c")).thenReturn(5L);
    when(mockFileWrapper.sizeOf("d")).thenReturn(9L);

    AnalysedMedias analysedMedias = videoGenAnalyse.analyse(parsedMediaList);

    assertThat(analysedMedias, notNullValue());
    assertThat(analysedMedias.getLongestSize(), notNullValue());
    assertThat(analysedMedias.getLongestSize().getValue(), hasSize(1));
    assertThat(analysedMedias.getLongestSize().getValue().get(0).getFileName(), equalTo("a"));
    assertThat(analysedMedias.getShortestSize(), notNullValue());
    assertThat(analysedMedias.getShortestSize().getValue(), hasSize(1));
    assertThat(analysedMedias.getShortestSize().getValue().get(0).getFileName(), equalTo("b"));
    assertThat(analysedMedias.getMediumSize(), notNullValue());
    assertThat(analysedMedias.getMediumSize().getValue(), hasSize(2));
    assertThat(analysedMedias.getMediumSize().getValue().get(0).getFileName(), equalTo("c"));
  }

  @Test
  public void shouldAnalyseLengthFile() {
    List<List<ParsedMedia>> parsedMediaList = new ArrayList<>();
    parsedMediaList.add(Collections.singletonList(ParsedMedia.builder().active(true).fileName("a").build()));
    parsedMediaList.add(Collections.singletonList(ParsedMedia.builder().active(true).fileName("b").build()));
    parsedMediaList.add(asList(ParsedMedia.builder().active(true).fileName("c").build(), ParsedMedia.builder().active(false).fileName("e").build()));
    parsedMediaList.add(Collections.singletonList(ParsedMedia.builder().active(true).fileName("d").build()));

    when(mockLengthWrapper.retrieveLength("a")).thenReturn(10L);
    when(mockLengthWrapper.retrieveLength("b")).thenReturn(0L);
    when(mockLengthWrapper.retrieveLength("c")).thenReturn(5L);
    when(mockLengthWrapper.retrieveLength("d")).thenReturn(9L);

    AnalysedMedias analysedMedias = videoGenAnalyse.analyse(parsedMediaList);

    assertThat(analysedMedias, notNullValue());
    assertThat(analysedMedias.getLongestLength(), notNullValue());
    assertThat(analysedMedias.getLongestLength().getValue(), hasSize(1));
    assertThat(analysedMedias.getLongestLength().getValue().get(0).getFileName(), equalTo("a"));
    assertThat(analysedMedias.getShortestLength(), notNullValue());
    assertThat(analysedMedias.getShortestLength().getValue(), hasSize(1));
    assertThat(analysedMedias.getShortestLength().getValue().get(0).getFileName(), equalTo("b"));
    assertThat(analysedMedias.getMediumLength(), notNullValue());
    assertThat(analysedMedias.getMediumLength().getValue(), hasSize(2));
    assertThat(analysedMedias.getMediumLength().getValue().get(0).getFileName(), equalTo("c"));
  }
}

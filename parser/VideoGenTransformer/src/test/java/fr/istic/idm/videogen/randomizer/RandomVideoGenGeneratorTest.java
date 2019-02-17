package fr.istic.idm.videogen.randomizer;

import fr.istic.idm.videogen.model.ParsedMedia;
import fr.istic.idm.videogen.parser.VideoGenParser;
import fr.istic.idm.videogen.randomizer.impl.RandomVideoGenGeneratorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static fr.istic.idm.videogen.TestData.some;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RandomVideoGenGeneratorTest {

    private RandomVideoGenGenerator randomVideoGenGenerator;

    @Mock
    private VideoGenParser mockVideoGenParser;

    @Before
    public void setUp() {
        randomVideoGenGenerator = new RandomVideoGenGeneratorImpl(mockVideoGenParser);
    }

    @Test
    public void shouldGenerateARandomVideoGen() {

        ParsedMedia parsedMedia = some(ParsedMedia.class);
        parsedMedia.setActive(true);
        ParsedMedia parsedMedia1 = some(ParsedMedia.class);
        parsedMedia1.setActive(true);

        when(mockVideoGenParser.parse()).thenReturn(singletonList(asList(parsedMedia, parsedMedia1)));

        List<String> files = randomVideoGenGenerator.generateRandomConfiguration();

        verify(mockVideoGenParser).parse();

        assertThat(files, notNullValue());
        assertThat(files, hasSize(2));
        assertThat(files, hasItem(parsedMedia.getFileName()));
        assertThat(files, hasItem(parsedMedia1.getFileName()));
    }

    @Test
    public void shouldGenerateARandomVideoGenFromAList() {

        ParsedMedia parsedMedia = some(ParsedMedia.class);
        parsedMedia.setActive(true);
        ParsedMedia parsedMedia1 = some(ParsedMedia.class);
        parsedMedia1.setActive(true);

        ParsedMedia parsedMedia2 = some(ParsedMedia.class);
        parsedMedia2.setActive(true);
        ParsedMedia parsedMedia3 = some(ParsedMedia.class);
        parsedMedia3.setActive(true);

        when(mockVideoGenParser.parse()).thenReturn(asList(asList(parsedMedia, parsedMedia1), asList(parsedMedia2, parsedMedia3)));

        List<String> files = randomVideoGenGenerator.generateRandomConfiguration();

        verify(mockVideoGenParser).parse();

        assertThat(files, notNullValue());
        assertThat(files, hasSize(2));
    }

    @Test
    public void shouldGenerateARandomVideoGenWithoutDisabledVideos() {

        ParsedMedia parsedMedia = some(ParsedMedia.class);
        parsedMedia.setActive(true);
        ParsedMedia parsedMedia1 = some(ParsedMedia.class);
        parsedMedia1.setActive(false);

        when(mockVideoGenParser.parse()).thenReturn(singletonList(asList(parsedMedia, parsedMedia1)));

        List<String> files = randomVideoGenGenerator.generateRandomConfiguration();

        verify(mockVideoGenParser).parse();

        assertThat(files, notNullValue());
        assertThat(files, hasSize(1));
        assertThat(files, hasItem(parsedMedia.getFileName()));
    }
}

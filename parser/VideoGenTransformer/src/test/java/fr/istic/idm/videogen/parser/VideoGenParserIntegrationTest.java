package fr.istic.idm.videogen.parser;

import fr.istic.idm.videogen.factory.VideoGenParserFactory;
import fr.istic.idm.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.idm.videogen.generated.VideoGenHelper;
import fr.istic.idm.videogen.model.ParsedMediaList;
import fr.istic.idm.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.idm.videogen.parser.impl.VideoGenParserImpl;
import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VideoGenParserIntegrationTest {

    private VideoGenParserFactory videoGenParserFactory;

    @Before
    public void setUp() {
        videoGenParserFactory = new VideoGenParserFactoryImpl(new VideoGenHelper());
    }

    @Test
    public void shouldParseMandatoryMedias() {
        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/mandatory.videogen");

        List<ParsedMediaList> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(1));
        assertThat(parse.get(0).getParsedMedias(), hasSize(2));
        assertThat(parse.get(0).getParsedMedias().get(0).isActive(), equalTo(true));
        assertThat(parse.get(0).getParsedMedias().get(1).isActive(), equalTo(true));
        assertThat(parse.get(0).getParsedMedias().get(0).getType(), equalTo("MandatoryMedia"));
        assertThat(parse.get(0).getParsedMedias().get(1).getType(), equalTo("MandatoryMedia"));
        assertThat(parse.get(0).getParsedMedias().get(0).getTotalAlternative(), equalTo(0));
        assertThat(parse.get(0).getParsedMedias().get(1).getTotalAlternative(), equalTo(0));
        assertThat(parse.get(0).getParsedMedias().get(0).getFileName(), equalTo("v1.mp4"));
        assertThat(parse.get(0).getParsedMedias().get(1).getFileName(), equalTo("v2.mp4"));
    }

    @Test
    public void testMandatory2() {
        VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("target/test-classes/example.videogen"));
        assertNotNull(videoGen);

        VideoGenParserImpl videoGenParser = new VideoGenParserImpl(videoGen);
        List<ParsedMediaList> parsedMediaList = videoGenParser.parse();
        assertFalse("First opt needs to be false at index 0", parsedMediaList.get(0).getParsedMedias().get(1).isActive());
        assertTrue("First opt needs to be true at index 1", parsedMediaList.get(1).getParsedMedias().get(1).isActive());
        assertFalse("First opt needs to be false at index 2", parsedMediaList.get(2).getParsedMedias().get(1).isActive());
        assertTrue("First opt needs to be true at index 3", parsedMediaList.get(3).getParsedMedias().get(1).isActive());
        assertFalse("First opt needs to be false at index 4", parsedMediaList.get(4).getParsedMedias().get(1).isActive());


        assertFalse("Second opt needs to be false at index 0", parsedMediaList.get(0).getParsedMedias().get(8).isActive());
        assertFalse("Second opt needs to be false at index 1", parsedMediaList.get(1).getParsedMedias().get(8).isActive());
        assertTrue("Second opt needs to be true at index 2", parsedMediaList.get(2).getParsedMedias().get(8).isActive());
        assertTrue("Second opt needs to be true at index 3", parsedMediaList.get(3).getParsedMedias().get(8).isActive());
        assertFalse("Second opt needs to be false at index 4", parsedMediaList.get(4).getParsedMedias().get(8).isActive());


        assertFalse("Third opt needs to be false at index 0", parsedMediaList.get(0).getParsedMedias().get(9).isActive());
        assertFalse("Third opt needs to be false at index 1", parsedMediaList.get(1).getParsedMedias().get(9).isActive());
        assertFalse("Third opt needs to be false at index 2", parsedMediaList.get(2).getParsedMedias().get(9).isActive());
        assertFalse("Third opt needs to be false at index 3", parsedMediaList.get(3).getParsedMedias().get(9).isActive());
        assertTrue("Third opt needs to be true at index 4", parsedMediaList.get(4).getParsedMedias().get(9).isActive());
        assertTrue("Third opt needs to be true at index 5", parsedMediaList.get(5).getParsedMedias().get(9).isActive());
        assertTrue("Third opt needs to be true at index 6", parsedMediaList.get(6).getParsedMedias().get(9).isActive());
        assertTrue("Third opt needs to be true at index 7", parsedMediaList.get(7).getParsedMedias().get(9).isActive());
        assertFalse("Third opt needs to be false at index 8", parsedMediaList.get(8).getParsedMedias().get(9).isActive());
    }
}
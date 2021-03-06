package fr.istic.hbmlh.videogen.parser;

import fr.istic.hbmlh.videogen.factory.VideoGenParserFactory;
import fr.istic.hbmlh.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.hbmlh.videogen.generated.VideoGenHelper;
import fr.istic.hbmlh.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.hbmlh.videogen.model.MediaType;
import fr.istic.hbmlh.videogen.model.ParsedMedia;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

public class VideoGenParserIntegrationTest {

    private VideoGenParserFactory videoGenParserFactory;

    @Before
    public void setUp() {
        videoGenParserFactory = new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl());
    }

    @Test
    public void shouldParseOptionalsAndAlternatives() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/optionalsAlternatives.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        boolean[][] expectedValues = new boolean[][]{
                new boolean[]{false, true, false, false, true, false, false},
                new boolean[]{true, true, false, false, true, false, false},
                new boolean[]{false, true, false, true, true, false, false},
                new boolean[]{true, true, false, true, true, false, false},
                new boolean[]{false, false, true, false, true, false, false},
                new boolean[]{true, false, true, false, true, false, false},
                new boolean[]{false, false, true, true, true, false, false},
                new boolean[]{true, false, true, true, true, false, false},
                new boolean[]{false, true, false, false, false, true, false},
                new boolean[]{true, true, false, false, false, true, false},
                new boolean[]{false, true, false, true, false, true, false},
                new boolean[]{true, true, false, true, false, true, false},
                new boolean[]{false, false, true, false, false, true, false},
                new boolean[]{true, false, true, false, false, true, false},
                new boolean[]{false, false, true, true, false, true, false},
                new boolean[]{true, false, true, true, false, true, false},
                new boolean[]{false, true, false, false, false, false, true},
                new boolean[]{true, true, false, false, false, false, true},
                new boolean[]{false, true, false, true, false, false, true},
                new boolean[]{true, true, false, true, false, false, true},
                new boolean[]{false, false, true, false, false, false, true},
                new boolean[]{true, false, true, false, false, false, true},
                new boolean[]{false, false, true, true, false, false, true},
                new boolean[]{true, false, true, true, false, false, true}
        };

        assertValues(parse, expectedValues);
    }

    @Test
    public void shouldParseMandatoriesOptionalsAndAlternatives() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/mandatoriesOptionalsAlternatives.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        boolean[][] expectedValues = new boolean[][]{
                new boolean[]{true, false, true, false, false, false, true, false, true, true, false, false, false},
                new boolean[]{true, true, true, false, false, false, true, false, true, true, false, false, false},
                new boolean[]{true, false, true, false, true, false, true, false, true, true, false, false, false},
                new boolean[]{true, true, true, false, true, false, true, false, true, true, false, false, false},
                new boolean[]{true, false, true, false, false, true, true, false, true, true, false, false, false},
                new boolean[]{true, true, true, false, false, true, true, false, true, true, false, false, false},
                new boolean[]{true, false, true, false, true, true, true, false, true, true, false, false, false},
                new boolean[]{true, true, true, false, true, true, true, false, true, true, false, false, false},
                new boolean[]{true, false, false, true, false, false, true, false, true, true, false, false, false},
                new boolean[]{true, true, false, true, false, false, true, false, true, true, false, false, false},
                new boolean[]{true, false, false, true, true, false, true, false, true, true, false, false, false},
                new boolean[]{true, true, false, true, true, false, true, false, true, true, false, false, false},
                new boolean[]{true, false, false, true, false, true, true, false, true, true, false, false, false},
                new boolean[]{true, true, false, true, false, true, true, false, true, true, false, false, false},
                new boolean[]{true, false, false, true, true, true, true, false, true, true, false, false, false},
                new boolean[]{true, true, false, true, true, true, true, false, true, true, false, false, false},
                new boolean[]{true, false, true, false, false, false, false, true, true, true, false, false, false},
                new boolean[]{true, true, true, false, false, false, false, true, true, true, false, false, false},
                new boolean[]{true, false, true, false, true, false, false, true, true, true, false, false, false},
                new boolean[]{true, true, true, false, true, false, false, true, true, true, false, false, false},
                new boolean[]{true, false, true, false, false, true, false, true, true, true, false, false, false},
                new boolean[]{true, true, true, false, false, true, false, true, true, true, false, false, false},
                new boolean[]{true, false, true, false, true, true, false, true, true, true, false, false, false},
                new boolean[]{true, true, true, false, true, true, false, true, true, true, false, false, false},
                new boolean[]{true, false, false, true, false, false, false, true, true, true, false, false, false},
                new boolean[]{true, true, false, true, false, false, false, true, true, true, false, false, false},
                new boolean[]{true, false, false, true, true, false, false, true, true, true, false, false, false},
                new boolean[]{true, true, false, true, true, false, false, true, true, true, false, false, false},
                new boolean[]{true, false, false, true, false, true, false, true, true, true, false, false, false},
                new boolean[]{true, true, false, true, false, true, false, true, true, true, false, false, false},
                new boolean[]{true, false, false, true, true, true, false, true, true, true, false, false, false},
                new boolean[]{true, true, false, true, true, true, false, true, true, true, false, false, false},
                new boolean[]{true, false, true, false, false, false, true, false, true, false, true, false, false},
                new boolean[]{true, true, true, false, false, false, true, false, true, false, true, false, false},
                new boolean[]{true, false, true, false, true, false, true, false, true, false, true, false, false},
                new boolean[]{true, true, true, false, true, false, true, false, true, false, true, false, false},
                new boolean[]{true, false, true, false, false, true, true, false, true, false, true, false, false},
                new boolean[]{true, true, true, false, false, true, true, false, true, false, true, false, false},
                new boolean[]{true, false, true, false, true, true, true, false, true, false, true, false, false},
                new boolean[]{true, true, true, false, true, true, true, false, true, false, true, false, false},
                new boolean[]{true, false, false, true, false, false, true, false, true, false, true, false, false},
                new boolean[]{true, true, false, true, false, false, true, false, true, false, true, false, false},
                new boolean[]{true, false, false, true, true, false, true, false, true, false, true, false, false},
                new boolean[]{true, true, false, true, true, false, true, false, true, false, true, false, false},
                new boolean[]{true, false, false, true, false, true, true, false, true, false, true, false, false},
                new boolean[]{true, true, false, true, false, true, true, false, true, false, true, false, false},
                new boolean[]{true, false, false, true, true, true, true, false, true, false, true, false, false},
                new boolean[]{true, true, false, true, true, true, true, false, true, false, true, false, false},
                new boolean[]{true, false, true, false, false, false, false, true, true, false, true, false, false},
                new boolean[]{true, true, true, false, false, false, false, true, true, false, true, false, false},
                new boolean[]{true, false, true, false, true, false, false, true, true, false, true, false, false},
                new boolean[]{true, true, true, false, true, false, false, true, true, false, true, false, false},
                new boolean[]{true, false, true, false, false, true, false, true, true, false, true, false, false},
                new boolean[]{true, true, true, false, false, true, false, true, true, false, true, false, false},
                new boolean[]{true, false, true, false, true, true, false, true, true, false, true, false, false},
                new boolean[]{true, true, true, false, true, true, false, true, true, false, true, false, false},
                new boolean[]{true, false, false, true, false, false, false, true, true, false, true, false, false},
                new boolean[]{true, true, false, true, false, false, false, true, true, false, true, false, false},
                new boolean[]{true, false, false, true, true, false, false, true, true, false, true, false, false},
                new boolean[]{true, true, false, true, true, false, false, true, true, false, true, false, false},
                new boolean[]{true, false, false, true, false, true, false, true, true, false, true, false, false},
                new boolean[]{true, true, false, true, false, true, false, true, true, false, true, false, false},
                new boolean[]{true, false, false, true, true, true, false, true, true, false, true, false, false},
                new boolean[]{true, true, false, true, true, true, false, true, true, false, true, false, false},
                new boolean[]{true, false, true, false, false, false, true, false, true, false, false, true, false},
                new boolean[]{true, true, true, false, false, false, true, false, true, false, false, true, false},
                new boolean[]{true, false, true, false, true, false, true, false, true, false, false, true, false},
                new boolean[]{true, true, true, false, true, false, true, false, true, false, false, true, false},
                new boolean[]{true, false, true, false, false, true, true, false, true, false, false, true, false},
                new boolean[]{true, true, true, false, false, true, true, false, true, false, false, true, false},
                new boolean[]{true, false, true, false, true, true, true, false, true, false, false, true, false},
                new boolean[]{true, true, true, false, true, true, true, false, true, false, false, true, false},
                new boolean[]{true, false, false, true, false, false, true, false, true, false, false, true, false},
                new boolean[]{true, true, false, true, false, false, true, false, true, false, false, true, false},
                new boolean[]{true, false, false, true, true, false, true, false, true, false, false, true, false},
                new boolean[]{true, true, false, true, true, false, true, false, true, false, false, true, false},
                new boolean[]{true, false, false, true, false, true, true, false, true, false, false, true, false},
                new boolean[]{true, true, false, true, false, true, true, false, true, false, false, true, false},
                new boolean[]{true, false, false, true, true, true, true, false, true, false, false, true, false},
                new boolean[]{true, true, false, true, true, true, true, false, true, false, false, true, false},
                new boolean[]{true, false, true, false, false, false, false, true, true, false, false, true, false},
                new boolean[]{true, true, true, false, false, false, false, true, true, false, false, true, false},
                new boolean[]{true, false, true, false, true, false, false, true, true, false, false, true, false},
                new boolean[]{true, true, true, false, true, false, false, true, true, false, false, true, false},
                new boolean[]{true, false, true, false, false, true, false, true, true, false, false, true, false},
                new boolean[]{true, true, true, false, false, true, false, true, true, false, false, true, false},
                new boolean[]{true, false, true, false, true, true, false, true, true, false, false, true, false},
                new boolean[]{true, true, true, false, true, true, false, true, true, false, false, true, false},
                new boolean[]{true, false, false, true, false, false, false, true, true, false, false, true, false},
                new boolean[]{true, true, false, true, false, false, false, true, true, false, false, true, false},
                new boolean[]{true, false, false, true, true, false, false, true, true, false, false, true, false},
                new boolean[]{true, true, false, true, true, false, false, true, true, false, false, true, false},
                new boolean[]{true, false, false, true, false, true, false, true, true, false, false, true, false},
                new boolean[]{true, true, false, true, false, true, false, true, true, false, false, true, false},
                new boolean[]{true, false, false, true, true, true, false, true, true, false, false, true, false},
                new boolean[]{true, true, false, true, true, true, false, true, true, false, false, true, false},
                new boolean[]{true, false, true, false, false, false, true, false, true, false, false, false, true},
                new boolean[]{true, true, true, false, false, false, true, false, true, false, false, false, true},
                new boolean[]{true, false, true, false, true, false, true, false, true, false, false, false, true},
                new boolean[]{true, true, true, false, true, false, true, false, true, false, false, false, true},
                new boolean[]{true, false, true, false, false, true, true, false, true, false, false, false, true},
                new boolean[]{true, true, true, false, false, true, true, false, true, false, false, false, true},
                new boolean[]{true, false, true, false, true, true, true, false, true, false, false, false, true},
                new boolean[]{true, true, true, false, true, true, true, false, true, false, false, false, true},
                new boolean[]{true, false, false, true, false, false, true, false, true, false, false, false, true},
                new boolean[]{true, true, false, true, false, false, true, false, true, false, false, false, true},
                new boolean[]{true, false, false, true, true, false, true, false, true, false, false, false, true},
                new boolean[]{true, true, false, true, true, false, true, false, true, false, false, false, true},
                new boolean[]{true, false, false, true, false, true, true, false, true, false, false, false, true},
                new boolean[]{true, true, false, true, false, true, true, false, true, false, false, false, true},
                new boolean[]{true, false, false, true, true, true, true, false, true, false, false, false, true},
                new boolean[]{true, true, false, true, true, true, true, false, true, false, false, false, true},
                new boolean[]{true, false, true, false, false, false, false, true, true, false, false, false, true},
                new boolean[]{true, true, true, false, false, false, false, true, true, false, false, false, true},
                new boolean[]{true, false, true, false, true, false, false, true, true, false, false, false, true},
                new boolean[]{true, true, true, false, true, false, false, true, true, false, false, false, true},
                new boolean[]{true, false, true, false, false, true, false, true, true, false, false, false, true},
                new boolean[]{true, true, true, false, false, true, false, true, true, false, false, false, true},
                new boolean[]{true, false, true, false, true, true, false, true, true, false, false, false, true},
                new boolean[]{true, true, true, false, true, true, false, true, true, false, false, false, true},
                new boolean[]{true, false, false, true, false, false, false, true, true, false, false, false, true},
                new boolean[]{true, true, false, true, false, false, false, true, true, false, false, false, true},
                new boolean[]{true, false, false, true, true, false, false, true, true, false, false, false, true},
                new boolean[]{true, true, false, true, true, false, false, true, true, false, false, false, true},
                new boolean[]{true, false, false, true, false, true, false, true, true, false, false, false, true},
                new boolean[]{true, true, false, true, false, true, false, true, true, false, false, false, true},
                new boolean[]{true, false, false, true, true, true, false, true, true, false, false, false, true},
                new boolean[]{true, true, false, true, true, true, false, true, true, false, false, false, true},
        };

        assertValues(parse, expectedValues);
    }

    private void assertValues(List<List<ParsedMedia>> parse, boolean[][] expectedValues) {
        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(expectedValues.length));
        for (int i = 0; i < expectedValues.length; i++) {
            assertThat(String.format("The index %s has wrong length", i), parse.get(i).size(), equalTo(expectedValues[i].length));
            assertThat(String.format("The index %s has wrong values", i), mapToActive(parse.get(i)), equalTo(expectedValues[i]));
        }
    }

    private Object[] mapToActive(List<ParsedMedia> parsedMedias) {
        return parsedMedias.stream().map(ParsedMedia::isActive).toArray();
    }

    @Test
    public void shouldParseMandatoryMedias() {
        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/mandatory.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(1));
        assertThat(parse.get(0), hasSize(2));
        assertThat(parse.get(0).get(0).isActive(), equalTo(true));
        assertThat(parse.get(0).get(1).isActive(), equalTo(true));
        assertThat(parse.get(0).get(0).getType(), equalTo(MediaType.MANDATORY));
        assertThat(parse.get(0).get(1).getType(), equalTo(MediaType.MANDATORY));
        assertThat(parse.get(0).get(0).getCurrentAlternatives(), equalTo(0));
        assertThat(parse.get(0).get(1).getCurrentAlternatives(), equalTo(0));
        assertThat(parse.get(0).get(0).getFileName(), equalTo("v1.mp4"));
        assertThat(parse.get(0).get(1).getFileName(), equalTo("v2.mp4"));
    }

    @Test
    public void shouldParseOptionalMedias() {
        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/optional.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(8));

        assertThat(parse.get(0).get(0).getType(), equalTo(MediaType.OPTIONAL));
        assertThat(parse.get(0).get(1).getType(), equalTo(MediaType.OPTIONAL));
        assertThat(parse.get(0).get(2).getType(), equalTo(MediaType.OPTIONAL));

        assertThat(parse.get(0).get(0).getCurrentAlternatives(), equalTo(0));
        assertThat(parse.get(0).get(1).getCurrentAlternatives(), equalTo(0));
        assertThat(parse.get(0).get(2).getCurrentAlternatives(), equalTo(0));

        assertThat(parse.get(0).get(0).getFileName(), equalTo("v1.mp4"));
        assertThat(parse.get(0).get(1).getFileName(), equalTo("v2.mp4"));
        assertThat(parse.get(0).get(2).getFileName(), equalTo("v3.mp4"));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(2);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(3);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(4);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));

        parsedMedias = parse.get(5);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));

        parsedMedias = parse.get(6);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));

        parsedMedias = parse.get(7);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
    }

    @Test
    public void shouldParseOptionalAndMandatoryMedias() {
        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/optionalMandatory.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(8));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(5));

        assertThat(parsedMedias.get(0).getType(), equalTo(MediaType.OPTIONAL));
        assertThat(parsedMedias.get(1).getType(), equalTo(MediaType.MANDATORY));
        assertThat(parsedMedias.get(2).getType(), equalTo(MediaType.OPTIONAL));
        assertThat(parsedMedias.get(3).getType(), equalTo(MediaType.MANDATORY));
        assertThat(parsedMedias.get(4).getType(), equalTo(MediaType.OPTIONAL));

        assertThat(parsedMedias.get(0).getCurrentAlternatives(), equalTo(0));
        assertThat(parsedMedias.get(1).getCurrentAlternatives(), equalTo(0));
        assertThat(parsedMedias.get(2).getCurrentAlternatives(), equalTo(0));
        assertThat(parsedMedias.get(3).getCurrentAlternatives(), equalTo(0));
        assertThat(parsedMedias.get(4).getCurrentAlternatives(), equalTo(0));

        assertThat(parsedMedias.get(0).getFileName(), equalTo("v1.mp4"));
        assertThat(parsedMedias.get(1).getFileName(), equalTo("v2.mp4"));
        assertThat(parsedMedias.get(2).getFileName(), equalTo("v3.mp4"));
        assertThat(parsedMedias.get(3).getFileName(), equalTo("v4.mp4"));
        assertThat(parsedMedias.get(4).getFileName(), equalTo("v5.mp4"));

        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(2);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(3);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(4);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(5);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(6);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(7);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
    }

    @Test
    public void shouldParseOneAlternative() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/alternative.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(3));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(3));

        assertThat(parsedMedias.get(0).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(1).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(2).getType(), equalTo(MediaType.ALTERNATIVE));

        assertThat(parsedMedias.get(0).getCurrentAlternatives(), equalTo(3));
        assertThat(parsedMedias.get(1).getCurrentAlternatives(), equalTo(3));
        assertThat(parsedMedias.get(2).getCurrentAlternatives(), equalTo(3));

        assertThat(parsedMedias.get(0).getFileName(), equalTo("v11.mp4"));
        assertThat(parsedMedias.get(1).getFileName(), equalTo("v12.mp4"));
        assertThat(parsedMedias.get(2).getFileName(), equalTo("v13.mp4"));

        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(2);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));

    }

    @Test
    public void shouldParseTwoAlternatives() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/alternatives.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(6));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(5));

        assertThat(parsedMedias.get(0).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(1).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(2).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(3).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(4).getType(), equalTo(MediaType.ALTERNATIVE));

        assertThat(parsedMedias.get(0).getCurrentAlternatives(), equalTo(3));
        assertThat(parsedMedias.get(1).getCurrentAlternatives(), equalTo(3));
        assertThat(parsedMedias.get(2).getCurrentAlternatives(), equalTo(3));
        assertThat(parsedMedias.get(3).getCurrentAlternatives(), equalTo(2));
        assertThat(parsedMedias.get(4).getCurrentAlternatives(), equalTo(2));

        assertThat(parsedMedias.get(0).getFileName(), equalTo("v11.mp4"));
        assertThat(parsedMedias.get(1).getFileName(), equalTo("v12.mp4"));
        assertThat(parsedMedias.get(2).getFileName(), equalTo("v13.mp4"));
        assertThat(parsedMedias.get(3).getFileName(), equalTo("v21.mp4"));
        assertThat(parsedMedias.get(4).getFileName(), equalTo("v22.mp4"));

        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));

        parsedMedias = parse.get(2);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));

        parsedMedias = parse.get(3);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(false));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));

        parsedMedias = parse.get(4);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(false));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));

        parsedMedias = parse.get(5);
        assertThat(parsedMedias, hasSize(5));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(false));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));
    }

    @Test
    public void shouldParseMandatoryAndAlternatives() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/mandatoryAlternatives.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(6));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(7));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));
        assertThat(parsedMedias.get(5).isActive(), equalTo(false));
        assertThat(parsedMedias.get(6).isActive(), equalTo(false));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(7));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(true));
        assertThat(parsedMedias.get(5).isActive(), equalTo(false));
        assertThat(parsedMedias.get(6).isActive(), equalTo(false));

        parsedMedias = parse.get(2);
        assertThat(parsedMedias, hasSize(7));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(5).isActive(), equalTo(true));
        assertThat(parsedMedias.get(6).isActive(), equalTo(false));

        parsedMedias = parse.get(3);
        assertThat(parsedMedias, hasSize(7));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(5).isActive(), equalTo(true));
        assertThat(parsedMedias.get(6).isActive(), equalTo(false));

        parsedMedias = parse.get(4);
        assertThat(parsedMedias, hasSize(7));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(5).isActive(), equalTo(false));
        assertThat(parsedMedias.get(6).isActive(), equalTo(true));

        parsedMedias = parse.get(5);
        assertThat(parsedMedias, hasSize(7));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
        assertThat(parsedMedias.get(4).isActive(), equalTo(false));
        assertThat(parsedMedias.get(5).isActive(), equalTo(false));
        assertThat(parsedMedias.get(6).isActive(), equalTo(true));
    }

    @Test
    public void shouldParseMandatoryAndAlternative() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/mandatoryAlternative.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(2));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
    }

    @Test
    public void shouldParseOptionalAndAlternative() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/optionalAlternative.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(4));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));

        parsedMedias = parse.get(2);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));

        parsedMedias = parse.get(3);
        assertThat(parsedMedias, hasSize(3));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
    }

    @Test
    public void shouldParseOptionalsAndAlternative() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("target/test-classes/optionalsAlternative.videogen");

        List<List<ParsedMedia>> parse = videoGenParser.parse();

        assertThat(parse, notNullValue());
        assertThat(parse, hasSize(8));

        List<ParsedMedia> parsedMedias = parse.get(0);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(false));

        parsedMedias = parse.get(1);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(false));

        parsedMedias = parse.get(2);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(3);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(true));
        assertThat(parsedMedias.get(2).isActive(), equalTo(false));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(4);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(false));

        parsedMedias = parse.get(5);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(false));

        parsedMedias = parse.get(6);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));

        parsedMedias = parse.get(7);
        assertThat(parsedMedias, hasSize(4));
        assertThat(parsedMedias.get(0).isActive(), equalTo(true));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
        assertThat(parsedMedias.get(2).isActive(), equalTo(true));
        assertThat(parsedMedias.get(3).isActive(), equalTo(true));
    }
}

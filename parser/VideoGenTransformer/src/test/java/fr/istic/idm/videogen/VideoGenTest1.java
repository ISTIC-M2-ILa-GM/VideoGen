package fr.istic.idm.videogen;

import fr.istic.VideoGenHelper;
import fr.istic.idm.videogen.model.ParsedMediaList;
import fr.istic.idm.videogen.parser.VideoGenCsvParser;
import fr.istic.videoGen.VideoGeneratorModel;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VideoGenTest1 {

    @Test
    public void testInJava1() {

        VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example.videogen"));
        assertNotNull(videoGen);

        VideoGenCsvParser videoGenParser = new VideoGenCsvParser(videoGen);
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
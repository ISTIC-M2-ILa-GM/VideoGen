package fr.istic.hbmlh.videogen.factory.impl;

import fr.istic.hbmlh.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.hbmlh.videogen.factory.VideoGenParserFactory;
import fr.istic.hbmlh.videogen.generated.VideoGenHelper;
import fr.istic.hbmlh.videogen.mapper.MediaMapper;
import fr.istic.hbmlh.videogen.parser.VideoGenParser;
import fr.istic.hbmlh.videogen.parser.impl.VideoGenParserImpl;
import lombok.AllArgsConstructor;
import org.eclipse.emf.common.util.URI;

@AllArgsConstructor
public class VideoGenParserFactoryImpl implements VideoGenParserFactory {

    private VideoGenHelper videoGenHelper;

    private MediaMapper mediaMapper;

    @Override
    public VideoGenParser create(String videoGenFilePath) {
        VideoGeneratorModel videoGen = videoGenHelper.loadVideoGenerator(URI.createURI(videoGenFilePath));
        return new VideoGenParserImpl(videoGen, mediaMapper);
    }
}

package fr.istic.idm.videogen.factory.impl;

import fr.istic.idm.videogen.factory.VideoGenParserFactory;
import fr.istic.idm.videogen.generated.VideoGenHelper;
import fr.istic.idm.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.idm.videogen.mapper.MediaMapper;
import fr.istic.idm.videogen.parser.VideoGenParser;
import fr.istic.idm.videogen.parser.impl.VideoGenParserImpl;
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

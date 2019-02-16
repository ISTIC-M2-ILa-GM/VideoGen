package fr.istic.idm.videogen.factory;

import fr.istic.idm.videogen.parser.VideoGenParser;

public interface VideoGenParserFactory {
    VideoGenParser create(String videoGenFilePath);
}

package fr.istic.hbmlh.videogen.factory;

import fr.istic.hbmlh.videogen.parser.VideoGenParser;

public interface VideoGenParserFactory {
    VideoGenParser create(String videoGenFilePath);
}

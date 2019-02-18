package fr.istic.hbmlh.videogen.parser;

import fr.istic.hbmlh.videogen.model.ParsedMedia;

import java.util.List;

public interface VideoGenParser {
    List<List<ParsedMedia>> parse();
}

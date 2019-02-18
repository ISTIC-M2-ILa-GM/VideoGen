package fr.istic.idm.videogen.parser;

import fr.istic.idm.videogen.model.ParsedMedia;

import java.util.List;

public interface VideoGenParser {
    List<List<ParsedMedia>> parse();
}

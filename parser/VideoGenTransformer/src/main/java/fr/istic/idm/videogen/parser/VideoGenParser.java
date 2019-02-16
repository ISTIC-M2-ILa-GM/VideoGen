package fr.istic.idm.videogen.parser;

import fr.istic.idm.videogen.model.ParsedMediaList;

import java.util.List;

public interface VideoGenParser {
    List<ParsedMediaList> parse();
}

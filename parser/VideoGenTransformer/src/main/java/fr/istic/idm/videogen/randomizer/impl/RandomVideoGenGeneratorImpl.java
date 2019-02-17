package fr.istic.idm.videogen.randomizer.impl;

import fr.istic.idm.videogen.model.ParsedMedia;
import fr.istic.idm.videogen.parser.VideoGenParser;
import fr.istic.idm.videogen.randomizer.RandomVideoGenGenerator;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RandomVideoGenGeneratorImpl implements RandomVideoGenGenerator {

    private VideoGenParser videoGenParser;

    @Override
    public List<String> generateRandomConfiguration() {

        List<List<ParsedMedia>> parsed = videoGenParser.parse();

        List<ParsedMedia> selectedVideo = parsed.get(new Random().nextInt(parsed.size()));

        return selectedVideo.stream()
                .filter(ParsedMedia::isActive)
                .map(ParsedMedia::getFileName)
                .collect(Collectors.toList());
    }
}

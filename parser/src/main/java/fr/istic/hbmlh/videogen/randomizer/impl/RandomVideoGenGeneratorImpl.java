package fr.istic.hbmlh.videogen.randomizer.impl;

import fr.istic.hbmlh.videogen.model.ParsedMedia;
import fr.istic.hbmlh.videogen.parser.VideoGenParser;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class RandomVideoGenGeneratorImpl implements RandomVideoGenGenerator {

    private VideoGenParser videoGenParser;

    @Override
    public List<String> generateRandomConfiguration() {

        log.info("VideoGenParserImpl: Sélection d'une variante...");
        List<List<ParsedMedia>> parsed = videoGenParser.parse();

        List<ParsedMedia> selectedVideo = parsed.get(new Random().nextInt(parsed.size()));

      List<String> selected = selectedVideo.stream()
        .filter(ParsedMedia::isActive)
        .map(ParsedMedia::getFileName)
        .collect(Collectors.toList());

      log.info("VideoGenParserImpl: sélection faite, nous avons {} videos.", selected.size());
      return selected;
    }
}

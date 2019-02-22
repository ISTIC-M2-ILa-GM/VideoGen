package fr.istic.hbmlh.videogen.parser.impl;

import fr.istic.hbmlh.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.hbmlh.videogen.generated.videoGen.OptionalMedia;
import fr.istic.hbmlh.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.hbmlh.videogen.parser.VideoGenParser;
import fr.istic.hbmlh.videogen.mapper.MediaMapper;
import fr.istic.hbmlh.videogen.model.MediaType;
import fr.istic.hbmlh.videogen.model.ParsedMedia;
import fr.istic.hbmlh.videogen.model.PreCalculationValues;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class VideoGenParserImpl implements VideoGenParser {

    private VideoGeneratorModel videoGeneratorModel;

    private MediaMapper mediaMapper;

    public List<List<ParsedMedia>> parse() {

        log.info("VideoGenParserImpl: début du parsing...");
        PreCalculationValues preCalculation = preCalculate();

        List<List<ParsedMedia>> parsedMediaLists = generateMediaLists(preCalculation.getVariants());

        for (int i = 0; i < parsedMediaLists.size(); i++) {
            List<ParsedMedia> p = parsedMediaLists.get(i);
            parseOptionals(preCalculation, i, filter(p, MediaType.OPTIONAL));
            parseAlternatives(preCalculation, i, filter(p, MediaType.ALTERNATIVE));
        }
        log.info("VideoGenParserImpl: Parsing fini, nous avons {} variantes", parsedMediaLists.size());
        return parsedMediaLists;
    }

    //TODO refactor useless pre calculation
    private PreCalculationValues preCalculate() {

        List<AlternativesMedia> alternatives = videoGeneratorModel.getMedias().stream().filter(m -> m instanceof AlternativesMedia).map(m -> (AlternativesMedia) m).collect(Collectors.toList());
        List<OptionalMedia> optionals = videoGeneratorModel.getMedias().stream().filter(m -> m instanceof OptionalMedia).map(m -> (OptionalMedia) m).collect(Collectors.toList());

        int alternativeVariants = alternatives.stream().map(m -> m.getMedias().size()).filter(s -> s != 0).reduce((i1, i2) -> i1 * i2).orElse(0);
        int optionalVariants = (int) Math.pow(2, optionals.size());

        return PreCalculationValues.builder()
                .variants(alternativeVariants == 0 ? optionalVariants : optionalVariants * alternativeVariants)
                .alternativesSize(alternatives.stream().map(m -> m.getMedias().size()).filter(s -> s != 0).reduce((i1, i2) -> i1 + i2).orElse(0))
                .optionalsSize(optionals.size())
                .optionalVariants(optionalVariants)
                .build();
    }

    private List<ParsedMedia> filter(List<ParsedMedia> parsedMedia, MediaType type) {
        return parsedMedia.stream().filter(m -> type.equals(m.getType())).collect(Collectors.toList());
    }

    private List<List<ParsedMedia>> generateMediaLists(int variants) {
        List<List<ParsedMedia>> parsedMediaLists = new ArrayList<>();
        for (int i = 0; i < variants; i++) {
            parsedMediaLists.add(mediaMapper.toParsedMedias(videoGeneratorModel.getMedias()));
        }
        return parsedMediaLists;
    }

    private void parseOptionals(PreCalculationValues preCalculation, int index, List<ParsedMedia> optionals) {
        for (int i = 0; i < preCalculation.getOptionalsSize(); i++) {
            double optionalIndex = Math.pow(2, (i + 1));
            if (index % optionalIndex >= optionalIndex / 2) {
                optionals.get(i).setActive(true);
            }
        }
    }

    private void parseAlternatives(PreCalculationValues preCalculation, int index, List<ParsedMedia> alternatives) {
        for (int i = 0; i < preCalculation.getAlternativesSize(); i++) {
            ParsedMedia parsedMedia = alternatives.get(i);
            int previousIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getIndex() * preCalculation.getOptionalVariants();
            int currentIndex = parsedMedia.getPreviousAlternatives() * (parsedMedia.getIndex() + 1) * preCalculation.getOptionalVariants();
            int nextIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getCurrentAlternatives() * preCalculation.getOptionalVariants();
            int position = index % nextIndex;
            if (position < currentIndex && position >= previousIndex) {
                parsedMedia.setActive(true);
            }
        }
    }
}

package fr.istic.idm.videogen.parser.impl;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.idm.videogen.mapper.MediaMapper;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;
import fr.istic.idm.videogen.model.PreCalculationValues;
import fr.istic.idm.videogen.parser.VideoGenParser;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class VideoGenParserImpl implements VideoGenParser {

    private VideoGeneratorModel videoGeneratorModel;

    private MediaMapper mediaMapper;

    public List<List<ParsedMedia>> parse() {

        PreCalculationValues preCalculation = preCalculate();

        List<List<ParsedMedia>> parsedMediaLists = generateMediaLists(preCalculation.getVariants());

        for (int j = 0; j < parsedMediaLists.size(); j++) {
            List<ParsedMedia> p = parsedMediaLists.get(j);
            parseOptionals(preCalculation, j, filter(p, MediaType.OPTIONAL));
            parseAlternatives(preCalculation, j, filter(p, MediaType.ALTERNATIVE));
        }

        return parsedMediaLists;
    }

    //TODO refactor useless pre calculation
    private PreCalculationValues preCalculate() {
        List<AlternativesMedia> alternatives = videoGeneratorModel.getMedias().stream().filter(m -> m instanceof AlternativesMedia).map(m -> (AlternativesMedia) m).collect(Collectors.toList());
        List<OptionalMedia> optionals = videoGeneratorModel.getMedias().stream().filter(m -> m instanceof OptionalMedia).map(m -> (OptionalMedia) m).collect(Collectors.toList());
        int alternativeVariants = alternatives.stream().map(m -> m.getMedias().size()).filter(s -> s != 0).reduce((i1, i2) -> i1 * i2).orElse(0);
        int alternativesSize = alternatives.stream().map(m -> m.getMedias().size()).filter(s -> s != 0).reduce((i1, i2) -> i1 + i2).orElse(0);
        int optionalVariants = (int) Math.pow(2, optionals.size());
        int variants;
        if (alternativeVariants == 0) {
            variants = optionalVariants;
        } else {
            variants = optionalVariants * alternativeVariants;
        }
        return PreCalculationValues.builder().variants(variants).alternativesSize(alternativesSize).optionalsSize(optionals.size()).optionalVariants(optionalVariants).build();
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

    private void parseAlternatives(PreCalculationValues preCalculation, int j, List<ParsedMedia> alternatives) {
        for (int k = 0; k < preCalculation.getAlternativesSize(); k++) {
            ParsedMedia parsedMedia = alternatives.get(k);
            int previousIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getIndex() * preCalculation.getOptionalVariants();
            int currentIndex = parsedMedia.getPreviousAlternatives() * (parsedMedia.getIndex() + 1) * preCalculation.getOptionalVariants();
            int nextIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getCurrentAlternatives() * preCalculation.getOptionalVariants();
            if (j % nextIndex < currentIndex && j % nextIndex >= previousIndex) {
                parsedMedia.setActive(true);
            }
        }
    }
}

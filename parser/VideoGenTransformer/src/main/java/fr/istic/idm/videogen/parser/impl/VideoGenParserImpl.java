package fr.istic.idm.videogen.parser.impl;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.idm.videogen.mapper.MediaMapper;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;
import fr.istic.idm.videogen.parser.VideoGenParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class VideoGenParserImpl implements VideoGenParser {

    private VideoGeneratorModel videoGeneratorModel;

    private MediaMapper mediaMapper;

    public List<List<ParsedMedia>> parse() {

        PreCalculationValues preCalculation = preCalculate();

        List<List<ParsedMedia>> parsedMediaLists = generateMediaLists(preCalculation.getTotal());

        for (int j = 0; j < preCalculation.getTotal(); j++) {
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
        int totalAlternatives = alternatives.stream().map(m -> m.getMedias().size()).filter(s -> s != 0).reduce((i1, i2) -> i1 * i2).orElse(0);
        int totalAlternativeMedia = alternatives.stream().map(m -> m.getMedias().size()).filter(s -> s != 0).reduce((i1, i2) -> i1 + i2).orElse(0);
        int totalOptionals = (int) Math.pow(2, optionals.size());
        int total;
        if (totalOptionals == 0 && totalAlternatives == 0) {
            total = 1;
        } else if (totalAlternatives == 0) {
            total = totalOptionals;
        } else {
            total = totalOptionals * totalAlternatives;
        }
        return PreCalculationValues.builder().total(total).alternativeSize(totalAlternativeMedia).optionalsSize(optionals.size()).optionalVariantes(totalOptionals).build();
    }

    private List<ParsedMedia> filter(List<ParsedMedia> p, MediaType type) {
        return p.stream().filter(m -> type.equals(m.getType())).collect(Collectors.toList());
    }

    private List<List<ParsedMedia>> generateMediaLists(int occurence) {
        List<List<ParsedMedia>> parsedMediaLists = new ArrayList<>();
        for (int i = 0; i < occurence; i++) {
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
        for (int k = 0; k < preCalculation.getAlternativeSize(); k++) {
            ParsedMedia parsedMedia = alternatives.get(k);
            int previousIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getIndex() * preCalculation.getOptionalVariantes();
            int currentIndex = parsedMedia.getPreviousAlternatives() * (parsedMedia.getIndex() + 1) * preCalculation.getOptionalVariantes();
            int nextIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getTotalAlternative() * preCalculation.getOptionalVariantes();
            if (j % nextIndex < currentIndex && j % nextIndex >= previousIndex) {
                parsedMedia.setActive(true);
            }
        }
    }

    @Data
    @Builder
    private static class PreCalculationValues {
        private int optionalsSize;
        private int optionalVariantes;
        private int alternativeSize;
        private int total;
    }
}

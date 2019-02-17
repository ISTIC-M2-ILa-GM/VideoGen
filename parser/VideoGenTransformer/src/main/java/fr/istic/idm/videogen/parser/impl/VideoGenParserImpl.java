package fr.istic.idm.videogen.parser.impl;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.idm.videogen.mapper.MediaMapper;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;
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

        //TODO refactor useless total/alternatives/optionales calculation ...
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

        List<List<ParsedMedia>> parsedMediaLists = generateMediaLists(total);
        for (int j = 0; j < total; j++) {
            for (int i = 0; i < optionals.size(); i++) {
                double optionalIndex = Math.pow(2, (i + 1));
                List<ParsedMedia> p = parsedMediaLists.get(j);
                if (j % optionalIndex >= optionalIndex / 2) {
                    List<ParsedMedia> parsedMedias = filter(p, MediaType.OPTIONAL);
                    parsedMedias.get(i).setActive(true);
                }
            }
            for (int k = 0; k < totalAlternativeMedia; k++) {
                List<ParsedMedia> p = parsedMediaLists.get(j);
                List<ParsedMedia> parsedMedias = filter(p, MediaType.ALTERNATIVE);
                ParsedMedia parsedMedia = parsedMedias.get(k);
                int previousIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getIndex() * totalOptionals;
                int currentIndex = parsedMedia.getPreviousAlternatives() * (parsedMedia.getIndex() + 1) * totalOptionals;
                int nextIndex = parsedMedia.getPreviousAlternatives() * parsedMedia.getTotalAlternative() * totalOptionals;
                if (j % nextIndex < currentIndex && j % nextIndex >= previousIndex) {
                    parsedMedia.setActive(true);
                }
            }
        }

        return parsedMediaLists;
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
}

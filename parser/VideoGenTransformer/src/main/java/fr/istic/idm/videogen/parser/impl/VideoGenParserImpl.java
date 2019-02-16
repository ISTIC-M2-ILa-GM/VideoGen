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

        List<AlternativesMedia> alternativesMedias = videoGeneratorModel.getMedias().stream()
                .filter(m -> m instanceof AlternativesMedia).map(m -> (AlternativesMedia) m)
                .collect(Collectors.toList());
        List<OptionalMedia> optionals = videoGeneratorModel.getMedias().stream().filter(m -> m instanceof OptionalMedia)
                .map(m -> (OptionalMedia) m).collect(Collectors.toList());
        int alternatives = alternativesMedias.size();
        int totalAlternatives = alternativesMedias.stream().map(m -> m.getMedias().size()).reduce((i1, i2) -> i1 + i2).orElse(0);
        int totalOptionals = (int) Math.pow(2, optionals.size());
        int occurence = totalOptionals * (totalAlternatives + 1);

        List<List<ParsedMedia>> parsedMediaLists = generateOccurence(occurence);
        double totalOptionalFactor = Math.pow(2, totalOptionals);
        for (int i = 0; i < optionals.size(); i++) {
            double optionalIndex = Math.pow(2, (i + 1));
            for (int j = 0; j < occurence; j++) {
                if (j % optionalIndex >= optionalIndex / 2) {
                    List<ParsedMedia> parsedMedias = parsedMediaLists.get(j).stream()
                            .filter(m -> MediaType.OPTIONAL.equals(m.getType())).collect(Collectors.toList());
                    parsedMedias.get(i).setActive(true);
                }
//				for (int k = 0; k < totalAlternatives; k++) {
//					List<ParsedMedia> parsedMedias = parsedMediaList.getParsedMedias().stream()
//							.filter(m -> "AlternativesMedia".equals(m.getType())).collect(Collectors.toList());
//					ParsedMedia parsedMedia = parsedMedias.get(k);
//					double altFactor = totalOptionalFactor * Math.pow(parsedMedia.getTotalAlternative(), k + 1);
//					if (j % altFactor >= altFactor / parsedMedia.getTotalAlternative()) {
//						parsedMedia.setActive(true);
//					}
//				}
            }
        }

        return parsedMediaLists;
    }

    private List<List<ParsedMedia>> generateOccurence(int occurence) {
        List<List<ParsedMedia>> parsedMediaLists = new ArrayList<>();
        for (int i = 0; i < occurence; i++) {
            parsedMediaLists.add(mediaMapper.toParsedMedias(videoGeneratorModel.getMedias()));
        }
        return parsedMediaLists;
    }
}

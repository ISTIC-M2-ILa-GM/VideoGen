package fr.istic.idm.videogen.parser.impl;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.MandatoryMedia;
import fr.istic.idm.videogen.generated.videoGen.Media;
import fr.istic.idm.videogen.generated.videoGen.MediaDescription;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.generated.videoGen.VideoGeneratorModel;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;
import fr.istic.idm.videogen.model.ParsedMediaList;
import fr.istic.idm.videogen.parser.VideoGenParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VideoGenParserImpl implements VideoGenParser {

    private VideoGeneratorModel videoGeneratorModel;

    public VideoGenParserImpl(VideoGeneratorModel videoGeneratorModel) {
        this.videoGeneratorModel = videoGeneratorModel;
    }

    public List<ParsedMediaList> parse() {
        List<ParsedMediaList> parsedMediaLists = new ArrayList<>();

        List<AlternativesMedia> alternativesMedias = videoGeneratorModel.getMedias().stream()
                .filter(m -> m instanceof AlternativesMedia).map(m -> (AlternativesMedia) m)
                .collect(Collectors.toList());
        List<OptionalMedia> optionals = videoGeneratorModel.getMedias().stream().filter(m -> m instanceof OptionalMedia)
                .map(m -> (OptionalMedia) m).collect(Collectors.toList());
        int alternatives = alternativesMedias.size();
        int totalAlternatives = alternativesMedias.stream().map(m -> m.getMedias().size()).reduce((i1, i2) -> i1 + i2).orElse(0);
        int totalOptionals = (int) Math.pow(2, optionals.size());
        int occurence = totalOptionals * (totalAlternatives + 1);

        for (int i = 0; i < occurence; i++) {
            ParsedMediaList parsedMediaList = new ParsedMediaList();
            for (Media m : videoGeneratorModel.getMedias()) {
                parsedMediaList.getParsedMedias().addAll(parsedMedia(m));
            }
            parsedMediaLists.add(parsedMediaList);
        }
        double totalOptionalFactor = Math.pow(2, totalOptionals);
        for (int i = 0; i < optionals.size(); i++) {
            double diff = Math.pow(2, (i + 1));
            for (int j = 0; j < occurence; j++) {
                ParsedMediaList parsedMediaList = parsedMediaLists.get(j); // voir nb d'elements
                if (j % diff >= diff / 2) {
                    List<ParsedMedia> parsedMedias = parsedMediaList.getParsedMedias().stream()
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

    private List<ParsedMedia> parsedMedia(Media m) {
        List<ParsedMedia> parsedMedias = new ArrayList<>();
        if (m instanceof MandatoryMedia) {
            MandatoryMedia mandatoryMedia = (MandatoryMedia) m;
            ParsedMedia parsedMedia = new ParsedMedia();
            parsedMedia.setActive(true);
            parsedMedia.setFileName(mandatoryMedia.getDescription().getLocation());
            parsedMedia.setType(MediaType.MANDATORY);
            parsedMedias.add(parsedMedia);
        }
        if (m instanceof OptionalMedia) {
            OptionalMedia optionalMedia = (OptionalMedia) m;
            ParsedMedia parsedMedia = new ParsedMedia();
            parsedMedia.setActive(false);
            parsedMedia.setFileName(optionalMedia.getDescription().getLocation());
            parsedMedia.setType(MediaType.OPTIONAL);
            parsedMedias.add(parsedMedia);
        }
        if (m instanceof AlternativesMedia) {
            AlternativesMedia alternativesMedia = (AlternativesMedia) m;
            int totalAlternative = alternativesMedia.getMedias().size();
            for (MediaDescription media : alternativesMedia.getMedias()) {
                ParsedMedia parsedMedia = new ParsedMedia();
                parsedMedia.setActive(false);
                parsedMedia.setFileName(media.getLocation());
                parsedMedia.setType(MediaType.ALTERNATIVE);
                parsedMedia.setTotalAlternative(totalAlternative);
                parsedMedias.add(parsedMedia);
            }
        }
        return parsedMedias;
    }
}

package fr.istic.idm.videogen.mapper.impl;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.MandatoryMedia;
import fr.istic.idm.videogen.generated.videoGen.Media;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.mapper.MediaMapper;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class MediaMapperImpl implements MediaMapper {

    private PreviousAlternatives previousAlternative = new PreviousAlternatives();

    @Override
    public List<ParsedMedia> toParsedMedia(Media media) {
        List<ParsedMedia> parsedMedias = new ArrayList<>();
        if (media instanceof MandatoryMedia) {
            parsedMedias.add(mandatoryMediaToParsedMedia((MandatoryMedia) media));
        }
        if (media instanceof OptionalMedia) {
            parsedMedias.add(optionalMediaToParsedMedia((OptionalMedia) media));
        }
        if (media instanceof AlternativesMedia) {
            List<ParsedMedia> parsedMedia = alternativeMediaToParsedMedia((AlternativesMedia) media);
            previousAlternative.add(parsedMedia.size());
            parsedMedias.addAll(parsedMedia);
        }
        return parsedMedias;
    }

    private ParsedMedia mandatoryMediaToParsedMedia(MandatoryMedia media) {
        return media != null ?
                ParsedMedia.builder()
                        .type(MediaType.MANDATORY)
                        .fileName(media.getDescription().getLocation())
                        .active(true)
                        .build()
                : null;
    }

    private ParsedMedia optionalMediaToParsedMedia(OptionalMedia media) {
        return media != null ?
                ParsedMedia.builder()
                        .type(MediaType.OPTIONAL)
                        .fileName(media.getDescription().getLocation())
                        .active(false)
                        .build()
                : null;
    }

    private List<ParsedMedia> alternativeMediaToParsedMedia(AlternativesMedia media) {
        if (media == null) {
            return new ArrayList<>();
        }
        int size = media.getMedias().size();
        List<ParsedMedia> parsedMedia = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            parsedMedia.add(ParsedMedia.builder()
                    .active(false)
                    .fileName(media.getMedias().get(i).getLocation())
                    .type(MediaType.ALTERNATIVE)
                    .previousAlternatives(previousAlternative.getTotal())
                    .index(i)
                    .totalAlternative(size)
                    .build());
        }
        return parsedMedia;
    }

    @Override
    public List<ParsedMedia> toParsedMedias(List<Media> media) {
        if (media == null) {
            return new ArrayList<>();
        }
        List<ParsedMedia> parsedMedia = new ArrayList<>();
        for (Media m : media) {
            parsedMedia.addAll(toParsedMedia(m));
        }
        previousAlternative = new PreviousAlternatives();
        return parsedMedia;
    }

    @Data
    private class PreviousAlternatives {

        private int total = 1;

        public void add(int n) {
            total *= n;
        }
    }
}

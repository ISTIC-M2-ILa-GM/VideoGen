package fr.istic.idm.videogen.mapper.impl;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.MandatoryMedia;
import fr.istic.idm.videogen.generated.videoGen.Media;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.mapper.MediaMapper;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MediaMapperImpl implements MediaMapper {

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
            parsedMedias.addAll(alternativeMediaToParsedMedia((AlternativesMedia) media));
        }
        return parsedMedias;
    }

    @Override
    public List<ParsedMedia> toParsedMedias(List<Media> media) {
        return new ArrayList<>();
    }

    private ParsedMedia mandatoryMediaToParsedMedia(MandatoryMedia media) {
        return media != null ? ParsedMedia.builder()
                .type(MediaType.MANDATORY)
                .fileName(media.getDescription().getLocation())
                .active(true)
                .build() : null;
    }

    private ParsedMedia optionalMediaToParsedMedia(OptionalMedia media) {
        return media != null ? ParsedMedia.builder()
                .type(MediaType.OPTIONAL)
                .fileName(media.getDescription().getLocation())
                .active(false)
                .build() : null;
    }

    private List<ParsedMedia> alternativeMediaToParsedMedia(AlternativesMedia media) {
        if (media == null) {
            return null;
        }
        int size = media.getMedias().size();
        return media.getMedias().stream().map(m ->
                ParsedMedia.builder()
                        .active(false)
                        .fileName(m.getLocation())
                        .type(MediaType.ALTERNATIVE)
                        .totalAlternative(size)
                        .build())
                .collect(Collectors.toList());
    }
}

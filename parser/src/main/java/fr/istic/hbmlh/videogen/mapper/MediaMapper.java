package fr.istic.hbmlh.videogen.mapper;

import fr.istic.hbmlh.videogen.generated.videoGen.Media;
import fr.istic.hbmlh.videogen.model.ParsedMedia;

import java.util.List;

public interface MediaMapper {

    List<ParsedMedia> toParsedMedias(List<Media> media);
}

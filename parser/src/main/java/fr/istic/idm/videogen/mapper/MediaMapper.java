package fr.istic.idm.videogen.mapper;

import fr.istic.idm.videogen.generated.videoGen.Media;
import fr.istic.idm.videogen.model.ParsedMedia;

import java.util.List;

public interface MediaMapper {

    List<ParsedMedia> toParsedMedias(List<Media> media);
}

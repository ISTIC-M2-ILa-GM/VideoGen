package fr.istic.hbmlh.videogen.analyse;

import fr.istic.hbmlh.videogen.model.AnalysedMedias;
import fr.istic.hbmlh.videogen.model.ParsedMedia;

import java.util.List;

public interface VideoGenAnalyse {
  AnalysedMedias analyse(List<List<ParsedMedia>> parsedMediaList);
}

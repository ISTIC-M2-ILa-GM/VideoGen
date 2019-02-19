package fr.istic.hbmlh.videogen.analyse.impl;

import fr.istic.hbmlh.videogen.analyse.VideoGenAnalyse;
import fr.istic.hbmlh.videogen.model.AnalysedMedias;
import fr.istic.hbmlh.videogen.model.Pair;
import fr.istic.hbmlh.videogen.model.ParsedMedia;
import fr.istic.hbmlh.videogen.wrapper.FileWrapper;
import fr.istic.hbmlh.videogen.wrapper.LengthWrapper;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class VideoGenAnalyseImpl implements VideoGenAnalyse {

  private FileWrapper fileWrapper;

  private LengthWrapper lengthWrapper;

  @Override
  public AnalysedMedias analyse(List<List<ParsedMedia>> parsedMediaList) {

    List<Pair<Long, List<ParsedMedia>>> sizes = retrieveSizes(parsedMediaList);

    List<Pair<Long, List<ParsedMedia>>> lengths = retrieveLengths(parsedMediaList);

    return AnalysedMedias.builder()
      .longestSize(retrieveLongest(sizes))
      .shortestSize(retrieveShortest(sizes))
      .mediumSize(retrieveMedium(sizes))
      .longestLength(retrieveLongest(lengths))
      .shortestLength(retrieveShortest(lengths))
      .mediumLength(retrieveMedium(lengths))
      .build();
  }

  private List<Pair<Long, List<ParsedMedia>>> retrieveSizes(List<List<ParsedMedia>> parsedMediaList) {
    return parsedMediaList.stream().map(ps -> {
      long size = 0;
      for (ParsedMedia p : ps) {
        if (p.isActive()) {
          size += fileWrapper.sizeOf(p.getFileName());
        }
      }
      return new Pair<>(size, ps);
    }).collect(Collectors.toList());
  }

  private List<Pair<Long, List<ParsedMedia>>> retrieveLengths(List<List<ParsedMedia>> parsedMediaList) {
    return parsedMediaList.stream().map(ps -> {
      long size = 0;
      for (ParsedMedia p : ps) {
        if (p.isActive()) {
          size += lengthWrapper.retrieveLength(p.getFileName());
        }
      }
      return new Pair<>(size, ps);
    }).collect(Collectors.toList());
  }

  private Pair<Long, List<ParsedMedia>> retrieveLongest(List<Pair<Long, List<ParsedMedia>>> sizes) {
    return sizes.stream().reduce((a, b) -> {
      if (a.getKey() > b.getKey()) {
        return a;
      } else {
        return b;
      }
    }).orElse(null);
  }

  private Pair<Long, List<ParsedMedia>> retrieveShortest(List<Pair<Long, List<ParsedMedia>>> sizes) {
    return sizes.stream().reduce((a, b) -> {
      if (a.getKey() < b.getKey()) {
        return a;
      } else {
        return b;
      }
    }).orElse(null);
  }

  private Pair<Long, List<ParsedMedia>> retrieveMedium(List<Pair<Long, List<ParsedMedia>>> sizes) {
    long mediumLength = sizes.stream().map(Pair::getKey).reduce((a, b) -> a + b).orElse(0L) / sizes.size();
    return sizes.stream().peek(s -> s.setKey(Math.abs(s.getKey() - mediumLength))).min(Comparator.comparing(Pair::getKey)).orElse(null);
  }
}

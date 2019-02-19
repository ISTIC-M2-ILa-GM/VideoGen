package fr.istic.hbmlh.videogen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysedMedias {

  private Pair<Long, List<ParsedMedia>> longestSize = new Pair<>();
  private Pair<Long, List<ParsedMedia>> shortestSize = new Pair<>();
  private Pair<Long, List<ParsedMedia>> mediumSize = new Pair<>();
  private Pair<Long, List<ParsedMedia>> longestLength = new Pair<>();
  private Pair<Long, List<ParsedMedia>> shortestLength = new Pair<>();
  private Pair<Long, List<ParsedMedia>> mediumLength = new Pair<>();
}

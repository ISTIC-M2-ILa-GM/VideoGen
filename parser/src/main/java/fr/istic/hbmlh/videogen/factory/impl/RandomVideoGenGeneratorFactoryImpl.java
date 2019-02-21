package fr.istic.hbmlh.videogen.factory.impl;

import fr.istic.hbmlh.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.hbmlh.videogen.factory.VideoGenParserFactory;
import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;
import fr.istic.hbmlh.videogen.randomizer.impl.RandomVideoGenGeneratorImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RandomVideoGenGeneratorFactoryImpl implements RandomVideoGenGeneratorFactory {

  private VideoGenParserFactory videoGenParserFactory;

  @Override
  public RandomVideoGenGenerator create(String videoGenFilePath) {
    return new RandomVideoGenGeneratorImpl(videoGenParserFactory.create(videoGenFilePath));
  }
}

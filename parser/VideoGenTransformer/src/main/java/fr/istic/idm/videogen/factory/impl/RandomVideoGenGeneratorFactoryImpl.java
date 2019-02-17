package fr.istic.idm.videogen.factory.impl;

import fr.istic.idm.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.idm.videogen.factory.VideoGenParserFactory;
import fr.istic.idm.videogen.randomizer.RandomVideoGenGenerator;
import fr.istic.idm.videogen.randomizer.impl.RandomVideoGenGeneratorImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RandomVideoGenGeneratorFactoryImpl implements RandomVideoGenGeneratorFactory {

    private VideoGenParserFactory videoGenParserFactory;

    @Override
    public RandomVideoGenGenerator create(String videoGenFilePath) {
        return new RandomVideoGenGeneratorImpl(videoGenParserFactory.create(videoGenFilePath));
    }
}

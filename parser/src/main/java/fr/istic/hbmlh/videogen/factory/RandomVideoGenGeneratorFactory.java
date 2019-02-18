package fr.istic.hbmlh.videogen.factory;

import fr.istic.hbmlh.videogen.randomizer.RandomVideoGenGenerator;

public interface RandomVideoGenGeneratorFactory {
    RandomVideoGenGenerator create(String videoGenFilePath);
}

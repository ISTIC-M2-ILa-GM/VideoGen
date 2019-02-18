package fr.istic.idm.videogen.factory;

import fr.istic.idm.videogen.randomizer.RandomVideoGenGenerator;

public interface RandomVideoGenGeneratorFactory {
    RandomVideoGenGenerator create(String videoGenFilePath);
}

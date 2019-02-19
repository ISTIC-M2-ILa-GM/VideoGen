package fr.istic.hbmlh.videogen.factory;

import fr.istic.hbmlh.videogen.wrapper.ProcessBuilderWrapper;

public interface ProcessBuilderFactory {

    ProcessBuilderWrapper create(String directory, String... command);
}

package fr.istic.hbmlh.videogen.factory.impl;

import fr.istic.hbmlh.videogen.exception.VideoGenException;
import fr.istic.hbmlh.videogen.factory.ProcessBuilderFactory;
import fr.istic.hbmlh.videogen.wrapper.ProcessBuilderWrapper;
import fr.istic.hbmlh.videogen.wrapper.impl.ProcessBuilderWrapperImpl;
import lombok.AllArgsConstructor;

import java.nio.file.Paths;

@AllArgsConstructor
public class ProcessBuilderFactoryImpl implements ProcessBuilderFactory {

    private static final String ERROR_DIRECTORY = "ProcessBuilderFactoryImpl: The directory needs to exists %s";
    private static final String ERROR_COMMAND = "ProcessBuilderFactoryImpl: No command";

    @Override
    public ProcessBuilderWrapper create(String directory, String... command) {
        if (directory == null || !Paths.get(directory).toFile().isDirectory()) {
            throw new VideoGenException(String.format(ERROR_DIRECTORY, directory));
        }
        if (command == null || command.length == 0 || command[0].isEmpty()) {
            throw new VideoGenException(ERROR_COMMAND);
        }
        return new ProcessBuilderWrapperImpl(command);
    }
}

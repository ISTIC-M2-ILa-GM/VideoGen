package fr.istic.hbmlh.videogen.wrapper.impl;

import fr.istic.hbmlh.videogen.exception.VideoGenException;
import fr.istic.hbmlh.videogen.wrapper.ProcessBuilderWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProcessBuilderWrapperImpl implements ProcessBuilderWrapper {

    private ProcessBuilder processBuilder;

    public ProcessBuilderWrapperImpl(String... command) {
        processBuilder = new ProcessBuilder(command);
    }

    @Override
    public Process start() {
        try {
            return processBuilder.start();
        } catch (IOException e) {
            throw new VideoGenException("ProcessBuilderWrapperImpl: Process start failed", e);
        }
    }

    @Override
    public File directory() {
        return processBuilder.directory();
    }

    @Override
    public List<String> command() {
        return processBuilder.command();
    }

    @Override
    public ProcessBuilderWrapper directory(String filePath) {
        processBuilder.directory(new File(filePath));
        return this;
    }
}

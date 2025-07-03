package com.github.zabbum.pimanager.services;

import com.github.zabbum.pimanager.configuration.Config;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Path rootDir;

    public Path getPublicDir() {
        return rootDir.resolve("public").toAbsolutePath();
    }

    public StorageService(Config config) {
        this.rootDir = Paths.get(config.getRootDir());
    }

    public Stream<Path> getPathContent(Path path) {
        try {
            return Files.list(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read files from path: " + path, e);
        }
    }
}

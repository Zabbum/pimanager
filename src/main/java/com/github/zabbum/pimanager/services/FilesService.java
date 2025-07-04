package com.github.zabbum.pimanager.services;

import com.github.zabbum.pimanager.configuration.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/** Service of files. */
@Service
public class FilesService {

  private final Path rootDir;

  public Path getPublicDir() {
    return rootDir.resolve("public").toAbsolutePath();
  }

  /**
   * Constructor of service with config.
   *
   * @param config Configuration.
   */
  public FilesService(Config config) {
    this.rootDir = Paths.get(config.getRootDir());
  }

  /**
   * Get content of provided path.
   *
   * @param path Path of dir which content will be listed.
   * @return Stream of content of dir.
   */
  public Stream<Path> getPathContent(Path path) {
    try {
      return Files.list(path);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read files from path: " + path, e);
    }
  }
}

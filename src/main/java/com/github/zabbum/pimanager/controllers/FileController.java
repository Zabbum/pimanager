package com.github.zabbum.pimanager.controllers;

import com.github.zabbum.pimanager.model.FilesDirs;
import com.github.zabbum.pimanager.services.FilesService;
import java.nio.file.Path;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** REST API controller for files operations. */
@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class FileController {
  private final FilesService filesService;

  /**
   * Creator of controller.
   *
   * @param filesService Storage service.
   */
  public FileController(FilesService filesService) {
    this.filesService = filesService;
  }

  /**
   * GET on /api/files. Get list of the files in provided directory.
   *
   * @param pathString Directory which content will be listed.
   * @return Files, dirs and current path.
   */
  @GetMapping("")
  public FilesDirs listFiles(@RequestParam(name = "path", required = false) String pathString) {
    // Create path from provided path
    Path currentDir = filesService.getPublicDir();
    if (pathString != null) {
      // If path begins with / cut it
      if (pathString.startsWith("/")) {
        pathString = pathString.substring(1);
      }
      currentDir = currentDir.resolve(pathString);
    }

    // Retrieve files inside current dir
    List<Path> allFiles = filesService.getPathContent(currentDir).toList();

    // Iterate through each file and check is it file or dir.
    FilesDirs filesDirs = new FilesDirs(filesService.relativize(currentDir));
    for (Path filePath : allFiles) {
      if (filePath.toFile().isDirectory()) {
        filesDirs.getDirs().add(filesService.relativize(filePath));
      } else {
        filesDirs.getFiles().add(filesService.relativize(filePath));
      }
    }

    return filesDirs;
  }
}

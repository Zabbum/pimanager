package com.github.zabbum.pimanager.controllers;

import com.github.zabbum.pimanager.model.FilesDirs;
import com.github.zabbum.pimanager.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;

@RestController
@Slf4j
public class FileController {
    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/listFiles")
    public FilesDirs listFiles(@RequestParam(name = "path", required = false) String pathString) {
        // Create path from provided path
        Path currentDir = storageService.getPublicDir();
        if (pathString != null) {
            // If path begins with / cut it
            if (pathString.startsWith("/")) {
                pathString = pathString.substring(1);
            }
            currentDir = currentDir.resolve(pathString);
        }

        // Retrieve files inside current dir
        List<Path> allFiles = storageService.getPathContent(currentDir).toList();

        // Iterate through each file and check is it file or dir.
        FilesDirs filesDirs = new FilesDirs(storageService.getPublicDir().relativize(currentDir));
        for (Path filePath : allFiles) {
            if (filePath.toFile().isDirectory()) {
                filesDirs.getDirs().add(filePath);
            } else {
                filesDirs.getFiles().add(filePath);
            }
        }

        return filesDirs;
    }
}

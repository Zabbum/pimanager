package com.github.zabbum.pimanager.model;

import lombok.Data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
public class FilesDirs {
    private String currentDir;
    private List<Path> files;
    private List<Path> dirs;

    public FilesDirs(Path currentDirRelative) {
        this.currentDir = "/" + currentDirRelative.toString();
        this.files = new ArrayList<>();
        this.dirs = new ArrayList<>();
    }
}

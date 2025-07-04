package com.github.zabbum.pimanager.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/** Class to store response of files and dirs in current dir. */
@Data
public class FilesDirs {
  private String currentDir;
  private List<Path> files;
  private List<Path> dirs;

  /**
   * Constructor with current path.
   *
   * @param currentDirRelative Path of current dir.
   */
  public FilesDirs(Path currentDirRelative) {
    this.currentDir = "/" + currentDirRelative.toString();
    this.files = new ArrayList<>();
    this.dirs = new ArrayList<>();
  }
}

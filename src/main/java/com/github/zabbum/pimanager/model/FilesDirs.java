package com.github.zabbum.pimanager.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/** Class to store response of files and dirs in current dir. */
@Data
public class FilesDirs {
  private String currentDir;
  private List<String> files;
  private List<String> dirs;

  /**
   * Constructor with current path.
   *
   * @param currentDirRelative Path of current dir.
   */
  public FilesDirs(String currentDirRelative) {
    this.currentDir = currentDirRelative;
    this.files = new ArrayList<>();
    this.dirs = new ArrayList<>();
  }
}

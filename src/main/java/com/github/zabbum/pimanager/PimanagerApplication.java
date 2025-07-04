package com.github.zabbum.pimanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main class of application. */
@SpringBootApplication
public class PimanagerApplication {

  /**
   * Main method for execution.
   *
   * @param args CLI args.
   */
  public static void main(String[] args) {
    SpringApplication.run(PimanagerApplication.class, args);
  }
}

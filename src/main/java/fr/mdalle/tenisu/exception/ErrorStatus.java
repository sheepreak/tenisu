package fr.mdalle.tenisu.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorStatus {
  NOT_FOUND(HttpStatus.NOT_FOUND, "No player found for id %s"),
  NUMBER_FORMAT(HttpStatus.BAD_REQUEST, "Format is not accepted for parameter \"%s\""),
  COMPUTATION_ISSUE(HttpStatus.INTERNAL_SERVER_ERROR, "There was an issue with computing %s"),
  NO_PLAYERS(HttpStatus.INTERNAL_SERVER_ERROR, "There are no players to compute statistics from");

  private final HttpStatus httpStatus;
  private final String message;

  ErrorStatus(HttpStatus httpStatus, String message) {
    this.httpStatus = httpStatus;
    this.message = message;
  }
}

package fr.mdalle.tenisu.exception;

import lombok.Getter;

@Getter
public class TenisuException extends RuntimeException {

  private final ErrorStatus errorStatus;

  public TenisuException(ErrorStatus errorStatus, String... messageVariables) {
    super(String.format(errorStatus.getMessage(), messageVariables));
    this.errorStatus = errorStatus;
  }
}

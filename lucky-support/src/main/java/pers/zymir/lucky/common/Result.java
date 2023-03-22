package pers.zymir.lucky.common;

import lombok.Data;

@Data
public class Result {
  private boolean success;

  private String message;

  private Result(boolean success, String message) {
    this.message = message;
    this.success = success;
  }

  public static Result success(String message) {
    return new Result(true, message);
  }

  public static Result success() {
    return success(null);
  }

  public static Result failure(String message) {
    return new Result(false, message);
  }
}

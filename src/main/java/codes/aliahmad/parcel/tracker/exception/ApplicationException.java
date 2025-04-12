package codes.aliahmad.parcel.tracker.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException
{
  private final int code;

  public ApplicationException(int code, String message, Throwable throwable)
  {
    super(message, throwable);
    this.code = code;
  }

  public ApplicationException(int code, String message)
  {
    this(code, message, null);
  }

}

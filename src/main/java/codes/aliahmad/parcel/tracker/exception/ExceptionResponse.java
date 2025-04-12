package codes.aliahmad.parcel.tracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse
{
  private int code;
  private String message;

  public static ExceptionResponse of(int errorCode, String errorMessage)
  {
    ExceptionResponse response = new ExceptionResponse();
    response.setCode(errorCode);
    response.setMessage(errorMessage);

    return response;
  }

  public static ExceptionResponse of(ApplicationException exception)
  {
    return of(exception.getCode(), exception.getMessage());
  }
}

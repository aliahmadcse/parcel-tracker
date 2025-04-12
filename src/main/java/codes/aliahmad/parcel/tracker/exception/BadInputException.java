package codes.aliahmad.parcel.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadInputException extends ApplicationException
{
  public BadInputException(int code, String message)
  {
    super(code, message);
  }
}

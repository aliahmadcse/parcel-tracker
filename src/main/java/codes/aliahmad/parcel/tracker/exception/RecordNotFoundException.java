package codes.aliahmad.parcel.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends ApplicationException
{
  public RecordNotFoundException(int code, String message)
  {
    super(code, message);
  }
}

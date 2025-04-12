package codes.aliahmad.parcel.tracker.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage
{
  public static final String CLIENT_NOT_FOUND = "No client found against provided email";
}

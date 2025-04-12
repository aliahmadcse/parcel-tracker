package codes.aliahmad.parcel.tracker.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage
{
  public static final String CLIENT_NOT_FOUND = "No client found against provided email";
  public static final String SENDER_NOT_FOUND = "Sender not found";
  public static final String RECEIVER_NOT_FOUND = "Receiver not found";
  public static final String EMAIL_ALREADY_EXIST = "Client already exists with the provided email";
  public static final String INVALID_PARCEL_ID = "Invalid parcel id";
}

package codes.aliahmad.parcel.tracker.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCode
{
  public static final int UNKNOWN_ERROR_OCCURRED = 60001;
  public static final int BAD_INPUT_PARAMETER = 60002;
  public static final int ENDPOINT_NOT_FOUND = 60003;
  public static final int HTTP_METHOD_NOT_SUPPORTED = 60004;
  public static final int RECORD_NOT_FOUND = 60005;
  public static final int INVALID_ENUM_VALUE = 60007;
  public static final int INVALID_MONGO_ID = 60008;

  public static final int UNABLE_TO_MAP_OBJECT = 60009;
}

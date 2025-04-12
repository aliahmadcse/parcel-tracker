package codes.aliahmad.parcel.tracker.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValueUtil
{
  public static boolean isPositiveNumber(Integer value)
  {
    return null != value && value > 0;
  }
}

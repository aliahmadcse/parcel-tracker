package codes.aliahmad.parcel.tracker.unit.dto;

import codes.aliahmad.parcel.tracker.dto.ParcelStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParcelStatusTest
{
  @Test
  public void testParcelGetStatus()
  {
    ParcelStatus parcelStatus = ParcelStatus.getStatus("created");
    Assertions.assertEquals(ParcelStatus.CREATED, parcelStatus);
  }

  @Test
  public void testGetStatusReturnsNullForInvalidStatus()
  {
    ParcelStatus parcelStatus = ParcelStatus.getStatus("invalid");
    Assertions.assertNull(parcelStatus);
  }


}

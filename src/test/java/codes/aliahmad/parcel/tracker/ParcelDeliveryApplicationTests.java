package codes.aliahmad.parcel.tracker;

import codes.aliahmad.parcel.tracker.base.BaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ParcelDeliveryApplicationTests extends BaseIntegrationTest
{

  @Test
  void contextLoads()
  {
    ParcelTrackerApplication.main(new String[]{});
    Assertions.assertTrue(true);
  }

}

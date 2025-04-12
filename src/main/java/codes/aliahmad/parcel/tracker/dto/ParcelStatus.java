package codes.aliahmad.parcel.tracker.dto;

public enum ParcelStatus
{
  CREATED, IN_TRANSIT, DELIVERED, CANCELLED;


  public static ParcelStatus getStatus(String status)
  {
    for (ParcelStatus parcelStatus : ParcelStatus.values())
    {
      if (parcelStatus.name().equalsIgnoreCase(status.toUpperCase()))
      {
        return parcelStatus;
      }
    }
    return null;
  }
}

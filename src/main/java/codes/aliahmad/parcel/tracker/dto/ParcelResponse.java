package codes.aliahmad.parcel.tracker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ParcelResponse
{
  private String parcelId;
  private String referenceCode;
  private String senderEmail;
  private String receiverEmail;
  private String deliveryAddress;
  private ParcelStatus parcelStatus;
  private Map<ParcelStatus, Instant> statusLog;
  private Instant createdAt;
}

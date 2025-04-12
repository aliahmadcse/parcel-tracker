package codes.aliahmad.parcel.tracker.dto.request;


import codes.aliahmad.parcel.tracker.dto.ParcelStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ParcelSearchRequest
{
  private ParcelStatus currentStatus;
  private String senderEmail;
  private Instant creationDateStart;
  private Instant creationDateEnd;
}

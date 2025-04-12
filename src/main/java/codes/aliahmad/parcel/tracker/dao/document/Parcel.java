package codes.aliahmad.parcel.tracker.dao.document;

import codes.aliahmad.parcel.tracker.dto.ParcelStatus;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Document(collection = "parcel")
@Getter
@Setter
public class Parcel
{
  @Id
  private ObjectId id;

  private UUID referenceCode;
  private String senderEmail;
  private String receiverEmail;
  private String deliveryAddress;
  private ParcelStatus status;
  private Map<ParcelStatus, Instant> statusLog;
  private Instant createdAt;
}

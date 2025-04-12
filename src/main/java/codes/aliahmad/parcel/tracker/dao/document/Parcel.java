package codes.aliahmad.parcel.tracker.dao.document;

import codes.aliahmad.parcel.tracker.dto.ParcelStatus;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "parcel")
@Getter
@Setter
public class Parcel
{
  @Id
  private ObjectId id;

  private UUID referenceCode;
  private Client sender;
  private Client receiver;
  private String deliveryAddress;
  private ParcelStatus status;
  private Instant createdAt;
}

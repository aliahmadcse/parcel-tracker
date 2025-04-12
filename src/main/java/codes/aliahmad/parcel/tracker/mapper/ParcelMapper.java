package codes.aliahmad.parcel.tracker.mapper;


import codes.aliahmad.parcel.tracker.dao.document.Parcel;
import codes.aliahmad.parcel.tracker.dto.ParcelResponse;
import codes.aliahmad.parcel.tracker.dto.ParcelStatus;
import codes.aliahmad.parcel.tracker.dto.request.ParcelRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParcelMapper
{
  public static Parcel toParcel(ParcelRequest parcelRequest)
  {
    Parcel parcel = new Parcel();
    parcel.setReferenceCode(UUID.randomUUID());
    parcel.setSenderEmail(parcelRequest.senderEmail());
    parcel.setReceiverEmail(parcelRequest.receiverEmail());
    parcel.setDeliveryAddress(parcelRequest.deliveryAddress());
    parcel.setStatus(ParcelStatus.CREATED);
    parcel.setStatusLog(Map.of(ParcelStatus.CREATED, Instant.now()));
    parcel.setCreatedAt(Instant.now());
    return parcel;
  }

  public static ParcelResponse toParcelResponse(Parcel parcel)
  {
    ParcelResponse parcelResponse = new ParcelResponse();
    parcelResponse.setParcelId(parcel.getId().toString());
    parcelResponse.setReferenceCode(parcel.getReferenceCode().toString());
    parcelResponse.setSenderEmail(parcel.getSenderEmail());
    parcelResponse.setReceiverEmail(parcel.getReceiverEmail());
    parcelResponse.setDeliveryAddress(parcel.getDeliveryAddress());
    parcelResponse.setParcelStatus(parcel.getStatus());
    parcelResponse.setStatusLog(parcel.getStatusLog());
    parcelResponse.setCreatedAt(parcel.getCreatedAt());

    return parcelResponse;
  }
}

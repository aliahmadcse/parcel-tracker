package codes.aliahmad.parcel.tracker.service;

import codes.aliahmad.parcel.tracker.dto.ParcelResponse;
import codes.aliahmad.parcel.tracker.dto.request.ParcelRequest;
import codes.aliahmad.parcel.tracker.dto.request.ParcelSearchRequest;
import codes.aliahmad.parcel.tracker.dto.request.ParcelStatusUpdateRequest;

import java.util.List;

public interface ParcelService
{
  ParcelResponse createParcel(ParcelRequest parcelRequest);

  ParcelResponse updateParcelStatus(ParcelStatusUpdateRequest parcelStatusUpdateRequest);

  List<ParcelResponse> searchParcels(ParcelSearchRequest parcelSearchRequest);
}

package codes.aliahmad.parcel.tracker.dto.request;

import codes.aliahmad.parcel.tracker.dto.ParcelStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParcelStatusUpdateRequest(@NotBlank String parcelId, @NotNull ParcelStatus status)
{

}

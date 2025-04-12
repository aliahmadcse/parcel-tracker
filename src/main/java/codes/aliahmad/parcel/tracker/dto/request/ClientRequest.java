package codes.aliahmad.parcel.tracker.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ClientRequest(@NotBlank String firstName,
                            @NotBlank String lastName,
                            @NotBlank String email,
                            @NotBlank String phone)
{
}


package codes.aliahmad.parcel.tracker.controller;


import codes.aliahmad.parcel.tracker.dto.ParcelResponse;
import codes.aliahmad.parcel.tracker.dto.request.ParcelRequest;
import codes.aliahmad.parcel.tracker.dto.request.ParcelSearchRequest;
import codes.aliahmad.parcel.tracker.dto.request.ParcelStatusUpdateRequest;
import codes.aliahmad.parcel.tracker.service.ParcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parcel")
@Tag(name = "Parcel Controller", description = "Parcel Related APIs")
public class ParcelController
{
  private final ParcelService parcelService;

  @Operation(
          summary = "Create a new parcel",
          description = "Creates a parcel with the given details and returns the created parcel response.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "Parcel creation request",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = ParcelRequest.class)
                  )
          ),
          responses = {
                  @ApiResponse(
                          responseCode = "201",
                          description = "Parcel successfully created",
                          content = @Content(schema = @Schema(implementation = ParcelResponse.class))
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Invalid request data",
                          content = @Content
                  ),
                  @ApiResponse(
                          responseCode = "500",
                          description = "Internal server error",
                          content = @Content
                  )
          }
  )
  @PostMapping
  public ResponseEntity<ParcelResponse> createParcel(@RequestBody ParcelRequest parcelRequest)
  {
    return new ResponseEntity<>(parcelService.createParcel(parcelRequest), HttpStatus.CREATED);
  }

  @Operation(
          summary = "Update parcel status",
          description = "Updates the status of a parcel based on the provided request data.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "Request payload to update parcel status",
                  required = true,
                  content = @Content(schema = @Schema(implementation = ParcelStatusUpdateRequest.class))
          ),
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Parcel status updated successfully",
                          content = @Content(schema = @Schema(implementation = ParcelResponse.class))
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Invalid request payload",
                          content = @Content
                  ),
                  @ApiResponse(
                          responseCode = "500",
                          description = "Internal server error",
                          content = @Content
                  )
          }
  )
  @PutMapping("/status/update")
  public ResponseEntity<ParcelResponse> updateParcelStatus(@RequestBody ParcelStatusUpdateRequest parcelStatusUpdateRequest)
  {
    return new ResponseEntity<>(parcelService.updateParcelStatus(parcelStatusUpdateRequest), HttpStatus.OK);
  }

  @Operation(
          summary = "Search parcels",
          description = "Search for parcels using optional filters such as status, sender name, and receiver name.",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "List of matching parcels",
                          content = @Content(schema = @Schema(implementation = ParcelResponse.class))
                  ),
                  @ApiResponse(
                          responseCode = "500",
                          description = "Internal server error",
                          content = @Content
                  )
          }
  )
  @GetMapping
  public ResponseEntity<List<ParcelResponse>> searchParcels(ParcelSearchRequest parcelSearchRequest)
  {
    return new ResponseEntity<>(parcelService.searchParcels(parcelSearchRequest), HttpStatus.OK);
  }
}

package codes.aliahmad.parcel.tracker.controller;

import codes.aliahmad.parcel.tracker.dto.request.ClientListRequest;
import codes.aliahmad.parcel.tracker.dto.request.ClientRequest;
import codes.aliahmad.parcel.tracker.dto.response.ClientListResponse;
import codes.aliahmad.parcel.tracker.dto.response.ClientResponse;
import codes.aliahmad.parcel.tracker.exception.ExceptionResponse;
import codes.aliahmad.parcel.tracker.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client")
@Tag(name = "Client", description = "Client APIs")
public class ClientController
{
  private final ClientService clientService;


  @Operation(
          summary = "Create a Client",
          description = "Use this POST API to create a Client"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "201",
                  description = "Client creation successful.",
                  content = @Content(schema = @Schema(implementation = ClientResponse.class))
          ),
          @ApiResponse(
                  responseCode = "400",
                  description = "Invalid request",
                  content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
          )
  })
  @PostMapping
  public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest)
  {
    return new ResponseEntity<>(clientService.createClient(clientRequest), HttpStatus.CREATED);
  }


  @Operation(
          summary = "Find client by email",
          description = "Retrieves a client using their email address.",
          parameters = {
                  @Parameter(
                          name = "email",
                          description = "Email address of the client",
                          required = true,
                          example = "ali@example.com"
                  )
          },
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Client found successfully",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = ClientResponse.class)
                          )
                  ),
                  @ApiResponse(
                          responseCode = "404",
                          description = "Client not found",
                          content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
                  )
          }
  )
  @GetMapping("/{email}")
  public ResponseEntity<ClientResponse> findClientByEmail(@PathVariable String email)
  {
    ClientResponse response = clientService.findByEmail(email);
    return ResponseEntity.ok(response);
  }

  @Operation(
          summary = "List clients",
          description = "Retrieves a paginated list of clients based on filter criteria.",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful retrieval",
                          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientListResponse.class))
                  ),
                  @ApiResponse(responseCode = "400",
                          description = "Invalid request",
                          content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
                  ),
                  @ApiResponse(responseCode = "500",
                          description = "Internal server error",
                          content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
                  )
          }
  )
  @GetMapping
  public ResponseEntity<ClientListResponse> listClients(ClientListRequest listRequest)
  {
    ClientListResponse response = clientService.listClients(listRequest);
    return ResponseEntity.ok(response);
  }

}

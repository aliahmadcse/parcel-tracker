package codes.aliahmad.parcel.tracker.mapper;

import codes.aliahmad.parcel.tracker.dao.document.Client;
import codes.aliahmad.parcel.tracker.dto.request.ClientRequest;
import codes.aliahmad.parcel.tracker.dto.response.ClientResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper
{
  public static Client toClient(ClientRequest clientRequest)
  {
    return Client.builder()
            .firstName(clientRequest.firstName())
            .lastName(clientRequest.lastName())
            .email(clientRequest.email())
            .phone(clientRequest.phone())
            .build();
  }

  public static ClientResponse toClientResponse(Client client)
  {
    return new ClientResponse(
            client.getFirstName(),
            client.getLastName(),
            client.getEmail(),
            client.getPhone()
    );
  }
}

package codes.aliahmad.parcel.tracker.service;

import codes.aliahmad.parcel.tracker.dto.request.ClientListRequest;
import codes.aliahmad.parcel.tracker.dto.request.ClientRequest;
import codes.aliahmad.parcel.tracker.dto.response.ClientListResponse;
import codes.aliahmad.parcel.tracker.dto.response.ClientResponse;

public interface ClientService
{
  ClientResponse createClient(ClientRequest clientRequest);

  ClientResponse findByEmail(String email);

  ClientListResponse listClients(ClientListRequest listRequest);
}

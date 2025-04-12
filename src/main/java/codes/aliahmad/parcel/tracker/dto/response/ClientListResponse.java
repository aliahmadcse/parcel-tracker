package codes.aliahmad.parcel.tracker.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientListResponse extends ListResponse
{
  private List<ClientResponse> clients;
}

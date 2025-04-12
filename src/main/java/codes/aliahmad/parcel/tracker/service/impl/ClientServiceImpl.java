package codes.aliahmad.parcel.tracker.service.impl;

import codes.aliahmad.parcel.tracker.dao.document.Client;
import codes.aliahmad.parcel.tracker.dao.repository.ClientRepository;
import codes.aliahmad.parcel.tracker.dto.request.ClientListRequest;
import codes.aliahmad.parcel.tracker.dto.request.ClientRequest;
import codes.aliahmad.parcel.tracker.dto.response.ClientListResponse;
import codes.aliahmad.parcel.tracker.dto.response.ClientResponse;
import codes.aliahmad.parcel.tracker.exception.BadInputException;
import codes.aliahmad.parcel.tracker.exception.ErrorCode;
import codes.aliahmad.parcel.tracker.exception.ErrorMessage;
import codes.aliahmad.parcel.tracker.exception.RecordNotFoundException;
import codes.aliahmad.parcel.tracker.mapper.ClientMapper;
import codes.aliahmad.parcel.tracker.service.ClientService;
import codes.aliahmad.parcel.tracker.service.PagingBase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends PagingBase implements ClientService
{
  private final ClientRepository clientRepository;


  @Override
  public ClientResponse createClient(ClientRequest clientRequest)
  {
    if (clientRepository.findByEmail(clientRequest.email()).isPresent()) {
      throw new BadInputException(ErrorCode.BAD_INPUT_PARAMETER, ErrorMessage.EMAIL_ALREADY_EXIST);
    }
    Client client = ClientMapper.toClient(clientRequest);

    Client savedClient = clientRepository.save(client);
    return ClientMapper.toClientResponse(savedClient);
  }

  @Override
  public ClientResponse findByEmail(String email)
  {
    Optional<Client> client = clientRepository.findByEmail(email);

    if (client.isEmpty())
    {
      throw new RecordNotFoundException(ErrorCode.RECORD_NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND);
    }
    return ClientMapper.toClientResponse(client.get());
  }

  @Override
  public ClientListResponse listClients(ClientListRequest listRequest)
  {
    Pageable pageable = createPage(listRequest);
    Page<Client> clientPage = clientRepository.findAll(pageable);

    List<ClientResponse> clientResponses = clientPage.getContent().stream()
            .map(ClientMapper::toClientResponse)
            .toList();

    ClientListResponse listResponse = new ClientListResponse();
    listResponse.setPageNo(pageable.getPageNumber() + 1);
    listResponse.setCurrentRecordsCount(clientPage.getNumberOfElements());
    listResponse.setTotalRecords(clientPage.getTotalElements());
    listResponse.setRecordPerPage(pageable.getPageSize());
    listResponse.setTotalPages(clientPage.getTotalPages());
    listResponse.setClients(clientResponses);
    return listResponse;
  }

}

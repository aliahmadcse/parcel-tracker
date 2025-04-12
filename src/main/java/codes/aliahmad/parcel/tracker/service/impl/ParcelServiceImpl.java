package codes.aliahmad.parcel.tracker.service.impl;

import codes.aliahmad.parcel.tracker.dao.document.Parcel;
import codes.aliahmad.parcel.tracker.dao.repository.ClientRepository;
import codes.aliahmad.parcel.tracker.dao.repository.ParcelRepository;
import codes.aliahmad.parcel.tracker.dto.ParcelResponse;
import codes.aliahmad.parcel.tracker.dto.ParcelStatus;
import codes.aliahmad.parcel.tracker.dto.request.ParcelRequest;
import codes.aliahmad.parcel.tracker.dto.request.ParcelSearchRequest;
import codes.aliahmad.parcel.tracker.dto.request.ParcelStatusUpdateRequest;
import codes.aliahmad.parcel.tracker.exception.BadInputException;
import codes.aliahmad.parcel.tracker.exception.ErrorCode;
import codes.aliahmad.parcel.tracker.exception.ErrorMessage;
import codes.aliahmad.parcel.tracker.exception.RecordNotFoundException;
import codes.aliahmad.parcel.tracker.mapper.ParcelMapper;
import codes.aliahmad.parcel.tracker.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService
{
  private final ParcelRepository parcelRepository;
  private final ClientRepository clientRepository;


  @Override
  public ParcelResponse createParcel(ParcelRequest parcelRequest)
  {
    String senderEmail = parcelRequest.senderEmail();
    String receiverEmail = parcelRequest.receiverEmail();

    if (clientRepository.findByEmail(senderEmail).isEmpty())
    {
      throw new RecordNotFoundException(ErrorCode.RECORD_NOT_FOUND, ErrorMessage.SENDER_NOT_FOUND);
    }
    if (clientRepository.findByEmail(receiverEmail).isEmpty())
    {
      throw new RecordNotFoundException(ErrorCode.RECORD_NOT_FOUND, ErrorMessage.RECEIVER_NOT_FOUND);
    }

    Parcel parcel = ParcelMapper.toParcel(parcelRequest);
    return ParcelMapper.toParcelResponse(parcelRepository.save(parcel));
  }

  @Override
  public ParcelResponse updateParcelStatus(ParcelStatusUpdateRequest parcelStatusUpdateRequest)
  {
    Parcel parcel = validateAndRetrieveParcel(parcelStatusUpdateRequest.parcelId());
    parcel.setStatus(parcelStatusUpdateRequest.status());
    Map<ParcelStatus, Instant> statusLog = parcel.getStatusLog();
    statusLog.put(parcelStatusUpdateRequest.status(), Instant.now());
    parcel.setStatusLog(statusLog);

    return ParcelMapper.toParcelResponse(parcelRepository.save(parcel));
  }

  @Override
  public List<ParcelResponse> searchParcels(ParcelSearchRequest parcelSearchRequest)
  {
    Criteria criteria = new Criteria();
    if (parcelSearchRequest.getCurrentStatus() != null)
    {
      criteria.and("status").is(parcelSearchRequest.getCurrentStatus());
    }
    if (parcelSearchRequest.getSenderEmail() != null)
    {
      criteria.and("senderEmail").is(parcelSearchRequest.getSenderEmail());
    }
    if (parcelSearchRequest.getCreationDateStart() != null && parcelSearchRequest.getCreationDateEnd() != null)
    {
      criteria.and("createdAt").gte(parcelSearchRequest.getCreationDateStart()).lte(parcelSearchRequest.getCreationDateEnd());
    }
    else if (parcelSearchRequest.getCreationDateStart() != null)
    {
      criteria.and("createdAt").gte(parcelSearchRequest.getCreationDateStart());
    }
    else if (parcelSearchRequest.getCreationDateEnd() != null)
    {
      criteria.and("createdAt").lte(parcelSearchRequest.getCreationDateEnd());
    }

    Query query = new Query(criteria);

    List<Parcel> parcels = parcelRepository.searchByQuery(query);
    return parcels.stream()
            .map(ParcelMapper::toParcelResponse)
            .toList();
  }

  private Parcel validateAndRetrieveParcel(String parcelId)
  {
    if (!ObjectId.isValid(parcelId))
    {
      throw new BadInputException(ErrorCode.BAD_INPUT_PARAMETER, ErrorMessage.INVALID_PARCEL_ID);
    }
    return parcelRepository.findById(new ObjectId(parcelId))
            .orElseThrow(() -> new RecordNotFoundException(ErrorCode.RECORD_NOT_FOUND, ErrorMessage.INVALID_PARCEL_ID));
  }


}

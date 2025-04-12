package codes.aliahmad.parcel.tracker.dto.response;

import codes.aliahmad.parcel.tracker.dto.request.ListRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ListResponse extends ListRequest
{
  private Long totalRecords;
  private Integer currentRecordsCount;
  private Integer totalPages;
}

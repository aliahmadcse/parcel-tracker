package codes.aliahmad.parcel.tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ListRequest
{
  @Schema(description = "Page number for pagination", example = "1")
  protected Integer pageNo;

  @Schema(description = "Number of records per page", example = "10")
  protected Integer recordPerPage;
}


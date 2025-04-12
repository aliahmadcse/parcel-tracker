package codes.aliahmad.parcel.tracker.service;

import codes.aliahmad.parcel.tracker.dto.request.ListRequest;
import codes.aliahmad.parcel.tracker.util.ValueUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public abstract class PagingBase
{
  private static final int DEFAULT_PAGE_NUMBER = 1;
  private static final int DEFAULT_RECORD_PER_PAGE = 20;
  private static final String DEFAULT_SORT_ORDER = "DESC";
  private static final String DEFAULT_SORT_COLUMN = "id";

  protected Pageable createPage(ListRequest request)
  {
    return createPage(request, DEFAULT_SORT_ORDER, DEFAULT_SORT_COLUMN);
  }

  protected Pageable createPage(ListRequest request, String sortOrder, String sortColumn)
  {
    if (null == sortOrder)
    {
      sortOrder = DEFAULT_SORT_ORDER;
    }

    if (null == sortColumn)
    {
      sortColumn = DEFAULT_SORT_COLUMN;
    }

    Integer currentPage = getPageNo(request);
    Integer recordPerPage = getRecordPerPage(request);

    return PageRequest.of(currentPage - 1, recordPerPage, Sort.by(Sort.Direction.fromString(sortOrder), sortColumn));
  }

  private Integer getPageNo(ListRequest request)
  {
    return ValueUtil.isPositiveNumber(request.getPageNo()) ? request.getPageNo() : DEFAULT_PAGE_NUMBER;
  }

  private Integer getRecordPerPage(ListRequest request)
  {
    return ValueUtil.isPositiveNumber(request.getRecordPerPage()) ? request.getRecordPerPage() : DEFAULT_RECORD_PER_PAGE;
  }
}

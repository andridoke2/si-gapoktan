package hamparan.gapoktan.dto.response;

import java.util.List;

public class AnggotaResponsePagination {

  private List<AnggotaResponse> content;

  private int pageNumber;

  private int pageSize;

  private long totalElements;

  private int totalPage;

  private boolean lastPage;

  public AnggotaResponsePagination() {
  }

  public AnggotaResponsePagination(List<AnggotaResponse> content, int pageNumber, int pageSize, long totalElements,
      int totalPage, boolean lastPage) {
    this.content = content;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalElements = totalElements;
    this.totalPage = totalPage;
    this.lastPage = lastPage;
  }

  public List<AnggotaResponse> getContent() {
    return content;
  }

  public void setContent(List<AnggotaResponse> content) {
    this.content = content;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public boolean isLastPage() {
    return lastPage;
  }

  public void setLastPage(boolean lastPage) {
    this.lastPage = lastPage;
  }

}

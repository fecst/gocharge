package br.com.gocharge.domain.defaultResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

  @JsonProperty("links")
  private Links links;

  @JsonProperty("page")
  private long page;

  @JsonProperty("total_pages")
  private long totalPages;

  @JsonProperty("total_elements")
  private long totalElements;

  @JsonProperty("page_size")
  private long pageSize;

  public Pagination() {
    super();
  }

  public Pagination(
      final Links links,
      final long page,
      final long totalPages,
      final long totalElements,
      final long pageSize) {
    this.links = links;
    this.page = page;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
    this.pageSize = pageSize;
  }

  public Pagination(
      final long page, final long totalPages, final long totalElements, final long pageSize) {
    this.page = page;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
    this.pageSize = pageSize;
  }

  public Pagination links(final Links links) {
    this.links = links;
    return this;
  }

  public Pagination links(
      final String first, final String last, final String previous, final String next) {
    links = new Links(first, last, previous, next);
    return this;
  }

  public Pagination page(final long page) {
    this.page = page;
    return this;
  }

  public Pagination totalPages(final long totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  public Pagination totalElements(final long totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  public Pagination pageSize(final long pageSize) {
    this.pageSize = pageSize;
    return this;
  }
}

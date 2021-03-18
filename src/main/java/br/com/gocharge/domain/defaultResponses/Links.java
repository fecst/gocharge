package br.com.gocharge.domain.defaultResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Links {

  private String first;

  private String last;

  private String previous;

  private String next;

  public Links() {
    super();
  }

  public Links(final String first, final String last, final String previous, final String next) {
    this.first = first;
    this.last = last;
    this.previous = previous;
    this.next = next;
  }

  public Links first(final String first) {
    this.first = first;
    return this;
  }

  public Links last(final String last) {
    this.last = last;
    return this;
  }

  public Links previous(final String previous) {
    this.previous = previous;
    return this;
  }

  public Links next(final String next) {
    this.next = next;
    return this;
  }
}

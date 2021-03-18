package br.com.gocharge.domain.defaultResponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {
    @JsonProperty("data")
    private T data;

    @JsonProperty("pagination")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagination pagination;

    public SuccessResponse(){super();}

    public SuccessResponse(final T data, final Pagination pagination){
        this.data = data;
        this.pagination = pagination;
    }

    public SuccessResponse<T> data(final T data){
        this.data = data;
        return this;
    }

    public SuccessResponse<T> pagination(final Pagination pagination){
        this.pagination = pagination;
        return this;
    }

    public SuccessResponse<T> pagination(final Links links, final long page, final long totalPages, final long totalElements, final long pageSize){
        this.pagination = new Pagination(links, page, totalPages, totalElements, pageSize);
        return this;
    }
}

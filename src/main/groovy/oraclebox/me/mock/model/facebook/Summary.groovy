package oraclebox.me.mock.model.facebook

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder([
    "total_count"
]) class Summary {

    @JsonProperty("total_count")
    Integer totalCount;

}
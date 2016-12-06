package oraclebox.me.mock.model.facebook

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder([
    "data",
    "paging",
    "summary"
])
class Friends {

    @JsonProperty("data")
    List<Datum> data = null;
    @JsonProperty("paging")
    Paging paging;
    @JsonProperty("summary")
    Summary summary;
}
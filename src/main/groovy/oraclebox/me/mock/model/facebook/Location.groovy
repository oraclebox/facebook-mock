package oraclebox.me.mock.model.facebook

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder([
    "id",
    "name"
])
class Location {

    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;

}
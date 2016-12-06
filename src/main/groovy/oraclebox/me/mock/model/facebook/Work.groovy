package oraclebox.me.mock.model.facebook

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder([
    "end_date",
    "employer",
    "start_date",
    "id"
])
 class Work {

    @JsonProperty("end_date")
    String endDate;
    @JsonProperty("employer")
    Employer employer;
    @JsonProperty("start_date")
    String startDate;
    @JsonProperty("id")
    String id;

}
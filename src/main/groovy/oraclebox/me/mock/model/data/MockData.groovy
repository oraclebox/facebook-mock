package oraclebox.me.mock.model.data

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder([
    "country",
    "females",
    "males",
    "locale",
    "locations",
    "domains",
    "companies",
    "languages",
    "timezone"
])
public class MockData {

    @JsonProperty("countries")
    List<String> countries = [];
    @JsonProperty("females")
    List<String> females = [];
    @JsonProperty("males")
    List<String> males = [];
    @JsonProperty("locale")
    String locale;
    @JsonProperty("locations")
    List<String> locations = [];
    @JsonProperty("domains")
    List<String> domains = [];
    @JsonProperty("companies")
    List<String> companies = [];
    @JsonProperty("languages")
    List<String> languages = [];
    @JsonProperty("timezone")
    int timezone;
}
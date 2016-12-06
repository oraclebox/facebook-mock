package oraclebox.me.mock.model.facebook

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder([
    "gender",
    "id",
    "name",
    "first_name",
    "last_name",
    "locale",
    "about"
])
class Datum {

    @JsonProperty("gender")
    String gender;
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("locale")
    String locale;
    @JsonProperty("about")
    String about;
}
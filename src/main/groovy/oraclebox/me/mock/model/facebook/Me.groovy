package oraclebox.me.mock.model.facebook

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder([
    "id",
    "name",
    "about",
    "age_range",
    "birthday",
    "email",
    "gender",
    "first_name",
    "last_name",
    "work",
    "location",
    "locale",
    "languages",
    "timezone",
    "updated_time",
    "friends"
])
 class Me {

    @JsonProperty("id")
     String id;
    @JsonProperty("name")
     String name;
    @JsonProperty("about")
     String about;
    @JsonProperty("age_range")
     AgeRange ageRange;
    @JsonProperty("birthday")
     String birthday;
    @JsonProperty("email")
     String email;
    @JsonProperty("gender")
     String gender;
    @JsonProperty("first_name")
     String firstName;
    @JsonProperty("last_name")
     String lastName;
    @JsonProperty("work")
     List<Work> work = null;
    @JsonProperty("location")
     Location location;
    @JsonProperty("locale")
     String locale;
    @JsonProperty("languages")
     List<Language> languages = null;
    @JsonProperty("timezone")
     Integer timezone;
    @JsonProperty("updated_time")
     String updatedTime;
    @JsonProperty("friends")
     Friends friends;

}
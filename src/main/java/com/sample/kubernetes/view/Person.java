package com.sample.kubernetes.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person extends ResourceSupport {

    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private String personUid;
    private String personId;

    @Override
    @JsonProperty("links")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Link> getLinks() {
        return super.getLinks();
    }
}

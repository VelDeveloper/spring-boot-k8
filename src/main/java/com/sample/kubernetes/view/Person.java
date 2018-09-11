package com.sample.kubernetes.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.hal.Jackson2HalModule;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person extends ResourceSupport {

    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private String personUid;
    private String personId;

//    @Override
//    @JsonProperty("links")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @JsonSerialize(using = Jackson2HalModule.HalLinkListSerializer.class)
//    @JsonDeserialize(using = Jackson2HalModule.HalLinkListDeserializer.class)
//    public List<Link> getLinks() {
//        return super.getLinks();
//    }
}

package com.fproject.sas.domain.cc;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class PoliceStation {
    @JsonProperty("Address")
    private String address;
    @JsonProperty("ID")
    private Integer iD;
    @JsonProperty("Name")
    private String name;
}
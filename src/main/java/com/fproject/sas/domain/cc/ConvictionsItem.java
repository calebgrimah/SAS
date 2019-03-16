package com.fproject.sas.domain.cc;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class ConvictionsItem {
    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("IncarcerationStartDate")
    private String incarcerationStartDate;
    @JsonProperty("IncarcerationEndDate")
    private String incarcerationEndDate;
    @JsonProperty("Offence")
    private String offence;
}
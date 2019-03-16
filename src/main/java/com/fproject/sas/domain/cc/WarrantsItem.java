package com.fproject.sas.domain.cc;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class WarrantsItem {
    @JsonProperty("Type")
    private Integer type;
    @JsonProperty("Priority")
    private Integer priority;
    @JsonProperty("RecordDate")
    private String recordDate;
    @JsonProperty("RequiredAction")
    private String requiredAction;
    @JsonProperty("Reason")
    private String reason;
}
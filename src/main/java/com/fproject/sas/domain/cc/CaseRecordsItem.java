package com.fproject.sas.domain.cc;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class CaseRecordsItem {
    @JsonProperty("PoliceStation")
    private PoliceStation policeStation;
    @JsonProperty("PrimaryCharge")
    private String primaryCharge;
    @JsonProperty("RecordDate")
    private String recordDate;
    @JsonProperty("BailStatus")
    private Integer bailStatus;
}
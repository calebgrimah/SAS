package com.fproject.sas.domain.cc;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class CaseMS {
    @JsonProperty("CaseRecords")
    private List<CaseRecordsItem> caseRecords;
    @JsonProperty("NumberOfConvictions")
    private Integer numberOfConvictions;
    @JsonProperty("EnrollmentID")
    private String enrollmentID;
    @JsonProperty("Convictions")
    private List<ConvictionsItem> convictions;
    @JsonProperty("Warrants")
    private List<WarrantsItem> warrants;
}
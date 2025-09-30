package com.haeahn.common.main.project.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResSearchProject {
    private String itemSort;
    private String itemType;
    private String itemCode;
    private String itemName;
    private String projSq;
    private String projCode;
    private String projName;
    private String orderTypeCode;
    private String startDate;
    private String endDate;
    private String orderName;
    private String isBookmark;
}

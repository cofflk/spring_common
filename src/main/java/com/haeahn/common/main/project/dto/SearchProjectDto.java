package com.haeahn.common.main.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchProjectDto {
    @JsonProperty("ITEM_SORT")
    private String itemSort;
    @JsonProperty("ITEM_TY")
    private String itemType;
    @JsonProperty("ITEM_CD")
    private String itemCode;
    @JsonProperty("ITEM_NM")
    private String itemName;

    @JsonProperty("PROJ_SQ")
    private String projSq;
    @JsonProperty("PROJ_CD")
    private String projCode;
    @JsonProperty("PROJ_NM")
    private String projName;

    @JsonProperty("ORDER_TYPE_CD")
    private String orderTypeCode;
    @JsonProperty("START_DT")
    private String startDate;
    @JsonProperty("END_DT")
    private String endDate;
    @JsonProperty("ORDER_NM")
    private String orderName;

    @JsonProperty("BOOKMARK_YN")
    private String isBookmark;

// 필요 시 추가
//    BUSN_LOC, PROJ_PROGRESS_CD, PROJ_STAT_CD, PROJ_TYPE_CD
//        , PROJ_SUB_TYPE_CD, PROJ_CONTR_CD, OWNR_COY_NM, PROJ_MNG_USER_ID, PROJ_COOD_USER_ID, PROJ_SECU_CD, SHARE_FOLD_FL
//        , CORP_CD, BIMTYPE_CD, REMARK_LN, XFER_STAT_CD, WRK_LOG_TYP_CD
}



package com.haeahn.common.main.org.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResSearchOrgUserSet {
    List<ResSearchOrgUser> book;
    List<ResSearchOrgUser> result;
}

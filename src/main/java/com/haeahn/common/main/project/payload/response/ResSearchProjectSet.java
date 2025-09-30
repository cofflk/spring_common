package com.haeahn.common.main.project.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResSearchProjectSet {
    List<ResSearchProject> book;
    List<ResSearchProject> result;
}

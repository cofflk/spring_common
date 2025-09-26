package com.haeahn.common.main.org.service;

import com.haeahn.common.main.code.payload.response.ResErpHrCode;
import com.haeahn.common.main.org.dto.SearchOrgUserDto;
import com.haeahn.common.main.org.payload.ReqSearchOrgUser;
import com.haeahn.common.main.org.payload.ResSearchOrgUser;
import com.haeahn.common.main.org.repository.SearchOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchOrgService {
    private final SearchOrgRepository searchOrgRepository;

    public List<ResSearchOrgUser> getSearchOrgUser(ReqSearchOrgUser reqSearchOrgUser) {
        List<SearchOrgUserDto> result = searchOrgRepository.searchOrgUser(reqSearchOrgUser);
        return result.stream()
                .map(item -> ResSearchOrgUser.builder()
                        .itemSort(item.getItemSort())
                        .itemType(item.getItemType())
                        .itemCode(item.getItemCode())
                        .itemName(item.getItemName())
                        .empNo(item.getEmpNo())
                        .userId(item.getUserId())
                        .userName(item.getUserName())
                        .companyCode(item.getCompanyCode())
                        .companyName(item.getCompanyName())
                        .deptCode(item.getDeptCode())
                        .deptName(item.getDeptName())
                        .titleCode(item.getTitleCode())
                        .titleName(item.getTitleName())
                        .dutyCode(item.getDutyCode())
                        .dutyName(item.getDutyName())
                        .jobCode(item.getJobCode())
                        .jobName(item.getJobName())
                        .deptSort(item.getDeptSort())
                        .dutySort(item.getDutySort())
                        .jobSort(item.getJobSort())
                        .titleSort(item.getTitleSort())
                        .userSort(item.getUserSort())
                        .email(item.getEmail())
                        .extension(item.getExtension())
                        .mobile(item.getMobile())
                        .hasO365(item.getHasO365())
                        .responsibility(item.getResponsibility())
                        .location(item.getLocation())
                        .build()
                )
                .toList();
    }
}

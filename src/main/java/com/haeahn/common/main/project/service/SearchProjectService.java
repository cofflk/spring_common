package com.haeahn.common.main.project.service;

import com.haeahn.common.main.project.dto.SearchProjectDto;
import com.haeahn.common.main.project.payload.request.ReqSearchProject;
import com.haeahn.common.main.project.payload.response.ResSearchProject;
import com.haeahn.common.main.project.repository.SearchProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchProjectService {
    private final SearchProjectRepository searchProjectRepository;

    private List<ResSearchProject> getPayload(List<SearchProjectDto> result) {

        return result.stream()
                .map(item -> ResSearchProject.builder()
                        .itemSort(item.getItemSort())
                        .itemType(item.getItemType())
                        .itemCode(item.getItemCode())
                        .itemName(item.getItemName())
                        .projSq(item.getProjSq())
                        .projCode(item.getProjCode())
                        .projName(item.getProjName())
                        .orderTypeCode(item.getOrderTypeCode())
                        .startDate(item.getStartDate())
                        .endDate(item.getEndDate())
                        .orderName(item.getOrderName())
                        .isBookmark(item.getIsBookmark())
                        .build()
                )
                .toList();
    }

    public Map<String, List<ResSearchProject>> getSearchProject(ReqSearchProject reqSearchProject) {
        List<String> resultNames = Arrays.asList("BOOK", "RESULT");
        Map<String, List<SearchProjectDto>> result = searchProjectRepository.searchProject(reqSearchProject, resultNames);

        return result.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> getPayload(entry.getValue()) // SearchOrgUserDto → ResSearchOrgUser 변환
                ));
    }
}

package com.haeahn.common.example.interceptor;

import com.haeahn.common.global.annotation.LogAccess;
import com.haeahn.common.global.threadlocal.CustomHeaderContext;
import com.haeahn.common.global.dto.CustomHeaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/interceptor")
@RequiredArgsConstructor
@Slf4j
public class InterceptorController {

    @RequestMapping("/test1")
    @LogAccess(value="InterceptorController 로그", level="INFO", message = "로그 테스트")
    public String test() {
//        RequestCustomHeaderDto clientInfo = (RequestCustomHeaderDto) request.getAttribute("clientInfo");
        CustomHeaderDto clientInfo = CustomHeaderContext.get();

        System.out.println(clientInfo.getDeviceType());
        System.out.println(clientInfo.getClientIp());
        System.out.println(clientInfo.getBrowserType());
        log.info("테스트 InterceptorController 123");

        System.out.println("/interceptor/test");
        return "interceptor test";
    }


}

package com.yanchun.web.feign;

import com.yanchun.user.service.VerificationService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author quyanchun
 * @Date 2018/12/28
 */
@FeignClient("spring-cloud-user")
@Component
public interface VerificationServiceFeign extends VerificationService {
}

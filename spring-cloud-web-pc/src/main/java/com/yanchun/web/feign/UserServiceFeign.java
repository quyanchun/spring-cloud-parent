package com.yanchun.web.feign;

import com.yanchun.user.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient("spring-cloud-user")
@Component
public interface UserServiceFeign extends UserService {
}

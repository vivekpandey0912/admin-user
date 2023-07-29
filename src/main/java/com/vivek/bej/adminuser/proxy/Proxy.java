package com.vivek.bej.adminuser.proxy;

import com.vivek.bej.adminuser.domain.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "UserAuthenticationService", url = "localhost:8084/")
public interface Proxy {

    @RequestMapping("/authUser/registerUser")
    ResponseEntity<?> registerUser(@RequestBody Admin admin);
}

package com.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.model.UserInfoResponse;
import com.server.service.UserInfoService;




@RestController
@RequestMapping("/appd/clntinfo")
public class UserInfoController {

	@Autowired
    private UserInfoService userInfoService;
	
	@GetMapping(value = "/getUserInfoAll")
    public ResponseEntity<UserInfoResponse> getAllUserInfo() {
        return userInfoService.getUserInfoAll();
    }
	
	
	@GetMapping(value = "/getUserInfo/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable int id) {
        return userInfoService.getUserInfo(id);
    }
}

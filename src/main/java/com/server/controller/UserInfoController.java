package com.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.model.ClientInfoResponse;
import com.server.service.UserInfoService;

@RestController
@RequestMapping("/appd/clntinfo")
public class UserInfoController {

	@Autowired
    private UserInfoService userInfoService;
	
	@GetMapping(value = "/getAllClientInfo")
    public ResponseEntity<ClientInfoResponse> getAllClientInfo() {
        return userInfoService.getAllClientInfo();
    }
	
	
	@GetMapping(value = "/getClientInfo/{id}")
    public ResponseEntity<ClientInfoResponse> getClientInfo(@PathVariable int id) {
        return userInfoService.getClientInfo(id);
    }
}

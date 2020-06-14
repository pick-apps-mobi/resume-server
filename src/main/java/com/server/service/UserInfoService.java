package com.server.service;

import org.springframework.http.ResponseEntity;

import com.server.model.ClientInfoResponse;

public interface UserInfoService {

	public ResponseEntity<ClientInfoResponse> getAllClientInfo();
	public ResponseEntity<ClientInfoResponse> getClientInfo(int id);
}

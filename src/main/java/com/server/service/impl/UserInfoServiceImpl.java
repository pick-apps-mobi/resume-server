package com.server.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.server.dao.UserInfoDao;
import com.server.model.ClientInfo;
import com.server.model.ClientInfoResponse;
import com.server.service.UserInfoService;
import com.server.util.Tokens;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	UserInfoDao userInfoDao;

	private static final Logger log = LoggerFactory.getLogger("resumeLogger");


	private ClientInfoResponse objClientInfoResponse;

	@Override
	public ResponseEntity<ClientInfoResponse> getAllClientInfo() {
		objClientInfoResponse = new ClientInfoResponse();
		try {
			List<ClientInfo> lst = userInfoDao.getClients();

			objClientInfoResponse.setLst(lst);
			objClientInfoResponse.setStatus(HttpStatus.OK.value());
			objClientInfoResponse.setSuccess(true);
			if(lst == null || lst.size() == 0)
				objClientInfoResponse.setInfo(Tokens.NO_DATA_FOUND.gettokens());

		}catch(SQLException | DataAccessException   ex) {
			log.error("Exception when try to fetch getClient info from DataBase " ,  ex);

			objClientInfoResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			objClientInfoResponse.setSuccess(false);
			objClientInfoResponse.setErrorMessage(ex.getLocalizedMessage());
			objClientInfoResponse.setInfo(Tokens.ISSUE_WHEN_INVOKING_FUNCTION.gettokens());
		}

		return new ResponseEntity<ClientInfoResponse>(objClientInfoResponse,HttpStatus.valueOf(objClientInfoResponse.getStatus()));
	}

	@Override
	public ResponseEntity<ClientInfoResponse> getClientInfo(int id) {
		objClientInfoResponse = new ClientInfoResponse();
		try {
			ClientInfo obj = userInfoDao.getClientInfo(id);

			objClientInfoResponse.setObj(obj);
			objClientInfoResponse.setStatus(HttpStatus.OK.value());
			objClientInfoResponse.setSuccess(true);
			if(obj == null)
				objClientInfoResponse.setInfo(Tokens.NO_DATA_FOUND.gettokens());

		}catch(SQLException | DataAccessException ex) {
			log.error("Exception when try to fetch getClientInfo info from DataBase " ,  ex);
			objClientInfoResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			objClientInfoResponse.setSuccess(false);
			objClientInfoResponse.setErrorMessage(ex.getLocalizedMessage());
			objClientInfoResponse.setInfo(Tokens.ISSUE_WHEN_INVOKING_FUNCTION.gettokens());
		}

		return new ResponseEntity<ClientInfoResponse>(objClientInfoResponse,HttpStatus.valueOf(objClientInfoResponse.getStatus()));
	}

}

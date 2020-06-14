package com.server.service;

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
import com.server.model.UserInfo;
import com.server.model.UserInfoResponse;
import com.server.util.Tokens;

@Service
public class UserInfoService{

	@Autowired
	UserInfoDao userInfoDao;

	private static final Logger log = LoggerFactory.getLogger("resumeLogger");


	private UserInfoResponse objUserInfoResponse;

	
	public ResponseEntity<UserInfoResponse> getUserInfoAll() {
		objUserInfoResponse = new UserInfoResponse();
		try {
			List<UserInfo> lst = userInfoDao.getUserInfoAll();

			objUserInfoResponse.setLst(lst);
			objUserInfoResponse.setStatus(HttpStatus.OK.value());
			objUserInfoResponse.setSuccess(true);
			if(lst == null || lst.size() == 0)
				objUserInfoResponse.setInfo(Tokens.NO_DATA_FOUND.gettokens());

		}catch(SQLException | DataAccessException   ex) {
			log.error("Exception when try to fetch getUser info from DataBase " ,  ex);

			objUserInfoResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			objUserInfoResponse.setSuccess(false);
			objUserInfoResponse.setErrorMessage(ex.getLocalizedMessage());
			objUserInfoResponse.setInfo(Tokens.ISSUE_WHEN_INVOKING_FUNCTION.gettokens());
		}

		return new ResponseEntity<UserInfoResponse>(objUserInfoResponse,HttpStatus.valueOf(objUserInfoResponse.getStatus()));
	}

	
	public ResponseEntity<UserInfoResponse> getUserInfo(int id) {
		objUserInfoResponse = new UserInfoResponse();
		try {
			UserInfo obj = userInfoDao.getUserInfo(id);

			objUserInfoResponse.setObjUserInfo(obj);
			objUserInfoResponse.setStatus(HttpStatus.OK.value());
			objUserInfoResponse.setSuccess(true);
			if(obj == null)
				objUserInfoResponse.setInfo(Tokens.NO_DATA_FOUND.gettokens());

		}catch(SQLException | DataAccessException ex) {
			log.error("Exception when try to fetch getUserInfo info from DataBase " ,  ex);
			objUserInfoResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			objUserInfoResponse.setSuccess(false);
			objUserInfoResponse.setErrorMessage(ex.getLocalizedMessage());
			objUserInfoResponse.setInfo(Tokens.ISSUE_WHEN_INVOKING_FUNCTION.gettokens());
		}

		return new ResponseEntity<UserInfoResponse>(objUserInfoResponse,HttpStatus.valueOf(objUserInfoResponse.getStatus()));
	}

}

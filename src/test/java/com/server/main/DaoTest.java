package com.server.main;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.server.dao.UserInfoDao;
import com.server.model.ClientInfo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DaoTest {

	@Autowired
    UserInfoDao userInfoDao;
	
	@Test
	public void getClients() throws SQLException, DataAccessException, NoSuchMethodException, SecurityException {
		List lst = userInfoDao.getClients();
		ClientInfo lst1 = userInfoDao.getClientInfo(1);
//		lst = value -> (System.out.println(v);)
		System.out.println(lst1);
		System.out.println(lst);
//		assertThat(lst,not(IsEmptyCollection.empty()));
	}
		
}

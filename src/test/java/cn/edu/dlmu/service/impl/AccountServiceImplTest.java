package cn.edu.dlmu.service.impl;

import cn.BaseTest;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class AccountServiceImplTest extends BaseTest {

	@Autowired
	AccountServiceImpl accountService;
	Account account;

	@Before
	public void setup(){
		account = new Account(null, null, "623351195", null, "123", null, null);
	}

	@Test
	public void queryById() throws Exception {
		System.out.println(accountService.queryAll());

	}

	@Test
	public void checkUser() throws Exception {
		System.out.println(accountService.checkUser(account));
		account.setLoginName("12321321");
		System.out.println(accountService.checkUser(account));
	}

	@Test
	public void getBondAccount() throws Exception {
		Integer aid = accountService.queryByLoginName(account.getLoginName()).getId();
		for (Account account : accountService.queryAll()) {
			System.out.println(account);
		}
	}

}
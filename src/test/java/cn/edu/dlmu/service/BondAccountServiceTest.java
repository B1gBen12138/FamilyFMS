package cn.edu.dlmu.service;

import cn.BaseTest;
import cn.edu.dlmu.pojo.BondAccount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BondAccountServiceTest extends BaseTest {

	@Autowired
	BondAccountService bondAccountService;

	@Test
	public void add() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void update() {
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void queryById() throws Exception {
		for (BondAccount ba : bondAccountService.queryAll()) {
			System.out.println(bondAccountService.queryById(ba.getId()));
		}
	}

	@Test
	public void queryAll() throws Exception {
		for (BondAccount ba : bondAccountService.queryAll()) {
			System.out.println(ba);
		}
	}

	@Test
	public void queryCountByParams() {
	}

	@Test
	public void queryByAccountId() {

	}
}
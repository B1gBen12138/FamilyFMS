package cn.edu.dlmu.dao;
import cn.edu.dlmu.pojo.Account;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import cn.BaseTest;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountMapperBaseTest extends BaseTest{
	@Autowired
	private AccountMapper accountMapper;

	String loginName;

	Account account, retAccount;

	Integer id;

	@Before
	public void setup(){
		loginName = "localhost";
		account = new Account(null, null, loginName, "ben", "hello", Boolean.FALSE, Boolean.FALSE);
	}

	@Test
	public void checkUser() {

	}

	@Test
	public void addAccount() {
		//System.out.println(accountMapper.queryAccountByLoginName("localhost"));
	}

	/**
	 *@Description 测试根据id删除用户
	 *             根据Before获取到
	 *@Params []
	 *@Return void
	 *@Author Mr.Xie
	 *@Date 2020/12/28
	 */
	@Test
	public void deleteAccountById()  throws Exception{
		Account ac = accountMapper.queryByLoginName("623351195");
		System.out.println(accountMapper.delete(ac.getId()));
	}

	@Test
	public void updateAccountById() throws Exception{
		Account oldAccount = accountMapper.queryByLoginName("623351195");
		System.out.println("更新");
		Account newAccount = new Account(oldAccount.getId(), null, "ben", "BigBen", "xielian", Boolean.TRUE, Boolean.FALSE);
		System.out.println(accountMapper.update(newAccount));
		System.out.println(accountMapper.queryByLoginName("ben"));
	}

	@Test
	public void queryAccountByLoginName()  throws Exception{
		Account ac = accountMapper.queryByLoginName("623351195");
		System.out.println(ac);
	}

	@Test
	public void queryAccountById() throws Exception{
		System.out.println(accountMapper.queryById(1));
	}

	@Test
	public void queryAllAccount()  throws Exception{
		for (Account ac:accountMapper.queryAll()) {
			System.out.println(ac);
		}
	}

	@Test
	public void queryByParams() throws DataAccessException{
		Map map = new HashMap<String, Object>();
		map.put("familyId", 1);
		for (Account a : accountMapper.queryByParams(map)) {
			System.out.println(a);
		}
	}
}
package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.AccountMapper;
import cn.edu.dlmu.dao.BondAccountMapper;
import cn.edu.dlmu.service.AccountService;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private BondAccountMapper bondAccountMapper;

	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	public void setBondAccountMapper(BondAccountMapper bondAccountMapper) {
		this.bondAccountMapper = bondAccountMapper;
	}

	public Integer add(Account t) throws Exception {
		AssertUtil.isNull(t, "实体为空");
		return accountMapper.add(t);
	}

	/*批量添加记录*/
	public Integer add(List<Account> tList) throws Exception {
		int cot = 0;
		for (Account t : tList) {
			accountMapper.add(t);
			cot++;
		}
		return cot;
	}

	/*删除记录*/
	public Integer delete(Integer id) throws Exception {
		// 判断 空
		AssertUtil.isNull(id, "列表为空");
		AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
		return accountMapper.delete(id);
	}

	/*删除记录*/
	public Integer delete(List<Integer> tList) throws Exception {
		// 判断 空
		AssertUtil.isNull(tList, "列表为空");
		int cot = 0;
		for (Integer id : tList) {
			AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
			cot += accountMapper.delete(id);
		}
		return cot;
	}
	/*修改记录*/
	public Integer update(Account entity) throws Exception {
		return accountMapper.update(entity);
	}

	public Integer update(List<Account> tList) throws Exception {
		AssertUtil.isNull(tList, "List is Null");
		int cot = 0;
		for (Account t : tList) {
			accountMapper.update(t);
			cot++;
		}
		return cot;
	}

	/**
	 * 根据参数统计记录数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws Exception {
		return accountMapper.queryCountByParams(map);
	}

	/*根据id查询记录*/
	public Account queryById(Integer id) throws Exception {
		AssertUtil.isNull(id, "记录id非空!");
		return accountMapper.queryById(id);
	}

	public List<Account> queryAll() throws Exception{
		return accountMapper.queryAll();
	}

	public Boolean checkUser(Account account) throws Exception {
		Account ac = accountMapper.queryByLoginName(account.getLoginName());
		if (ac != null
				&& ac.getLoginName().equals(account.getLoginName())
				&& ac.getPassword().equals(account.getPassword())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public Account queryByLoginName(String loginName) throws Exception {
		/*第一次登录，会提交name为空*/
		//if(loginName == null) return null;
		return accountMapper.queryByLoginName(loginName);
	}

	public List<BondAccount> queryBondAccount(Integer id) {
		AssertUtil.isNull(id, "账户id为空");
		return bondAccountMapper.queryByAccountId(id);
	}

	public List<Account> queryByParams(Map<String, Object> map) {
		return accountMapper.queryByParams(map);
	}

}

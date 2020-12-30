package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.BondAccountMapper;
import cn.edu.dlmu.service.AccountService;
import cn.edu.dlmu.service.IBaseService;
import cn.edu.dlmu.dao.AccountMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.pojo.FamilyAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountServiceImpl")
public class AccountServiceImpl extends IBaseServiceImpl<Account> implements AccountService {

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

}

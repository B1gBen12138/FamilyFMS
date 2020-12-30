package cn.edu.dlmu.service;

import cn.edu.dlmu.dao.BondAccountMapper;
import cn.edu.dlmu.pojo.BondAccount;

import java.util.List;

public interface BondAccountService extends IBaseService<BondAccount> {

	/*根据账户id查询所有证券账户*/
	public List<BondAccount> queryByAccountId(Integer accountId);



}

package cn.edu.dlmu.service;

import cn.edu.dlmu.dao.BondListMapper;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.pojo.BondList;

import java.util.List;

public interface BondListService extends IBaseService<BondList> {

	/*根据证券账户id获取所有证券*/
	public List<BondList> queryByBondAccountId(Integer bondAccountId);
}

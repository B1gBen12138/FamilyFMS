package cn.edu.dlmu.service;

import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.pojo.BondIOList;
import cn.edu.dlmu.pojo.BondList;
import cn.edu.dlmu.service.impl.IBaseServiceImpl;

import java.util.List;

public interface BondIOListService extends IBaseService<BondIOList> {

	/*根据证券账户获取所有证券*/
	public List<BondIOList> queryByBondListId(Integer bondListId);

}

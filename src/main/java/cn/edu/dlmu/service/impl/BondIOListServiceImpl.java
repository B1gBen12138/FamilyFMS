package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.pojo.BondIOList;
import cn.edu.dlmu.pojo.BondList;
import cn.edu.dlmu.service.BondIOListService;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public class BondIOListServiceImpl extends IBaseServiceImpl<BondIOList> implements BondIOListService {

	/*根据证券id查询所有流水*/
	public List<BondIOList> queryByBondListId(Integer bondListId) {
		return null;
	}
}

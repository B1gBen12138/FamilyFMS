package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.dao.BondAccountMapper;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.service.BondAccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service("bondAccountServiceImpl")
public class BondAccountServiceImpl extends IBaseServiceImpl<BondAccount> implements BondAccountService {
	@Autowired
	private BaseMapper bondAccountMapper;

	@Override
	public void setBaseMapper(BaseMapper<BondAccount> baseMapper) {
		this.bondAccountMapper = baseMapper;
	}

	/*根据用户id查询所有证券*/
	public List<BondAccount> queryByAccountId(Integer accountId) {
		return null;
	}
}

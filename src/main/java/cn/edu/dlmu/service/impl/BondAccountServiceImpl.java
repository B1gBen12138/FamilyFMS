package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.dao.BondAccountMapper;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.service.BondAccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("bondAccountServiceImpl")
public class BondAccountServiceImpl implements BondAccountService {
	@Autowired
	private BondAccountMapper bondAccountMapper;

	public void setBondAccountMapper(BondAccountMapper BondAccountMapper) {
		this.bondAccountMapper = BondAccountMapper;
	}

	public Integer add(BondAccount t) throws Exception {
		AssertUtil.isNull(t, "实体为空");
		return bondAccountMapper.add(t);
	}

	/*批量添加记录*/
	public Integer add(List<BondAccount> tList) throws Exception {
		int cot = 0;
		for (BondAccount t : tList) {
			bondAccountMapper.add(t);
			cot++;
		}
		return cot;
	}

	/*删除记录*/
	public Integer delete(Integer id) throws Exception {
		// 判断 空
		AssertUtil.isNull(id, "列表为空");
		AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
		return bondAccountMapper.delete(id);
	}

	/*删除记录*/
	public Integer delete(List<Integer> tList) throws Exception {
		// 判断 空
		AssertUtil.isNull(tList, "列表为空");
		int cot = 0;
		for (Integer id : tList) {
			AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
			cot += bondAccountMapper.delete(id);
		}
		return cot;
	}
	/*修改记录*/
	public Integer update(BondAccount entity) throws Exception {
		return bondAccountMapper.update(entity);
	}

	public Integer update(List<BondAccount> tList) throws Exception {
		AssertUtil.isNull(tList, "List is Null");
		int cot = 0;
		for (BondAccount t : tList) {
			bondAccountMapper.update(t);
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
		return bondAccountMapper.queryCountByParams(map);
	}

	/*根据id查询记录*/
	public BondAccount queryById(Integer id) throws Exception {
		AssertUtil.isNull(id, "记录id为空!");
		return bondAccountMapper.queryById(id);
	}

	public List<BondAccount> queryAll() throws Exception{
		return bondAccountMapper.queryAll();
	}

	/*根据用户id查询所有证券*/
	public List<BondAccount> queryByAccountId(Integer accountId) {
		return bondAccountMapper.queryByAccountId(accountId);
	}

	public BondAccount queryByName(String newName) {
		return null;
	}

}

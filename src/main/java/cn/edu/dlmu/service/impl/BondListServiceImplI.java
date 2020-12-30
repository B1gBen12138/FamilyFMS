package cn.edu.dlmu.service.impl;


import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.BondListMapper;
import cn.edu.dlmu.pojo.BondList;
import cn.edu.dlmu.service.BondListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("bondListServiceImpl")
public class BondListServiceImplI implements BondListService {

	@Autowired
	BondListMapper bondListMapper;

	public void setBondListMapper(BondListMapper bondListMapper) {
		this.bondListMapper = bondListMapper;
	}

	public Integer add(BondList t) throws Exception {
		AssertUtil.isNull(t, "实体为空");
		return bondListMapper.add(t);
	}

	/*批量添加记录*/
	public Integer add(List<BondList> tList) throws Exception {
		int cot = 0;
		for (BondList t : tList) {
			bondListMapper.add(t);
			cot++;
		}
		return cot;
	}

	/*删除记录*/
	public Integer delete(Integer id) throws Exception {
		// 判断 空
		AssertUtil.isNull(id, "列表为空");
		AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
		return bondListMapper.delete(id);
	}

	/*删除记录*/
	public Integer delete(List<Integer> tList) throws Exception {
		// 判断 空
		AssertUtil.isNull(tList, "列表为空");
		int cot = 0;
		for (Integer id : tList) {
			AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
			cot += bondListMapper.delete(id);
		}
		return cot;
	}
	/*修改记录*/
	public Integer update(BondList entity) throws Exception {
		return bondListMapper.update(entity);
	}

	public Integer update(List<BondList> tList) throws Exception {
		AssertUtil.isNull(tList, "List is Null");
		int cot = 0;
		for (BondList t : tList) {
			bondListMapper.update(t);
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
		return bondListMapper.queryCountByParams(map);
	}

	/*根据id查询记录*/
	public BondList queryById(Integer id) throws Exception {
		AssertUtil.isNull(id, "记录id非空!");
		return bondListMapper.queryById(id);
	}

	public List<BondList> queryAll() throws Exception{
		return bondListMapper.queryAll();
	}
	/**/
	public List<BondList> queryByBondAccountId(Integer bondAccountId) {
		return null;
	}
}

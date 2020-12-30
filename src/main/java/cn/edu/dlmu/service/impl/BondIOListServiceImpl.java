package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.BondIOListMapper;
import cn.edu.dlmu.pojo.BondIOList;
import cn.edu.dlmu.pojo.BondList;
import cn.edu.dlmu.service.BondIOListService;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public class BondIOListServiceImpl implements BondIOListService {
	
	BondIOListMapper bondIOListMapper;

	public void setBondIOListMapper(BondIOListMapper bondIOListMapper) {
		this.bondIOListMapper = bondIOListMapper;
	}

	public Integer add(BondIOList t) throws Exception {
		AssertUtil.isNull(t, "实体为空");
		return bondIOListMapper.add(t);
	}

	/*批量添加记录*/
	public Integer add(List<BondIOList> tList) throws Exception {
		int cot = 0;
		for (BondIOList t : tList) {
			bondIOListMapper.add(t);
			cot++;
		}
		return cot;
	}

	/*删除记录*/
	public Integer delete(Integer id) throws Exception {
		// 判断 空
		AssertUtil.isNull(id, "列表为空");
		AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
		return bondIOListMapper.delete(id);
	}

	/*删除记录*/
	public Integer delete(List<Integer> tList) throws Exception {
		// 判断 空
		AssertUtil.isNull(tList, "列表为空");
		int cot = 0;
		for (Integer id : tList) {
			AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
			cot += bondIOListMapper.delete(id);
		}
		return cot;
	}
	/*修改记录*/
	public Integer update(BondIOList entity) throws Exception {
		return bondIOListMapper.update(entity);
	}

	public Integer update(List<BondIOList> tList) throws Exception {
		AssertUtil.isNull(tList, "List is Null");
		int cot = 0;
		for (BondIOList t : tList) {
			bondIOListMapper.update(t);
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
		return bondIOListMapper.queryCountByParams(map);
	}

	/*根据id查询记录*/
	public BondIOList queryById(Integer id) throws Exception {
		AssertUtil.isNull(id, "记录id非空!");
		return bondIOListMapper.queryById(id);
	}

	public List<BondIOList> queryAll() throws Exception{
		return bondIOListMapper.queryAll();
	}
	/*根据证券id查询所有流水*/
	public List<BondIOList> queryByBondListId(Integer bondListId) {
		return null;
	}
}

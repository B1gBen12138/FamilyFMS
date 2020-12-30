package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.IOListMapper;
import cn.edu.dlmu.pojo.IOList;
import cn.edu.dlmu.service.IOListService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class IOListServiceImpl implements IOListService {

	@Autowired
	private IOListMapper ioListMapper;

	public void setIoListMapper(IOListMapper ioListMapper) {
		this.ioListMapper = ioListMapper;
	}

	public Integer add(IOList t) throws Exception {
		AssertUtil.isNull(t, "实体为空");
		return ioListMapper.add(t);
	}

	/*批量添加记录*/
	public Integer add(List<IOList> tList) throws Exception {
		int cot = 0;
		for (IOList t : tList) {
			ioListMapper.add(t);
			cot++;
		}
		return cot;
	}

	/*删除记录*/
	public Integer delete(Integer id) throws Exception {
		// 判断 空
		AssertUtil.isNull(id, "列表为空");
		AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
		return ioListMapper.delete(id);
	}

	/*删除记录*/
	public Integer delete(List<Integer> tList) throws Exception {
		// 判断 空
		AssertUtil.isNull(tList, "列表为空");
		int cot = 0;
		for (Integer id : tList) {
			AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
			cot += ioListMapper.delete(id);
		}
		return cot;
	}

	/*修改记录*/
	public Integer update(IOList entity) throws Exception {
		return ioListMapper.update(entity);
	}

	public Integer update(List<IOList> tList) throws Exception {
		AssertUtil.isNull(tList, "List is Null");
		int cot = 0;
		for (IOList t : tList) {
			ioListMapper.update(t);
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
		return ioListMapper.queryCountByParams(map);
	}

	/*根据id查询记录*/
	public IOList queryById(Integer id) throws Exception {
		AssertUtil.isNull(id, "记录id非空!");
		return ioListMapper.queryById(id);
	}

	public List<IOList> queryAll() throws Exception {
		return ioListMapper.queryAll();
	}

	public Boolean deleteBatch(List<IOList> ioLists) {
		return null;
	}
}

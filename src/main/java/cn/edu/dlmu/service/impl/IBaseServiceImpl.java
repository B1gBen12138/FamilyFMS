package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class IBaseServiceImpl<T> implements IBaseService<T> {

	public BaseMapper<T> baseMapper;

	public void setBaseMapper(BaseMapper<T> baseMapper) {
		this.baseMapper = baseMapper;
	}

	/*添加记录*/
	public Integer add(T t) throws Exception {
		AssertUtil.isNull(t, "实体为空");
		return baseMapper.add(t);
	}

	/*批量添加记录*/
	public Integer add(List<T> tList) throws Exception {
		int cot = 0;
		for (T t : tList) {
			baseMapper.add(t);
			cot++;
		}
		return cot;
	}

	/*删除记录*/
	public Integer delete(Integer id) throws Exception {
		// 判断 空
		AssertUtil.isNull(id, "列表为空");
		AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
		return baseMapper.delete(id);
	}

	/*删除记录*/
	public Integer delete(List<Integer> tList) throws Exception {
		// 判断 空
		AssertUtil.isNull(tList, "列表为空");
		int cot = 0;
		for (Integer id : tList) {
			AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
			cot += baseMapper.delete(id);
		}
		return cot;
	}
	/*修改记录*/
	public Integer update(T entity) throws Exception {
		return baseMapper.update(entity);
	}

	public Integer update(List<T> tList) throws Exception {
		AssertUtil.isNull(tList, "List is Null");
		int cot = 0;
		for (T t : tList) {
			baseMapper.update(t);
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
		return baseMapper.queryCountByParams(map);
	}

	/*根据id查询记录*/
	public T queryById(Integer id) throws Exception {
		AssertUtil.isNull(id, "记录id非空!");
		return baseMapper.queryById(id);
	}

	public List<T> queryAll() throws Exception{
		return baseMapper.queryAll();
	}

	///**
	// * 分页查询
	// * @param baseQuery
	// * @return
	// * @throws Exception
	// */
	//public PageInfo<T> queryForPage(BaseQuery baseQuery)throws Exception{
	//	PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
	//	List<T> list= baseMapper.queryForPage(baseQuery);
	//	PageInfo<T> pageInfo=new PageInfo<T>(list);
	//	return pageInfo;
	//}

	/**
	 * 查询总数量
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<T> queryByParams(Map map) throws Exception {
		return baseMapper.queryByParams(map);
	}


}
//
///**
// * 批量添加记录
// * @param entities
// * @return
// * @throws Exception
// */
//public Integer insertBatch(List<T> entities) throws Exception{
//	return baseMapper.addBatch(entities);
//}
//
///**
// * 批量删除
// * @param ids
// * @return
// */
//public Integer deleteBatch(Integer[] ids) throws Exception{
//	AssertUtil.isNull(ids.length==0,"请至少选择一项记录!");
//	return  baseMapper.deleteBatch(ids);
//}
//
///**
// * 批量更新
// * @param map
// * @return
// * @throws Exception
// */
//@SuppressWarnings("rawtypes")
//public Integer updateBatch(Map map) throws Exception{
//	return baseMapper.updateBatch(map);
//}
package cn.edu.dlmu.dao;

import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.pojo.IOList;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface IOListMapper extends BaseMapper<IOList> {

    /*插入一条收支流水*/
    Integer add(IOList entity) throws DataAccessException;

    /*批量插入收支流水*/
    Integer insertBatch(List<IOList> entities) throws DataAccessException;

    /*根据id查询收支流水*/
    IOList queryById(Integer id) throws Exception;

    /*根据参数查询收支流水*/
    List<IOList> queryByParams(Map map) throws DataAccessException;

    /*根据参数查询收支流水记录数*/
    Integer queryCountByParams(Map map) throws DataAccessException;

    /*更改一条收支流水*/
    Integer update(IOList entity) throws DataAccessException;

//    Integer updateBatch(Map map) throws DataAccessException;

    /*根据id删除收支流水*/
    Integer delete(Integer id) throws DataAccessException;

    /*根据参数删除收支流水*/
    Integer deleteByParams(Map map) throws DataAccessException;

//    int deleteBatch(int[] ids) throws DataAccessException;
}

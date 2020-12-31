package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.IOListMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.IOList;
import cn.edu.dlmu.service.IOListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("iOListServiceImpl")
public class IOListServiceImpl implements IOListService {

    private final static Logger logger = LogManager.getLogger(IOListServiceImpl.class);

    @Autowired
    private IOListMapper ioListMapper;

    public void setIoListMapper(IOListMapper ioListMapper) {
        this.ioListMapper = ioListMapper;
    }

    public Boolean add(IOList t) throws Exception {
        AssertUtil.isNull(t, "实体为空");
        return ioListMapper.add(t) == 1;
    }

    /*批量添加记录*/
    public Integer add(List<IOList> tList) throws Exception {
        AssertUtil.isNull(tList, "列表为空");

        int cot = 0;
        for (IOList t : tList)
            cot += this.add(t) ? 1 : 0;
        return cot;
    }

    @Override
    public Boolean add(IOList ioList, Account executor) throws Exception {
        if (ioList == null || executor == null || executor.getId() == null)
            return Boolean.FALSE;

        if (executor.getIsAdmin())
            return ioListMapper.add(ioList) == 1;

        ioList.setAccountId(executor.getId());

        if (ioList.getFamilyId() != null)
            if (!ioList.getFamilyId().equals(executor.getFamilyId()))
                return Boolean.FALSE;
        return ioListMapper.add(ioList) == 1;

    }

    /*删除记录*/
    public Boolean delete(Integer id) throws Exception {
        // 判断 空
        AssertUtil.isNull(id, "列表为空");
        return ioListMapper.delete(id) == 1;
    }

    @Override
    public Boolean delete(Integer id, Account executor) throws Exception {

        if (id == null)
            return Boolean.FALSE;

        LinkedList<Integer> ids = new LinkedList<Integer>();
        ids.add(id);

        return this.delete(ids, executor);
    }

    /*删除记录*/
    public Boolean delete(List<Integer> tList) throws Exception {
        // 判断 空
        AssertUtil.isNull(tList, "列表为空");
        int cot = 0;
        for (Integer id : tList) {
            cot += this.delete(id) ? 1 : 0;
        }
        return cot == tList.size();
    }

    @Override
    public Boolean delete(List<Integer> ids, Account executor) throws Exception {
        if (ids == null || executor == null || executor.getId() == null)
            return null;

        if (executor.getIsAdmin())
            return this.delete(ids);
        else {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put("accountId", executor.getId());

            int cot = 0;
            for (Integer id : ids) {
                paramMap.put("id", id);
                cot += ioListMapper.deleteByParams(paramMap)==1 ? 1 : 0;
            }
            return cot == ids.size();
        }
    }

    /*修改记录*/
    public Boolean update(IOList entity) throws Exception {
        return ioListMapper.update(entity) == 1;
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

    @Override
    public Boolean update(IOList ioList, Account executor) throws Exception {
        if (ioList == null || ioList.getId() == null || executor == null || executor.getId() == null)
            return Boolean.FALSE;

        if (executor.getIsAdmin())
            return ioListMapper.update(ioList) == 1;

        IOList original = this.queryById(ioList.getId(), executor);
        AssertUtil.isNull(original, "收支项目不存在");

        ioList.setAccountId(null);
        ioList.setFamilyId(null);

        return ioListMapper.update(ioList) == 1;
    }

    /**
     * 根据参数统计记录数
     *
     * @param map
     * @return
     * @throws Exception
     */
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

    /*根据参数查询收支流水*/
    public List<IOList> queryByParams(Map map) throws Exception {
        if (map == null || map.isEmpty())
            return null;
        return ioListMapper.queryByParams(map);
    }

    public Boolean deleteBatch(List<IOList> ioLists) {
        return null;
    }

    public IOList queryById(Integer id, Account account) throws Exception {
        if (id == null || account == null || account.getId() == null)
            return null;

        IOList ioList = null;
        if (account.getIsAdmin())
            ioList = this.queryById(id);
        else {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put("id", id);
            paramMap.put("accountId", account.getId());

            List<IOList> result = this.queryByParams(paramMap);
            if (result != null && !result.isEmpty()) {
                if (result.size() != 1)
                    logger.warn(String.format("unexpected query result: result size %d", result.size()));
                ioList = result.get(0);
            }
        }

        return ioList;
    }

    public List<IOList> queryByAccount(Account account) throws Exception {
        if (account == null || account.getId() == null)
            return null;

        List<IOList> result = null;
        if (account.getIsAdmin())
            result = this.queryAll();
        else {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put("accountId", account.getId());

            result = this.queryByParams(paramMap);
        }

        return result;
    }
}

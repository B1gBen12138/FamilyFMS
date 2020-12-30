package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.dao.IOListMapper;
import cn.edu.dlmu.pojo.FamilyAccount;
import cn.edu.dlmu.pojo.IOList;
import cn.edu.dlmu.service.IOListService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOListServiceImpl extends IBaseServiceImpl<IOList> implements IOListService {

    @Autowired
    private IOListMapper ioListMapper;

    public void setBaseMapper(BaseMapper<IOList> baseMapper) {
        this.baseMapper = ioListMapper;
    }

    public Boolean deleteBatch(List<IOList> ioLists) throws Exception {

        if (ioLists == null)
            return Boolean.FALSE;

        for (IOList item : ioLists)
            ioListMapper.delete(item.getId());

        return true;
    }

    public List<IOList> queryByFamily(FamilyAccount familyAccount) throws Exception {
        if (familyAccount == null)
            return null;

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("familyId", familyAccount.getId());

        return ioListMapper.queryByParams(paramMap);
    }
}

package cn.edu.dlmu.dao;

import cn.BaseTest;
import cn.edu.dlmu.pojo.IOList;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IOListMapperTest extends BaseTest {

    @Autowired
    private IOListMapper ioListMapper;

    @Test
    public void insert() throws Exception {
        IOList il = new IOList(null, 1, 1, true, 1.1f, 1, new Date(), "", "");
//        System.out.println(il);
        assertEquals(ioListMapper.add(il), new Integer(1));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isOutput", true);
        List<IOList> ill = ioListMapper.queryByParams(paramMap);
        assertNotNull(ill);
        for (IOList i : ill)
            System.out.println(i);
    }

    @Test
    public void insertBatch() throws Exception {
        IOList il = null;

        List<IOList> ill = new LinkedList<IOList>();
        ill.add(new IOList(null, 1, 1, true, 2.1f, 1, new Date(), "", ""));
	    ill.add(new IOList(null, 1, 1, true, 3.1f, 0, new Date(), "", ""));
        ill.add(new IOList(null, 1, 1, true, 4.1f, 1, new Date(), "", ""));


        assertEquals(ioListMapper.insertBatch(ill), new Integer(3));

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("accountId", 1);


        ill = ioListMapper.queryByParams(paramMap);
        assertNotNull(ill);
        for (IOList i : ill)
            System.out.println(i);

        il = new IOList();
        il.setId(ill.get(1).getId());
        il.setSource("dd");
        System.out.println(il);
        assertEquals(ioListMapper.update(il), new Integer(1));

        il = ioListMapper.queryById(il.getId());
        assertNotNull(il);
        System.out.println(il);

        assertEquals(ioListMapper.delete(il.getId()), new Integer(1));

        assertEquals(ioListMapper.queryCountByParams(paramMap), new Integer(2));

    }

    @Test
    public void queryById() throws Exception {
        IOList il = ioListMapper.queryById(1);
        System.out.println(il);
    }

    @Test
    public void queryByParams() throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isOutput", true);
        List<IOList> il = ioListMapper.queryByParams(paramMap);
        for (IOList i : il)
            System.out.println(i);
    }

}

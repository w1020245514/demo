package com.moli.demo.dao;

import com.moli.demo.entity.Area;
import com.moli.demo.web.AreaController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Autowired
    AreaController pushController;
    @Test
    public void queryArea() throws Exception {
        List<Area>  areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }

    @Test
    public void queryAreaById() throws Exception {
        Area area = areaDao.queryAreaById(1);
        assertEquals("东苑",area.getAreaName());
    }

    @Test
    public void insertArea() throws Exception {
        Area area = new Area();
        area.setAreaName("西苑");
        area.setPriority(10);
        area.setLastEditTime(new Date());
        int num = areaDao.insertArea(area);
        assertEquals(1,num);
    }

    @Test
    public void updateArea() throws Exception {
        Area area = new Area();
        area.setAreaId(3);
        area.setAreaName("西苑123");
        area.setLastEditTime(new Date());
        int num = areaDao.updateArea(area);
        assertEquals(1,num);
    }

    @Test
    public void deleteArea() throws Exception {
        int num = areaDao.deleteArea(3);
        assertEquals(1,num);
    }

    @Test
    public void push() {
        pushController.push();
    }

}
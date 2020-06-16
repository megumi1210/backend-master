package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.FemaleHealthFormMapper;
import org.example.mapper.MaleHealthFormMapper;
import org.example.model.FemaleHealthForm;
import org.example.model.MaleHealthForm;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class FemaleHealthFormTest {

    Logger logger  = Logger.getLogger(FemaleHealthFormTest.class);

    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();
        }
    }

    @Test
    public void testInsert(){
        openSession(session -> {
            FemaleHealthFormMapper mapper = session.getMapper(FemaleHealthFormMapper.class);
            FemaleHealthForm form = new  FemaleHealthForm();
            form.setEmpId(7L);
            form.setUterus("子宫癌");
            form.setHeart("心脏病");
            form.setKidney("肾虚");
            form.setLiver("脾不好");
            form.setLung("有肺炎");
            form.setSpleen("肝脏不好");
            form.setNote("这个人有病");
            logger.info(mapper.insertRecord(form));
        });
    }

    @Test
    public void testSelect(){
        openSession(session -> {
           FemaleHealthFormMapper mapper = session.getMapper(FemaleHealthFormMapper.class);
            logger.info(mapper.findRecordByEmpId(7L));
        });
    }
}

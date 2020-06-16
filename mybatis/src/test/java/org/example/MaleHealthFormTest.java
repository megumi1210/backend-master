package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.MaleHealthFormMapper;
import org.example.model.MaleHealthForm;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class MaleHealthFormTest {

    Logger logger  = Logger.getLogger(MaleHealthFormTest.class);

    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();
        }
    }

    @Test
    public void testInsert(){
         openSession(session -> {
             MaleHealthFormMapper mapper = session.getMapper(MaleHealthFormMapper.class);
             MaleHealthForm form = new MaleHealthForm();
             form.setEmpId(6L);
             form.setProstate("前列腺炎");
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
            MaleHealthFormMapper mapper = session.getMapper(MaleHealthFormMapper.class);
            logger.info(mapper.findRecordByEmpId(6L));
        });
    }
}

package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.WorkCardMapper;
import org.example.model.WorkCard;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class WorkCardTest {

    Logger logger  = Logger.getLogger(WorkCard.class);

    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();
        }
    }

    @Test
    public void  testFindByEmpId(){
         openSession((sqlSession)->{
             WorkCardMapper mapper = sqlSession.getMapper(WorkCardMapper.class);
             logger.info(mapper.findWorkCardByEmpId(6L));
         });

    }

    @Test
    public void  testInsert(){
        openSession((sqlSession)->{
             WorkCardMapper mapper = sqlSession.getMapper(WorkCardMapper.class);
             WorkCard workCard = new WorkCard();
             workCard.setEmpId(6L);
             workCard.setRealName("tom");
             workCard.setPosition("社畜");
             workCard.setDepartment("203");
             workCard.setMobile("123456");
             workCard.setNote("这是个懒人");
             logger.info(mapper.insertWorkCard(workCard));
        });
    }
}

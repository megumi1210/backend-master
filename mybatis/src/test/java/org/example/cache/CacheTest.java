package org.example.cache;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.RoleTest;
import org.example.SessionHandler;
import org.example.mapper.RoleMapper;
import org.example.model.Role;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class CacheTest {

    Logger logger = Logger.getLogger(CacheTest.class);


    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();
        }
    }
    @Test
    public  void test(){
        openSession(session -> {
            RoleMapper roleMapper = session.getMapper(RoleMapper.class);
            Role role =  roleMapper.getRole(1L);
            logger.info("再获取一次POJO...");
            Role role2=roleMapper.getRole(1L);
        });
    }


}

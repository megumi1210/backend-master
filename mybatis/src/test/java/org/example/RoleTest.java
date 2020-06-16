package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.RoleMapper;
import org.example.mapper.UserMapper;
import org.example.model.Role;
import org.example.model.User;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class RoleTest {

    Logger logger  = Logger.getLogger(RoleTest.class);

    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();
        }
    }

    @Test
    public void testManyToManyCascade(){
        openSession(
                session -> {
                    RoleMapper mapper = session.getMapper(RoleMapper.class);
                    Role role = mapper.getRole(2L);
                    logger.info(role.getUsers());
                });
    }
}

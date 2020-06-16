package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.model.WorkCard;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class UserTest {

    Logger logger  = Logger.getLogger(UserTest.class);

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
          UserMapper mapper = session.getMapper(UserMapper.class);
          User user = mapper.getUserById(1L);
          logger.info(user.getRoles());
        });
    }

}

package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.RoleMapper;
import org.example.mapper.UserMapper;
import org.example.model.Role;
import org.example.model.User;
import org.example.page.PageParams;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public  void testDynamicSql(){
         openSession(session -> {
             RoleMapper mapper = session.getMapper(RoleMapper.class);
             List<Long> ids =new ArrayList<>();
             ids.add(1L);
             ids.add(2L);
             logger.info(mapper.findRolesByIds(ids));
         });
    }

    @Test
    public void testInsert(){
        openSession(session -> {
            RoleMapper mapper = session.getMapper(RoleMapper.class);

            for(int i = 0 ;i<100 ;i++){
                 Role role  = new Role();
                 role.setRoleName("role"+i);
                 role.setNote(""+i);
                 mapper.insertRole(role);
            }

        });
    }

    @Test
    public void testPagePlugin(){
         openSession(session -> {
             RoleMapper mapper = session.getMapper(RoleMapper.class);
             PageParams pageParams = new PageParams();
             pageParams.setPageSize(10);
             List<Role> roles = mapper.findRoles(pageParams,"role");
             logger.info(roles.size());
         });
    }
}

package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.example.mapper.RoleMapper;
import org.example.model.Role;
import org.example.utils.SqlSessionFactoryUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Logger logger  = Logger.getLogger(App.class);
        SqlSession  sqlSession =  null;
        try{
            sqlSession = SqlSessionFactoryUtils.getSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(1L);
            logger.info(role.getRoleName());
        }finally {
            if(sqlSession !=null){
                 sqlSession.close();
            }
        }
    }
}

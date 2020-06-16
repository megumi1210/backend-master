package org.example;

import static org.junit.Assert.assertTrue;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.EmployeeMapper;
import org.example.model.Employee;
import org.example.model.SexEnum;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Unit test for simple App.
 */
public class EmployeeTest
{
    Logger logger  = Logger.getLogger(EmployeeTest.class);

    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();

        }
    }

    @Test
    public void  empInsert(){

            openSession((sqlSession)->{
                EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
                Employee employee = new Employee();
               // SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                employee.setBirthday(new Date(new java.util.Date().getTime()));
                employee.setEmail("123@qq.com");
                employee.setMobile("77443518");
                employee.setNote("这是个懒人");
                employee.setRealName("tom");
                employee.setPosition("社畜");
                employee.setSex(SexEnum.MALE);
                logger.info(employeeMapper.insertEmployee(employee));
            });
    }

    @Test
    public void  deleteEmp(){
         openSession((sqlSession)->{
              EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
              Employee employee = employeeMapper.findEmployeeById(5L);
              logger.info(employee);
              employee.setRealName("utaha");
              logger.info("修改后的" + employee);
              logger.info(employeeMapper.updateEmployee(employee));
              logger.info(employeeMapper.deleteEmployee(employee.getId()));;
         });

    }

    //一对一工牌表的测试 ctrl+shift+space快速生成lambda
    @Test
    public void testCascade(){
        openSession(session -> {
             EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
             logger.info(mapper.findEmployeeById(6L));
        });

    }







}

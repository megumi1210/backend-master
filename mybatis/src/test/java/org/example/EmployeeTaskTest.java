package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;


import org.example.mapper.EmployeeTaskMapper;
import org.example.model.EmployeeTask;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class EmployeeTaskTest {


    Logger logger  = Logger.getLogger(EmployeeTaskTest.class);

    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();
        }
    }

    @Test
    public  void testInsert(){
        openSession(session -> {
            EmployeeTaskMapper mapper = session.getMapper(EmployeeTaskMapper.class);
            EmployeeTask employeeTask = new EmployeeTask();
            employeeTask.setId(2L);
            employeeTask.setEmpId(6L);
            employeeTask.setTaskId(2L);
            employeeTask.setTaskName("任务名2");
            employeeTask.setNote("测试任务2");
            mapper.insertRecord(employeeTask);
        });
    }

    @Test
    public void testSelect(){
        openSession(session -> {
            EmployeeTaskMapper mapper = session.getMapper(EmployeeTaskMapper.class);
            //toString() 方法会使得懒加载失效
            mapper.selectRecordById(1L);

        });
    }

}

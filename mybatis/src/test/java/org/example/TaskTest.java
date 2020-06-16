package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.mapper.TaskMapper;
import org.example.model.Task;
import org.example.model.WorkCard;
import org.example.utils.SqlSessionFactoryUtils;
import org.junit.Test;

public class TaskTest {

    Logger logger  = Logger.getLogger(TaskTest.class);

    public  void openSession(SessionHandler handler){
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            handler.handler(sqlSession);
            sqlSession.commit();
        }
    }
    @Test
    public void testInsert(){
        openSession(session -> {
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            Task task = new Task();
            task.setId(2L);
            task.setTitle("标题2");
            task.setContext("抄书10遍2");
            task.setNote("这个人是个傻子2");
            logger.info(mapper.insertTask(task));
        });
    }

    @Test
    public void testSelect(){
         openSession(session -> {
              TaskMapper mapper = session.getMapper(TaskMapper.class);
              Task task = mapper.getTaskById(1L);
              logger.info(task);
         });
    }

}

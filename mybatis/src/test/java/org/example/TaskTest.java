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
            task.setId(1L);
            task.setTitle("标题");
            task.setContext("抄书10遍");
            task.setNote("这个人是个傻子");
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

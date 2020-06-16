package org.example.mapper;

import org.example.model.Task;

/**
 * @author chenj
 */
public interface TaskMapper {

    int insertTask(Task task);
    Task getTaskById(Long id);
    // int deleteTask();
    //int updateTask();
}

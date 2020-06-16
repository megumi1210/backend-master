package org.example.mapper;

import org.example.model.EmployeeTask;

import java.util.List;

/**
 * 中间表一般只需要增删改查就够了
 * @author chenj
 */
public interface EmployeeTaskMapper {
    int insertRecord(EmployeeTask record);

    EmployeeTask selectRecordById(Long id);

    List<EmployeeTask> findEmployeeTasksByEmpId(Long id);
}

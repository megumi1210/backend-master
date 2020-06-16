package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.model.*;


/**
 * @author chenj
 */
public interface EmployeeMapper {
    /**
     *  通过javaBean的方式插入员工
     * @param emp 需要插入的员工
     * @return 返回成功的数量
     */
    int insertEmployee(@Param("emp") Employee emp);

    /**
     * 通过id删除员工
     * @param id 需要删除的员工id
     * @return 返回成功的数量
     */
    int deleteEmployee(Long id);

    /**
     *  更新员工的数据
     * @param employee 更新的员工数据
     * @return 返回成功的数量
     */
    int updateEmployee(Employee employee);

    /**
     *  通过id查找员工
     * @param id 员工id
     * @return 员工对象数据
     */
    Employee findEmployeeById(Long id);



}

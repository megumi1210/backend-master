package org.example.mapper;

import org.example.model.WorkCard;

/**
 * @author chenj
 */
public interface WorkCardMapper {
    /**
     *  通过外键员工id获取工牌表
     * @param empId 员工id
     * @return  工牌数据
     */
    WorkCard findWorkCardByEmpId(Long empId);

    /**
     *  插入工作表数据
     * @param workCard 工作表对象
     * @return 影响的条数
     */
    int  insertWorkCard(WorkCard workCard);
}

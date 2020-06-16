package org.example.mapper;

import org.example.model.FemaleHealthForm;


/**
 * @author chenj
 */
public interface FemaleHealthFormMapper {

    /**
     *  插入男性健康表数据
     * @param record 需要插入的数据
     * @return 返回影响的条数
     */
    int insertRecord(FemaleHealthForm record);


   FemaleHealthForm  findRecordByEmpId(Long id);
}

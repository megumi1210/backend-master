package org.example.mapper;

import org.example.model.MaleHealthForm;

/**
 * 男性健康表的映射
 * @author chenj
 */
public interface MaleHealthFormMapper {
    /**
     *  插入男性健康表数据
     * @param record 需要插入的数据
     * @return 返回影响的条数
     */
    int insertRecord(MaleHealthForm record);


    MaleHealthForm  findRecordByEmpId(Long id);
}

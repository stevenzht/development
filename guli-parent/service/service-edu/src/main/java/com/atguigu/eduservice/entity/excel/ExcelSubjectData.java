package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author steven
 * @create 2023-09-24 10:11
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private int oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}

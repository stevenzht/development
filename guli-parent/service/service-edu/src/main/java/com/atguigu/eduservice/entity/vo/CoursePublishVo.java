package com.atguigu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author steven
 * @create 2023-09-26 9:44
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}

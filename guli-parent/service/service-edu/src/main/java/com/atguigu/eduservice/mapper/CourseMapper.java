package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author steven
 * @create 2023-09-26 9:43
 */
public interface CourseMapper extends BaseMapper<Course> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
}

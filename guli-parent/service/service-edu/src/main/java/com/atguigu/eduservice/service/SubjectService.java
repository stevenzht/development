package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author steven
 * @create 2023-09-24 10:13
 */
public interface SubjectService extends IService<Subject> {
    //添加课程分类
    void saveSubject(MultipartFile file,SubjectService subjectService);
}

package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.service.TeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.query.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * 访问地址：http://localhost:8001/eduservice/teacher
 * </p>
 *
 * @author atguigu
 * @since 2023-09-23
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R list(){
//        return teacherService.list(null);

        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        boolean result = teacherService.removeById(id);

        if (result){
            return R.ok();
        }else {
            return R.error().message("删除失败");
        }

    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page",value = "当前页码",required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            TeacherQuery teacherQuery){

        Page<Teacher> pageParam = new Page<>(page, limit);

        teacherService.page(pageParam,null);
//        teacherService.pageQuery(pageParam,teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher",value = "讲师对象",required = true)
            @RequestBody Teacher teacher
    ){
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id",value = "讲师id",required = true)
            @PathVariable String id
    ){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }

}


package com.atguigu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Date;

/**
 * @author steven
 * @create 2023-09-18 11:08
 */
@Data
public class User {

    //主键策略
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    public Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Date updateTime;

    @Version//乐观锁
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Integer version;

    @TableLogic(value = "0",delval = "1")//逻辑删除,0未删除，1已删除
    @TableField(fill = FieldFill.INSERT)//填充值
    public Integer deleted;
}

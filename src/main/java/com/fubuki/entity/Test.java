package com.fubuki.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("test")
public class Test {
    //说明id字段是表的主键，同时数据新增时使用表自带的自增主键生成编号
    @TableId(type = IdType.AUTO)
    @TableField("id") //说明属性对应哪个字段
    private Integer id;
    @TableField("content") //如果字段名与属性名/驼峰命名相同时@TableField可以忽略
    private String content;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.noly.forum.entity;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.util.Date;

@Data
public class DiscussPost {

    private int id;
    private int userId;
    private String title;
    private String content;
    private int type;
    private int status;
    private Date createTime;
    private int commentCount;
    private double score;

}

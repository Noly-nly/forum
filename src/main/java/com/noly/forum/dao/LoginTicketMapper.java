package com.noly.forum.dao;

import com.noly.forum.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
@Deprecated
public interface LoginTicketMapper {

    /**
     * 基于注解的Mapper使用，等价于使用xxx-mapper.xml的使用
     */
    @Insert({
            "insert into login_ticket(user_id, ticket, status, expired) ",
            "values(#{userId}, #{ticket}, #{status}, #{expired}) "
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select id, user_id, ticket, status, expired ",
            "from login_ticket ",
            "where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket);


    // 基于注解的Sql语句同样也支持动态sql语句，<if> xxx </if>
    // 使用时要放在<script> xxx </script>中
    // 下面演示恒成立，不加<if>也正确
    // 可实现退出登录修改凭证功能
    @Update({
            "<script>",
            "update login_ticket set status=#{status} where ticket=#{ticket}",
            "<if test=\"ticket!=null\"> ",
            "and 1=1 ",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);

}

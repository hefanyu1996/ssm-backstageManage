package cn.itcast.dao;

import cn.itcast.domain.Member;
import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;

}

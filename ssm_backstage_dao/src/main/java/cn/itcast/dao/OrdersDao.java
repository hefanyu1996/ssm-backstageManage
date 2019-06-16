package cn.itcast.dao;

import cn.itcast.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {


    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "product" ,one = @One(select = "cn.itcast.dao.ProductDao.findById"),column = "productId")
    })
    List<Orders> findAll();

    @Select("select * from orders where id = #{oid}")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            //查询订单关联的 商品 信息 1：1
            @Result(property = "product" ,one = @One(select = "cn.itcast.dao.ProductDao.findById"),column = "productId"),
            //查询订单关联的 游客信息
            @Result(property = "member" ,one=@One(select = "cn.itcast.dao.MemberDao.findById"),column = "memberId"),
            //查询订单关联的 会员（联系人）信息
            @Result(property = "travellerList",javaType = List.class,many = @Many(select = "cn.itcast.dao.TravellerDao.findTravellersByOid"),column = "id")
    })
    Orders findById(String oid);


    @Select("select * from orders where productId = #{pid}")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "product" ,one = @One(select = "cn.itcast.dao.ProductDao.findById"),column = "productId")
    })
    List<Orders> findByPid(String pid);

}

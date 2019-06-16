package cn.itcast.dao;

import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductDao {

    @Select("select * from product")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "productNum" ,column = "productNum"),
            @Result(property = "productName" ,column = "productName"),
            @Result(property = "cityName" ,column = "cityName"),
            @Result(property = "departureTime",column = "departureTime"),
            @Result(property = "productPrice" ,column = "productPrice"),
            @Result(property = "productDesc" ,column = "productDesc"),
            @Result(property = "productStatus" ,column = "productStatus"),
            @Result(property = "ordersList",javaType = List.class ,many = @Many(select = "cn.itcast.dao.OrdersDao.findByPid"),column = "id")
    })
    List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName," +
            "departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime}," +
            "#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;




    @Select("select * from product where id = #{productId}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "productNum" ,column = "productNum"),
            @Result(property = "productName" ,column = "productName"),
            @Result(property = "cityName" ,column = "cityName"),
            @Result(property = "departureTime",column = "departureTime"),
            @Result(property = "productPrice" ,column = "productPrice"),
            @Result(property = "productDesc" ,column = "productDesc"),
            @Result(property = "productStatus" ,column = "productStatus"),
            @Result(property = "ordersList",many = @Many(select = "cn.itcast.dao.OrdersDao.findByPid"),column = "id")
    })
    Product findById(String productId) throws Exception;

}

package cn.itcast.dao;

import cn.itcast.domain.Product;
import cn.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    @Select("select t.* from traveller t,ORDER_TRAVELLER ot where t.id = ot.travellerId and ot.orderId = #{oid}")
    List<Traveller> findTravellersByOid(String oid) throws Exception;

}

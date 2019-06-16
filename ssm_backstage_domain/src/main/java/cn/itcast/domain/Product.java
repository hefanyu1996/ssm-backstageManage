package cn.itcast.domain;

import cn.itcast.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.List;

public class Product {

  private String id;
  private String productNum;
  private String productName;
  private String cityName;
  private Date departureTime;
  private String productPrice;
  private String productDesc;
  private Integer productStatus;

  private String departureTimeStr;
  private String productStatusStr;
  private List<Orders> ordersList;


  /**
   * 商品关联订单（一对多）
   * @return
   */
  public List<Orders> getOrdersList() {
    return ordersList;
  }

  public void setOrdersList(List<Orders> ordersList) {
    this.ordersList = ordersList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Date getDepartureTime() {
    return departureTime;
  }

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  public void setDepartureTime(Date departureTime) {
    this.departureTime = departureTime;
  }

  public String getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(String productPrice) {
    this.productPrice = productPrice;
  }

  public String getProductDesc() {
    return productDesc;
  }

  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }

  public Integer getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(Integer productStatus) {
    this.productStatus = productStatus;
  }

  public String getDepartureTimeStr() {
    if(departureTime !=null){
      departureTimeStr = DateUtils.date2String(departureTime,"yyyy-MM-dd HH:mm:ss");
    }
    return departureTimeStr;
  }

  public void setDepartureTimeStr(String departureTimeStr) {
    this.departureTimeStr = departureTimeStr;
  }

  public String getProductStatusStr() {
    if(productStatus!=null){
      if(productStatus == 1){
        productStatusStr = "开启";
      }else{
        productStatusStr = "关闭";
      }
    }
    return productStatusStr;
  }

  public void setProductStatusStr(String productStatusStr) {
    this.productStatusStr = productStatusStr;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id='" + id + '\'' +
            ", productNum='" + productNum + '\'' +
            ", productName='" + productName + '\'' +
            ", cityName='" + cityName + '\'' +
            ", departureTime=" + departureTime +
            ", productPrice='" + productPrice + '\'' +
            ", productDesc='" + productDesc + '\'' +
            ", productStatus=" + productStatus +
            ", departureTimeStr='" + departureTimeStr + '\'' +
            ", productStatusStr='" + productStatusStr + '\'' +
            '}';
  }
}

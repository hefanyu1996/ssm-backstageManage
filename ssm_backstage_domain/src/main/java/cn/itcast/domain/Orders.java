package cn.itcast.domain;

import cn.itcast.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

public class Orders {

  private String id;
  private String orderNum;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date orderTime;
  private String orderTimeStr;

  private Integer peopleCount;
  private String orderDesc;
  private Integer payType;
  private String payTypeStr;
  private Integer orderStatus;
  private String orderStatusStr;

  //关联游客  1:1
  private  Member member;

  //关联会员 n:n
  private List<Traveller> travellerList;

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public List<Traveller> getTravellerList() {
    return travellerList;
  }

  public void setTravellerList(List<Traveller> travellerList) {
    this.travellerList = travellerList;
  }

  public String getOrderStatusStr() {
    if(orderStatus!=null){
      if(orderStatus == 1){
        orderStatusStr =  "已支付";
      }else{
        orderStatusStr =  "未支付";
      }
    }
    return orderStatusStr;
  }

  public void setOrderStatusStr(String orderStatusStr) {
    this.orderStatusStr = orderStatusStr;
  }

  public String getPayTypeStr() {
    if(payType !=null){
      if(payType == 0){
        payTypeStr = "支付宝";
      }else if(payType == 1){
        payTypeStr = "微信";
      }else{
        payTypeStr = "其他";
      }
    }
    return payTypeStr;
  }

  public void setPayTypeStr(String payTypeStr) {
    this.payTypeStr = payTypeStr;
  }

  //  private String memberId;

  private Product product;

  /**
   * 订单商品关联（一对一）
   * @return
   */
  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }



  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  public Date getOrderTime() {
    return orderTime;
  }

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }

  public String getOrderTimeStr() {
    if(orderTime !=null){
      orderTimeStr = DateUtils.date2String(orderTime,"yyyy-MM-dd HH:mm:ss");
    }
    return orderTimeStr;
  }

  public void setOrderTimeStr(String orderTimeStr) {
    this.orderTimeStr = orderTimeStr;
  }

  public Integer getPeopleCount() {
    return peopleCount;
  }

  public void setPeopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public String getOrderDesc() {
    return orderDesc;
  }

  public void setOrderDesc(String orderDesc) {
    this.orderDesc = orderDesc;
  }


  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }


  @Override
  public String toString() {
    return "Orders{" +
            "id='" + id + '\'' +
            ", orderNum='" + orderNum + '\'' +
            ", orderTime=" + orderTime +
            ", orderTimeStr='" + orderTimeStr + '\'' +
            ", peopleCount=" + peopleCount +
            ", orderDesc='" + orderDesc + '\'' +
            ", payType=" + payType +
            ", payTypeStr='" + payTypeStr + '\'' +
            ", orderStatus=" + orderStatus +
            ", orderStatusStr='" + orderStatusStr + '\'' +
            ", member=" + member +
            ", travellerList=" + travellerList +
            ", product=" + product +
            '}';
  }
}

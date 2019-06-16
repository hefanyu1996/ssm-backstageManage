package cn.itcast.domain;

import org.springframework.core.annotation.Order;

import java.util.List;

public class Member {

  private String id;
  private String name;
  private String nickName;
  private String phoneNum;
  private String email;

  private List<Orders> ordersList;


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


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Member{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", nickName='" + nickName + '\'' +
            ", phoneNum='" + phoneNum + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}

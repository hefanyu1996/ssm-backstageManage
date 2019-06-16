package cn.itcast.domain;

import java.util.List;

public class Traveller {

  private String id;
  private String name;
  private String sex;
  private String phoneNum;
  private Integer credentialsType;
  private String credentialsTypeStr;

  private String credentialsNum;
  private Integer travellerType;
  private String travellerTypeStr;

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


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }


  public String getCredentialsNum() {
    return credentialsNum;
  }

  public void setCredentialsNum(String credentialsNum) {
    this.credentialsNum = credentialsNum;
  }

  public Integer getCredentialsType() {
    return credentialsType;
  }

  public void setCredentialsType(Integer credentialsType) {
    this.credentialsType = credentialsType;
  }

  public Integer getTravellerType() {
    return travellerType;
  }

  public void setTravellerType(Integer travellerType) {
    this.travellerType = travellerType;
  }

  public String getCredentialsTypeStr() {
    if(credentialsType != null){
      if(credentialsType == 0){
        credentialsTypeStr = "成人";
      }else if(credentialsType == 1){
        credentialsTypeStr = "儿童";
      }
    }
    return credentialsTypeStr;
  }

  public void setCredentialsTypeStr(String credentialsTypeStr) {
    this.credentialsTypeStr = credentialsTypeStr;
  }

  public String getTravellerTypeStr() {
    if(travellerType != null){
      if(travellerType == 0){
        travellerTypeStr = "身份证";
      }else if(travellerType == 1){
        travellerTypeStr = "护照";
      }else if(travellerType == 2){
        travellerTypeStr = "军官证";
      }
    }
    return travellerTypeStr;
  }

  public void setTravellerTypeStr(String travellerTypeStr) {
    this.travellerTypeStr = travellerTypeStr;
  }

  @Override
  public String toString() {
    return "Traveller{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", phoneNum='" + phoneNum + '\'' +
            ", credentialsType=" + credentialsType +
            ", credentialsTypeStr='" + credentialsTypeStr + '\'' +
            ", credentialsNum='" + credentialsNum + '\'' +
            ", travellerType=" + travellerType +
            ", travellerTypeStr='" + travellerTypeStr + '\'' +
            '}';
  }
}

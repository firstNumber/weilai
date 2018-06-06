package com.weilai.appuser.core.model;

import java.io.Serializable;

public class OrderShortPackage implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String batchNum;
    private String packageNum;
    private Integer packageStatus;
    private Integer reverseStatus;
    private Integer goodsStatus;
    private Integer driverId;
    private String driverName;
    private String driverPhone;
    private Integer totalWeight;
    private Integer totalVolume;
    private Integer goodsLength;
    private Integer goodsWidth;
    private Integer goodsHeight;
    private java.util.Date takingTime;
    private java.util.Date goodsTime;
    private java.util.Date overTime;
    private java.util.Date createTime;
    private java.util.Date updateTime;


}

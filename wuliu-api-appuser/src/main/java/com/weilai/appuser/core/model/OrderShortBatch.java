package com.weilai.appuser.core.model;

import java.io.Serializable;

public class OrderShortBatch implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String batchNum;
    private Integer batchStatus;
    private Integer reverseStatus;
    private String positiveBatchNum;
    private Integer orderType;
    private Integer driverId;
    private String driverName;
    private String driverPhone;
    private String agentNo;
    private String shipperCode;
    private String province;
    private Integer totalVolume;
    private Integer goodsLength;
    private Integer goodsWidth;
    private Integer goodsHeight;
    private Integer totalWeight;
    private Integer totalDist;
    private java.math.BigDecimal totalBasicsPrice;
    private java.math.BigDecimal totalNodePrice;
    private java.math.BigDecimal actualPrice;
    private java.math.BigDecimal cargoPrice;
    private java.math.BigDecimal finalPrice;
    private java.util.Date costTime;
    private java.util.Date orderTakingTime;
    private java.util.Date overTime;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private java.util.Date robPoolTime;


}

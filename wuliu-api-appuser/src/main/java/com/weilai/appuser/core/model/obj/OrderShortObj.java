package com.weilai.appuser.core.model.obj;

import java.io.Serializable;

import com.weilai.appuser.core.model.OrderShort;

public class OrderShortObj extends OrderShort implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer befReceiveDist;
	private Integer befSendDist;
	private String packageFlag;

	public Integer getBefReceiveDist() {
		return befReceiveDist;
	}

	public void setBefReceiveDist(Integer befReceiveDist) {
		this.befReceiveDist = befReceiveDist;
	}

	public Integer getBefSendDist() {
		return befSendDist;
	}

	public void setBefSendDist(Integer befSendDist) {
		this.befSendDist = befSendDist;
	}

	public String getPackageFlag() {
		return packageFlag;
	}

	public void setPackageFlag(String packageFlag) {
		this.packageFlag = packageFlag;
	}

}

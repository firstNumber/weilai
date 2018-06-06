package com.weilai.appuser.core.service;

import java.util.List;

import com.weilai.appuser.core.model.OrderShort;
import com.weilai.appuser.core.model.obj.OrderShortObj;
import com.weilai.common.service.BaseService;

public interface OrderShortService extends BaseService<OrderShort> {
	public void orderTest();

	public List<OrderShortObj> sort();

	public List<OrderShortObj> revokeSort(List<OrderShort> orders);
}

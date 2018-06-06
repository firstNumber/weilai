package com.weilai.appuser.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.appuser.core.dao.OrderShortBatchDao;
import com.weilai.appuser.core.model.OrderShortBatch;
import com.weilai.appuser.core.service.OrderShortBatchService;
import com.weilai.common.dao.BaseDao;
import com.weilai.common.service.impl.BaseServiceImpl;

@Service("orderShortBatchService")
public class OrderShortBatchServiceImpl extends BaseServiceImpl<OrderShortBatch> implements OrderShortBatchService {

	@Autowired
	private OrderShortBatchDao orderShortBatchDaoImpl;

	@Override
	public BaseDao<OrderShortBatch> getBaseDao() {
		return orderShortBatchDaoImpl;
	}

	@Override
	public void packageBatch() {

	}
}

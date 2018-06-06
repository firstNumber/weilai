package com.weilai.appuser.core.service;

import com.weilai.appuser.core.model.OrderShortBatch;
import com.weilai.common.service.BaseService;

public interface OrderShortBatchService extends BaseService<OrderShortBatch> {
	public void packageBatch();
}

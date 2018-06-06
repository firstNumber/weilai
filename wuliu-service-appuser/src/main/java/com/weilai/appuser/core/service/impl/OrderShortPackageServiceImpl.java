package com.weilai.appuser.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.common.dao.BaseDao;
import com.weilai.appuser.core.dao.OrderShortPackageDao;
import com.weilai.appuser.core.model.OrderShortPackage;
import com.weilai.appuser.core.service.OrderShortPackageService;


@Service("orderShortPackageService")
public class OrderShortPackageServiceImpl extends BaseServiceImpl<OrderShortPackage> implements OrderShortPackageService{

	@Autowired
	private OrderShortPackageDao orderShortPackageDaoImpl;
	
	@Override
	public BaseDao<OrderShortPackage> getBaseDao() {
		return orderShortPackageDaoImpl;
	}
}

package com.weilai.appuser.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.common.dao.BaseDao;
import com.weilai.appuser.core.dao.AppUserDao;
import com.weilai.appuser.core.model.AppUser;
import com.weilai.appuser.core.service.AppUserService;


@Service("appUserService")
public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements AppUserService{

	@Autowired
	private AppUserDao appUserDaoImpl;
	
	@Override
	public BaseDao<AppUser> getBaseDao() {
		return appUserDaoImpl;
	}
}

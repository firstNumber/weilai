package com.weilai.appuser.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.appuser.core.dao.AppUserDao;
import com.weilai.appuser.core.model.AppUser;
import com.weilai.appuser.core.service.AppUserService;
import com.weilai.common.dao.BaseDao;
import com.weilai.common.service.impl.BaseServiceImpl;

@Service("appUserService")
public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements AppUserService {

	@Autowired
	private AppUserDao appUserDaoImpl;

	@Override
	public BaseDao<AppUser> getBaseDao() {
		return appUserDaoImpl;
	}

	@Override
	public void updateAppUserName() {
		List<AppUser> userList = findAll();
		for (int i = 0; i < 2; i++) {
			try {
				update2(userList, i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void update2(List<AppUser> userList, Integer num) {
		try {
			if (num == 0) {
				userList.get(0).setNickName("杨幂");
				userList.get(1).setNickName("刘诗诗");
				update(userList.get(0));
				update(userList.get(1));
			}
			if (num == 1) {
				userList.get(2).setNickName("唐嫣");
				update(userList.get(2));
				userList.get(3).setNickName("景甜是个大美女,每个人都喜欢");
				update(userList.get(3));
			}

		} catch (Exception e) {
			throw e;
		}
	}
}

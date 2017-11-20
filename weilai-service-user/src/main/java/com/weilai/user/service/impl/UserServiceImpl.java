package com.weilai.user.service.impl;

import org.springframework.stereotype.Service;

import com.weilai.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public void queryUser() {
		System.out.println("Hello Service");
	}

}

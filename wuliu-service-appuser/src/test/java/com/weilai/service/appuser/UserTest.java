package com.weilai.service.appuser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weilai.appuser.core.service.AppUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml" })
public class UserTest {
	@Autowired
	AppUserService appUserService;

	@Test
	public void appUserTest() {
		appUserService.updateAppUserName();
	}
}

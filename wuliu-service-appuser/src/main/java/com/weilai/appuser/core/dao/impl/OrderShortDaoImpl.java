package com.weilai.appuser.core.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.weilai.appuser.core.dao.OrderShortDao;
import com.weilai.appuser.core.model.OrderShort;
import com.weilai.common.dao.impl.BaseDaoImpl;

@Scope("prototype")
@Repository("orderShortDaoImpl")
public class OrderShortDaoImpl extends BaseDaoImpl<OrderShort> implements OrderShortDao {

}

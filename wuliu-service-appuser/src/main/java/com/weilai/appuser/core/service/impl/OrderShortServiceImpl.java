package com.weilai.appuser.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weilai.appuser.core.dao.OrderShortDao;
import com.weilai.appuser.core.model.OrderShort;
import com.weilai.appuser.core.model.dto.OrderShortButionObj;
import com.weilai.appuser.core.model.obj.OrderShortObj;
import com.weilai.appuser.core.service.OrderShortService;
import com.weilai.common.dao.BaseDao;
import com.weilai.common.service.impl.BaseServiceImpl;
import com.weilai.common.util.LatLngUtil;
import com.weilai.common.util.PageBean;

@Service("orderShortService")
public class OrderShortServiceImpl extends BaseServiceImpl<OrderShort> implements OrderShortService {
	@Autowired
	private OrderShortDao orderShortDaoImpl;

	@Override
	public BaseDao<OrderShort> getBaseDao() {
		return orderShortDaoImpl;
	}

	@Override
	public void orderTest() {
		// List<OrderShort> orderLsit = findBy("statusIn", 42 + "," + 47);
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusIn", 42 + "," + 47);
		PageBean<OrderShort> pageBean = new PageBean<OrderShort>(1, 10);
		find4Page(pageBean, map, true);
		List<OrderShort> orderList = pageBean.getObjList();

		for (OrderShort order : orderList) {
			OrderShortButionObj orderShortButionObj = new OrderShortButionObj();
			BeanUtils.copyProperties(order, orderShortButionObj);
			i++;
			Integer totalVloumn = order.getGoodsLength() * order.getGoodsWidth() * order.getGoodsHeight();
			System.out.println(totalVloumn + "\t" + i);
		}

	}

	@Override
	public List<OrderShortObj> sort() {
		List<OrderShort> orders = findAll();
		return null;
	}

	@Override
	public List<OrderShortObj> revokeSort(List<OrderShort> orders) {
		if (orders.size() == 0) {
			return null;
		}

		List<OrderShortObj> list = new ArrayList<>();
		for (OrderShort order : orders) {
			OrderShortObj obj = new OrderShortObj();
			BeanUtils.copyProperties(order, obj);
			list.add(obj);
		}
		List<List<OrderShortObj>> classifyOrdersList = null;
		return null;

	}

	private List<List<OrderShortObj>> classifyOrders(List<OrderShortObj> orders) {
		// 存在仓库编码的
		List<OrderShortObj> exitStorageNoOrders = new ArrayList<>();
		// 不存在仓库编码的
		List<OrderShortObj> noExitStorageNoOrders = new ArrayList<>();
		/* 首先把有仓库编码的归类 */
		for (OrderShortObj order : orders) {
			if (order.getDkhCode() == null) {
				continue;
			}
			String currStroage = order.getStorageNo();
			if (StringUtils.isNotBlank(currStroage)) {
				exitStorageNoOrders.add(order);
			} else {
				noExitStorageNoOrders.add(order);
			}
		}

		/* 按照大客户编码再分类 */
		List<List<OrderShortObj>> exitsList = new ArrayList<>();
		// 最终完成分类
		List<List<OrderShortObj>> overExitStorageNoOrders = new ArrayList<>();
		if (exitStorageNoOrders.size() > 0) {
			OrderShortObj order = exitStorageNoOrders.get(0);
			classifyOdersByDkhcode(exitsList, exitStorageNoOrders, order);
			for (List<OrderShortObj> objs : exitsList) {
				String befStorageNo = objs.get(0).getStorageNo();
				classifyOrdersForExistStorageNo(overExitStorageNoOrders, objs, befStorageNo);
			}
		}

		List<List<OrderShortObj>> classifyOrdersList = new ArrayList<List<OrderShortObj>>();
		classifyOrdersList.addAll(overExitStorageNoOrders);
		return null;

	}

	private void classifyOdersByDkhcode(List<List<OrderShortObj>> list, List<OrderShortObj> orders,
			OrderShortObj order) {
		List<OrderShortObj> copyOrders = new ArrayList<>();
		copyOrders.addAll(orders);
		List<OrderShortObj> exists = new ArrayList<>();
		for (OrderShortObj obj : copyOrders) {
			if (obj.getDkhCode().equals(order.getDkhCode())) {
				exists.add(obj);
				orders.remove(obj);
			}
		}
		list.add(exists);
		if (orders.size() == 0) {
			return;
		}
		order = orders.get(0);
		classifyOdersByDkhcode(list, orders, order);
	}

	private void classifyOrdersForExistStorageNo(List<List<OrderShortObj>> overExitStorageNoOrders,
			List<OrderShortObj> existStorageNoOrders, String befStorageNo) {
		List<OrderShortObj> classifyOrders = new ArrayList<OrderShortObj>();
		for (OrderShortObj order : existStorageNoOrders) {
			String currStorageNo = order.getStorageNo();
			if (befStorageNo.equals(currStorageNo)) {
				classifyOrders.add(order);
			}
		}
		OrderShortObj firstOrder = getFirstOrder(classifyOrders);
		classifyOrders.remove(firstOrder);
		classifyOrders.add(0, firstOrder);
		/* 移除existStorageNoOrders归类的订单 */
		for (int j = 0; j < classifyOrders.size(); j++) {
			OrderShortObj order = classifyOrders.get(j);
			if (existStorageNoOrders.contains(order)) {
				existStorageNoOrders.remove(existStorageNoOrders);
			}
		}
		// 并对归类完成的订单排序
		for (int j = 0; j < classifyOrders.size(); j++) {
			OrderShortObj order = classifyOrders.get(j);
			long minDist = Long.MAX_VALUE;
			OrderShortObj minOrder = null;
			for (int i = j + 1; i < classifyOrders.size(); i++) {
				OrderShortObj o = classifyOrders.get(i);
				double currDist = LatLngUtil.distance(order.getReceiveLongitude(), order.getReceiveLatitude(),
						o.getReceiveLongitude(), o.getReceiveLatitude());
				if (currDist < minDist) {
					minDist = (long) currDist;
					minOrder = o;
				}
			}
			if (minOrder != null && j < classifyOrders.size() - 1) {
				classifyOrders.remove(minOrder);
				minOrder.setBefReceiveDist((int) minDist);
				classifyOrders.add(j + 1, minOrder);
			}
		}
		overExitStorageNoOrders.add(classifyOrders);
		if (existStorageNoOrders.size() > 0) {
			befStorageNo = existStorageNoOrders.get(0).getStorageNo();
			classifyOrdersForExistStorageNo(overExitStorageNoOrders, existStorageNoOrders, befStorageNo);
		}
	}

	private OrderShortObj getFirstOrder(List<OrderShortObj> orders) {
		OrderShortObj fristOrder = null;
		Integer distance = Integer.MAX_VALUE;
		for (OrderShortObj order : orders) {
			if (order.getDisreceDistance() < distance) {
				distance = order.getDisreceDistance();
				fristOrder = order;
			}
		}
		return fristOrder;
	}
}

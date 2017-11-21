package com.weilai.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.weilai.common.exception.BusiException;

public class MapUtil {
	public static final Integer SUCCESS_CODE = 1;// 默认成功状态码
	public static final Integer ERROR_CODE = 2; // 默认失败状态码
	public static final Integer ERROR_CODE_PART = 3; // 数组订单请求时，其中部分有异常的

	public static final Integer ERROR_USER_EXIT = 201; // 用户已存在
	public static final Integer ERROR_USER_NOT_EXIT = 202; // 用户不存在
	public static final Integer ERROR_USER_TYPE = 203; // 用户类型不对
	public static final Integer ERROR_USER_CODE = 204; // 验证码不正确
	public static final Integer ERROR_USER_NAME_PWD = 205; // 用户或密码错误
	public static final Integer ERROR_USER_BAN = 206; // 用户已被禁用
	public static final Integer ERROR_USER_RECOMMEND = 207; // 推荐人编码不存在
	public static final Integer ERROR_USER_ADMIN = 208; // 管理员信息已存在，不能重复录入
	public static final Integer ERROR_USER_PWD_LESS = 211; // 密码小于6位

	public static final Integer ERROR_USER_COMP_CONTENT = 221; // 业务内容不存在
	public static final Integer ERROR_USER_COMP_LEGALPERSONID = 222;// 法人身份证不能重复
	public static final Integer ERROR_USER_COMP_BUSINESSLICENCE = 223;// 营业执照不能重复

	public static final Integer ERROR_AUDIT_COMP_NOID = 240;// compInfoId不存在
	public static final Integer ERROR_AUDIT_COMP_REVOKE = 241;// 重复撤销
	public static final Integer ERROR_AUDIT_COMP = 242;// 审核失败
	public static final Integer ERROR_AUDIT_NULL = 243;// 返回内容为空
	public static final Integer ERROR_AUDIT_GRAB = 244;// 未开启抢单模式
	public static final Integer ERROR_AUDIT_GRAB_NULL = 245;// 抢单：没有符合规则的单子
	public static final Integer ERROR_AUDIT_USER = 246;// 无审核权限

	public static final Integer ERROR_AUTH = 401; // 权限验证错误
	public static final Integer ERROR_SYS = 500; // 系统错误
	public static final Integer ERROR_REPEAT_COMMIT = 600; // 重复提交
	public static final Integer ERROR_DOUBLE_LOGIN = 998; // 重复登录/互踢
	public static final Integer ERROR_MUST_LOGIN = 999; // 需要登录

	public static final Integer WARN_ORDER_DELIVERY_TIMEOUT_EXCEPTION = 101; // 订单确认送达超时异常
	/**
	 * 返回错误信息(错误码500)
	 * 
	 * @param message
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> error500() {
		return toMap(500, "网络异常,请刷新重试!", null);
	}

	/**
	 * 返回错误信息(错误码默认2)
	 * 
	 * @param message
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> error(String message) {
		return toMap(ERROR_CODE, message, null);
	}

	/**
	 * 返回错误信息
	 * 
	 * @param message
	 *            提示信息
	 * @param errorCode
	 *            错误码
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> error(String message, Integer errorCode) {
		return toMap(errorCode, message, null);
	}

	/**
	 * 返回正确结果
	 * 
	 * @param result
	 *            结果内容
	 * @param message
	 *            提示语
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> success(Object result, String message) {
		return toMap(SUCCESS_CODE, message, result);
	}

	/**
	 * 一些错误情况,也需要返回result
	* @param code
	* @param result
	* @param message
	* @return
	* @Author: liyongzhen
	* @Date: 2017年4月10日
	 */
	public static Map<String, Object> error(Integer code, Object result, String message) {
		return toMap(code, message, result);
	}

	/**
	 * 返回正确结果
	 * 
	 * @param result
	 *            结果内容
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> success(Object result) {
		return toMap(SUCCESS_CODE, "操作成功!", result);
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	public static Map<String, Object> success200() {
		return toMap(SUCCESS_CODE, "操作成功!", null);
	}

	/**
	 * 封装返回结果
	 * 
	 * @param code
	 * @param message
	 * @param result
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年3月31日
	 */
	private static Map<String, Object> toMap(Integer code, String message, Object result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", code);
		map.put("message", message);
		if (result != null)
			map.put("result", result);
		return map;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 分页数据的封装
	 * 
	 * @param objList
	 *            明细list
	 * @param totalRows
	 *            总记录数
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年5月17日
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> success4Page(List objList, Integer totalRows, Integer totalPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SUCCESS_CODE);// 状态固定为成功
		map.put("totalRow", totalRows);// 总记录数
		map.put("totalPage", totalPage);// 总页数
		map.put("message", "操作成功!");// 提示信息
		map.put("result", objList);// 结果明细
		return map;
	}

	/**
	 * 分页封装数据
	 * 
	 * @param pageBean
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年6月25日
	 */
	public static Map<String, Object> success4Page(PageBean<?> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", SUCCESS_CODE);// 状态固定为成功
		map.put("totalRow", pageBean.getTotalRows());// 总记录数
		map.put("totalPage", pageBean.getTotalPages());// 总页数
		map.put("message", "操作成功!");// 提示信息
		map.put("result", pageBean.getObjList());// 结果明细
		return map;
	}

	/**
	 * 将异常状态和信息返回
	 * 
	 * @param e
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年6月2日
	 */
	public static Map<String, Object> error(BusiException e) {
		return toMap(e.getErrorCode(), e.getMessage(), null);
	}

	/**
	 * 将错误状态和信息返回
	 * 
	 * @param resultBean
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年12月8日
	 */
	public static Map<String, Object> error(ResultBean<?> resultBean) {
		return toMap(resultBean.getStatus(), resultBean.getErrorMsg(), null);
	}
	
	/**
	 * 返回警告信息
	 * 
	 * @param message
	 *            提示信息
	 * @param warnCode
	 *            警告码
	 * @return
	 * @Author: xiedonghua
	 * @Date: 2017年8月8日
	 */
	public static Map<String, Object> warn(Integer warnCode, String message) {
		return toMap(warnCode, message, null);
	}

}

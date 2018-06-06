package com.weilai.common.util;

import java.text.DecimalFormat;

public class LatLngUtil {
	public static final double EA = 6378137; // 赤道半径
	public static final double EB = 6356725; // 极半径

	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param lng1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param lng2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 * @Author: liduo
	 * @Date: 2016年8月9日
	 */
	public static double distance(double lng1, double lat1, double lng2, double lat2) {
		double a, b;
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (lng1 - lng2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * EA * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	/*
	 * 获取范围内最大纬度
	 */
	private static double getDistanceMaxLat(double lat, int distance) {
		double angle = 0;
		double dy = distance * Math.cos(angle * Math.PI / 180.0);
		double ec = EB + (EA - EB) * (90.0 - lat) / 90.0;
		return (dy / ec + lat * Math.PI / 180.0) * 180.0 / Math.PI;
	}

	/*
	 * 获取范围内最小纬度
	 */
	private static double getDistanceMinLat(double lat, int distance) {
		double angle = 180;
		double dy = distance * Math.cos(angle * Math.PI / 180.0);
		double ec = EB + (EA - EB) * (90.0 - lat) / 90.0;
		return (dy / ec + lat * Math.PI / 180.0) * 180.0 / Math.PI;
	}

	/*
	 * 获取范围内最大经度
	 */
	private static double getDistanceMaxLng(double lat, double lng, int distance) {
		double angle = 90;
		double dx = distance * Math.sin(angle * Math.PI / 180.0);
		double ec = EB + (EA - EB) * (90.0 - lat) / 90.0;
		double ed = ec * Math.cos(lat * Math.PI / 180);
		return (dx / ed + lng * Math.PI / 180.0) * 180.0 / Math.PI;
	}

	/*
	 * 获取范围内最小经度
	 */
	private static double getDistanceMinLng(double lat, double lng, int distance) {
		double angle = 270;
		double dx = distance * Math.sin(angle * Math.PI / 180.0);
		double ec = EB + (EA - EB) * (90.0 - lat) / 90.0;
		double ed = ec * Math.cos(lat * Math.PI / 180);
		return (dx / ed + lng * Math.PI / 180.0) * 180.0 / Math.PI;
	}

	/**
	 * 以此lat,lng为圆心,distance为半径, 计算出范围内最大,最小的经纬度
	 * 注意这个计算出的是矩形(正方形)的坐标,范围要是原型,需要在代码或sql中再次过滤
	 * 
	 * @param lat
	 * @param lng
	 * @param distance
	 *            距离半径 单位米
	 * @return 数组[1=最大纬度maxLat,2=最小纬度minLat,3=最大经度maxLng,4=最小纬度minLng]
	 * @Author: wangxingfei
	 * @Date: 2016年8月15日
	 */
	public static float[] getRectRange(double lat, double lng, int distance) {
		double maxLat = getDistanceMaxLat(lat, distance);
		double minLat = getDistanceMinLat(lat, distance);
		double maxLng = getDistanceMaxLng(lat, lng, distance);
		double minLng = getDistanceMinLng(lat, lng, distance);
		float[] range = { Float.valueOf(maxLat + ""), Float.valueOf(minLat + ""), Float.valueOf(maxLng + ""),
				Float.valueOf(minLng + "") };
		// double[] range = {maxLat,minLat,maxLng,minLng};
		return range;
	}

	/**
	 * 若经纬度小数超过4位，只保留4位并返回String
	 * 
	 * @param latlng
	 * @param comFlag
	 *            是否以0补齐4位
	 * @return
	 * @Author: xiedonghua
	 * @Date: 2017年7月4日
	 */
	public static String format4(float latlng, boolean comFlag) {
		String str = String.valueOf(latlng);
		int index = str.indexOf('.');
		int len = 0;
		String de = "";
		String in = str.substring(0, index);
		if (index >= 0) {
			de = str.substring(index + 1);
			len = de.length();
			if (len > 4) {
				de = de.substring(0, 4);
				len = de.length();
			}
		}
		if (comFlag && len < 4) {
			for (int i = len; i < 4; i++) {
				de = de + "0";
			}
		}
		str = in + "." + de;
		return str;
	}

	/**
	 * 获取2点之间的距离 . 如果大于1000米,则返回 单位是km, 小于1000米,则单位是m; 例如 1500米则返回1.5km,
	 * 800米则返回800m
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return 如果有一个坐标为空或等0,则返回0km
	 * @Author: wangxingfei
	 * @Date: 2017年8月23日
	 */
	public static String getDistanceAndUnit(Double lng1, Double lat1, Double lng2, Double lat2) {
		if (lng1 == null || lat1 == null || lng2 == null || lat2 == null) {
			return "0km";
		}
		if (lng1 <= 0 || lat1 <= 0 || lng2 <= 0 || lat2 <= 0) {
			return "0km";
		}

		DecimalFormat df = new DecimalFormat("0.0");
		long distance = (long) LatLngUtil.distance(lng1, lat1, lng2, lat2);
		if (distance < 1000) {
			return distance + "m";
		} else {
			return df.format(distance / 1000) + "km";
		}
	}

	/**
	 * 获取2点之间的距离 . 如果大于1000米,则返回 单位是km, 小于1000米,则单位是m; 例如 1500米则返回1.5km,
	 * 800米则返回800m
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return 如果有一个坐标为空或等0,则返回0km
	 * @Author: wangxingfei
	 * @Date: 2017年8月23日
	 */
	public static String getDistanceAndUnit(Float lng1, Float lat1, Float lng2, Float lat2) {
		if (lng1 == null || lat1 == null || lng2 == null || lat2 == null) {
			return "0km";
		}
		if (lng1 <= 0 || lat1 <= 0 || lng2 <= 0 || lat2 <= 0) {
			return "0km";
		}

		DecimalFormat df = new DecimalFormat("0.0");
		long distance = (long) LatLngUtil.distance(lng1, lat1, lng2, lat2);
		if (distance < 1000) {
			return distance + "m";
		} else {
			return df.format(distance / 1000) + "km";
		}
	}

	public static void main(String[] args) {
		float[] range = getRectRange(39.91031265258789, 116.43924713134766, 5000);
		for (float f : range) {
			System.out.println(f);
		}
		System.out.println(distance(116.439117, 39.910431, 116.497894, 39.955296));
		System.out.println(distance(116.439117, 39.910431, 116.38047, 39.865448));
	}

}

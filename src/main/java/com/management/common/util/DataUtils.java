package com.management.common.util;

/**
 * 数据工具类
 * @author sawyer
 * @date 2016年8月14日
 */
public class DataUtils {

	/**
	 * 生成场地编号
	 * @author sawyer
	 * @date 2016年8月14日
	 * @return
	 */
	public static synchronized String generateSiteCode()
	{
		return String.valueOf(System.currentTimeMillis());
	}
	
	public static synchronized String generateGameCode()
	{
		return "G_"+String.valueOf(System.currentTimeMillis());
	}
}

package com.atguigu.team.service;
/**
 * 
 * @Description	表示员工的状态
 * @author zhouzhou
 * @version
 * @date 2023年5月2日上午9:16:17
 *
 */

//public class Status {
//	private final String NAME;
//	private Status(String name){
//		this.NAME = name;
//	}
//
//	public static final Status FREE = new Status("FREE");
//	public static final Status BUSY = new Status("BUSY");
//	public static final Status VOCATION = new Status("VOCATION");
//
//	public String getNAME() {
//		return NAME;
//	}
//
//	@Override
//	public String toString() {
//		return NAME;
//	}
//
//}

public enum Status{
	FREE,BUSY,VOCATION;
}

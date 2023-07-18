package com.atguigu.team.service;
/**
 * 
 * @Description	自定义异常类
 * @author zhouzhou
 * @version
 * @date 2023年5月2日上午10:13:27
 *
 */

public class TeamException extends Exception {
	
	static final long serialVersionUID = -3387516993124229948L;
	
	public TeamException(){
		super();
	}
	
	public TeamException(String msg){
		super(msg);
	}

}

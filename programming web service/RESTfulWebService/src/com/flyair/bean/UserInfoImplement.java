/**
 * 
 */
package com.flyair.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenchen Cheng
 *
 */
public class UserInfoImplement {
	protected List<UserInfo> userList = new ArrayList<UserInfo>();
	
	public List<UserInfo> getUserList(){
		UserInfo userInfo1 = new UserInfo();
		UserInfo userInfo2 = new UserInfo();
		UserInfo userInfo3 = new UserInfo();
		
		String[] user1 = {"000001","Jon","100000"};
		String[] user2 = {"000002","Arya","200000"};
		String[] user3 = {"000003","Sansa","300000"};
		
		
		userInfo1.setUserId(user1[0]);
		userInfo1.setUserName(user1[1]);
		userInfo1.setPassword(user1[2]);
		
		userInfo2.setUserId(user2[0]);
		userInfo2.setUserName(user2[1]);
		userInfo2.setPassword(user2[2]);
		
		userInfo3.setUserId(user3[0]);
		userInfo3.setUserName(user3[1]);
		userInfo3.setPassword(user3[2]);
		
		userList.add(userInfo1);
		userList.add(userInfo2);
		userList.add(userInfo3);
		
		return userList;
	
	}

}

/**
 * 
 */
package com.flyair.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.flyair.bean.UserInfo;
import com.flyair.bean.UserInfoImplement;



/**
 * @author Chenchen Cheng
 *
 */
@Path("/login")
public class LoginCheck {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{userName}/{password}")
	public String loginCheck(@PathParam("userName") String userName, @PathParam("password") String password)
			throws java.rmi.RemoteException {
		UserInfoImplement userData = new UserInfoImplement();

		// parameters: 0-non registered user; 1-registered user
		String returnValue = "User(name:"+ userName +") is non-registered user.";

		List<UserInfo> userList = userData.getUserList();

		if (null != userList && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {
				UserInfo user = userList.get(i);
				if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
					returnValue = "User(name:"+ userName +"; id:"+user.getUserId()+") has logined successfully.";
				}
			}
		}

		return returnValue;
	}
}

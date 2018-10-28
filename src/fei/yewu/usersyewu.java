package fei.yewu;

import fei.customer.users;

public interface usersyewu {
	//用户登录,返回的就是登录用户的信息(一个对象)
	public users login(users u);
	//用户注册
	public int register(users u);

}

package fei.dao.imple;

import java.util.ArrayList;
import java.util.List;

import fei.customer.users;
import fei.impledao.usersdao;

public class userdaoimpl implements usersdao {

	@Override
	public void adduser(users u) {
		String sql="insert into users(uname,upass,type) values(?,?,?)";
		List<Object> obj=new ArrayList<Object>();
		obj.add(u.getUname());
		obj.add(u.getUpass());
		obj.add(u.getType());
		basedao.operUpdate(sql, obj);
	}

	@Override
	public void deleteuser(int id) {
		String sql="delete from users where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(id);
		basedao.operUpdate(sql, obj);	
	}

	@Override
	public void updateuser(users u) {
		String sql="update users set uname=?,upass=?,type=? where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(u.getUname());
		obj.add(u.getUpass());
		obj.add(u.getType());
		obj.add(u.getId());
		basedao.operUpdate(sql, obj);
	}

	@Override
	public users queryuser1(int id) {
		List<users> ulist=new ArrayList<users>();
		String sql="select * from users where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(id);
		ulist=basedao.operQuery(sql, obj, users.class);
		if(ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}

	@Override
	public users queryuser2(String uname) {
		List<users> ulist=new ArrayList<users>();
		String sql="select * from users where uname=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(uname);
		ulist=basedao.operQuery(sql, obj, users.class);
		if(ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}

	@Override
	public List<users> queryuser3() {
		List<users> ulist=new ArrayList<users>();
		String sql="select * from users";
		ulist=basedao.operQuery(sql, null, users.class);
		return ulist;
	}
	
}

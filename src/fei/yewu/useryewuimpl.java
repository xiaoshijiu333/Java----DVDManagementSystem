package fei.yewu;

import java.util.ArrayList;
import java.util.List;

import fei.customer.users;
import fei.dao.imple.basedao;
import fei.dao.imple.userdaoimpl;
import fei.impledao.usersdao;

public class useryewuimpl implements usersyewu {
	//�ӿڵ�����ָ��ʵ�������
	private usersdao udao=null;
	public useryewuimpl() {
		udao=new userdaoimpl();
	}

	@Override
	public users login(users u) {
		List<users> ulist=new ArrayList<users>();
		String sql="select * from users where uname=? and upass=? and type=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(u.getUname());
		obj.add(u.getUpass());
		obj.add(u.getType());
		ulist=basedao.operQuery(sql, obj, users.class);
		if(ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}

	@Override
	public int register(users u) {
		if(udao.queryuser2(u.getUname())!=null){
			return 0;
			//"���û��Ѿ����ڣ������ظ�ע��";
		}
		else{
			udao.adduser(u);
			return 1;
			//"ע��ɹ�";
		}		
	}
}

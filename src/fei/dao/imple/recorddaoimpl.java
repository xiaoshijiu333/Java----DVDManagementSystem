package fei.dao.imple;

import java.util.ArrayList;
import java.util.List;

import fei.customer.record2;
import fei.customer.records;
import fei.dao.imple.basedao;
import fei.impledao.recorddao;

public class recorddaoimpl implements recorddao {
	@Override
	public records queryrec1(int did) {
		List<records> rlist=new ArrayList<records>();
		String sql="select * from records where did=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(did);
		rlist=basedao.operQuery(sql, obj, records.class);
		if(rlist.size()>0){
			return rlist.get(0);
		}
		return null;
	}
	@Override
	public records queryrec2(int rid) {
		List<records> rlist=new ArrayList<records>();
		String sql="select * from records where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(rid);
		rlist=basedao.operQuery(sql, obj, records.class);
		if(rlist.size()>0){
			return rlist.get(0);
		}
		return null;
	}
	
	@Override
	public void addrec(records rec) {
		String sql="insert into records(uid,did,lendtime,returntime) values(?,?,?,?)";
		List<Object> obj=new ArrayList<Object>();
		obj.add(rec.getUid());
		obj.add(rec.getDid());
		obj.add(rec.getLendtime());
		obj.add(rec.getReturntime());
		basedao.operUpdate(sql, obj);
	}

	@Override
	public void updaterec(records rec) {
		String sql="update records set uid=?,did=?,lendtime=?,returntime=? where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(rec.getUid());
		obj.add(rec.getDid());
		obj.add(rec.getLendtime());
		obj.add(rec.getReturntime());
		obj.add(rec.getId());
		basedao.operUpdate(sql, obj);
	}

	@Override
	public List<record2> queryallrec() {
		String sql="select r.id, u.uname, d.dname, r.lendtime, r.returntime from users u, dvds d, records r where u.id=r.uid and d.id=r.did";
		//多表查询语句，记住限制条件的写法
		List<record2> rlist=new ArrayList<record2>();
		rlist=basedao.operQuery(sql, null, record2.class);
		return rlist;
	}

	@Override
	public List<record2> queryallrecbyuname(String uname) {
		String sql="select r.id, u.uname, d.dname, r.lendtime, r.returntime from users u, dvds d, records r where u.id=r.uid and d.id=r.did and uname=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(uname);
		List<record2> rlist=new ArrayList<record2>();
		rlist=basedao.operQuery(sql, obj, record2.class);
		return rlist;
	}

	@Override
	public List<record2> queryallrecbydname(String dname) {
		String sql="select r.id, u.uname, d.dname, r.lendtime, r.returntime from users u, dvds d, records r where u.id=r.uid and d.id=r.did and dname=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(dname);
		List<record2> rlist=new ArrayList<record2>();
		rlist=basedao.operQuery(sql, obj, record2.class);
		return rlist;
	}

	//该方法用于查看指定用户的归还记录，flag=true表示已归还，false表示未归还
	@Override
	public List<record2> queryallrecflag(boolean flag, String uname) {
		String sql=null;
		if(flag){
			sql="select r.id,  u.uname, d.dname, r.lendtime, r.returntime from users u, dvds d, records r where u.id=r.uid and d.id=r.did and returntime is not null and uname=?";
		}
		else{
			sql="select r.id,  u.uname, d.dname, r.lendtime, r.returntime from users u, dvds d, records r where u.id=r.uid and d.id=r.did and returntime is null and uname=?";
		}
		List<Object> obj=new ArrayList<Object>();
		obj.add(uname);
		List<record2> rlist=new ArrayList<record2>();
		rlist=basedao.operQuery(sql, obj, record2.class);
		return rlist;
	}
}

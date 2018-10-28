package fei.dao.imple;

import java.util.ArrayList;
import java.util.List;

import fei.customer.dvds;
import fei.impledao.dvddao;

public class dvddaoimple implements dvddao {

	@Override
	public void adddvd(dvds d) {
		String sql="insert into dvds(dname,count,status) values(?,?,?)";
		List<Object> obj=new ArrayList<Object>();
		obj.add(d.getDname());
		obj.add(d.getCount());
		obj.add(d.getStatus());
		basedao.operUpdate(sql, obj);
	}

	@Override
	public void deletedvd(int id) {
		String sql="delete from dvds where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(id);
		basedao.operUpdate(sql, obj);
	}

	@Override
	public void updatedvd(dvds d) {
		String sql="update dvds set dname=?,count=?,status=? where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(d.getDname());
		obj.add(d.getCount());
		obj.add(d.getStatus());
		obj.add(d.getId());
		basedao.operUpdate(sql, obj);
	}

	@Override
	public dvds querydvd1(int id) {
		List<dvds> dList=new ArrayList<dvds>();
		String sql="select * from dvds where id=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(id);
		dList=basedao.operQuery(sql, obj, dvds.class);
		if(dList.size()>0){
			return dList.get(0);
		}
		return null;
	}

	@Override
	public dvds querydvd2(String dname) {
		List<dvds> dList=new ArrayList<dvds>();
		String sql="select * from dvds where dname=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(dname);
		dList=basedao.operQuery(sql, obj, dvds.class);
		if(dList.size()>0){
			return dList.get(0);
		}
		return null;
	}

	@Override
	public List<dvds> querydvd3() {
		List<dvds> dList=new ArrayList<dvds>();
		String sql="select * from dvds";
		dList=basedao.operQuery(sql, null, dvds.class);
		return dList;
	}

	@Override
	public List<dvds> querydvd4(int status) {
		List<dvds> dList=new ArrayList<dvds>();
		String sql="select * from dvds where status=?";
		List<Object> obj=new ArrayList<Object>();
		obj.add(status);
		dList=basedao.operQuery(sql, obj, dvds.class);
		return dList;
	}
	
}


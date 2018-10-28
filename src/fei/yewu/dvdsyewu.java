package fei.yewu;

import java.util.List;

import fei.customer.dvds;

public interface dvdsyewu {
	public void adddvd(dvds d);  					//添加DVD
	public void deletedvd(int id);				//删除DVD
	public void modifydvd(dvds d);				//修改DVD
	public List<dvds> queryalldvd();		//查询所有的DVD
	public dvds querydvdbyname(String dname); 	//根据名字查询相应的DVD
	public dvds querydvdbyid(int id);		//根据ID查询相应的DVD
	public int lenddvd(int did,int uid);		//按照DVD编号和用户编号租DVD
	public int returndvd(int did);			//还DVD，需要更新records表中的returntime记录
	public List<dvds> canlenddvd();				//可借DVD
	public List<dvds> haslenddvd();				//不可借DVD
}

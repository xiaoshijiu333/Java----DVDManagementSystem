package fei.yewu;

import java.util.List;

import fei.customer.record2;

public interface recordyewu {
	//查看指定用户的租赁记录
	public List<record2> queryuserrecord(String uname);
	//查看指定DVD的租赁记录
	public List<record2> querydvdrecord(String dname);
	//查看指定用户的已归还记录
	public List<record2> queryhasreturnrecord(String uname);
	//查看指定用户的未归还记录
	public List<record2> querynotreturnrecord(String uname);
	//查看所有的记录
	public List<record2> queryallrecord();
}

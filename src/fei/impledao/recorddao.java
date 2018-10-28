package fei.impledao;

import java.util.List;

import fei.customer.record2;
import fei.customer.records;

public interface recorddao {
	public records queryrec1(int did);	//根据DVD的id查询记录
	public records queryrec2(int rid);	//根据records的id查询记录
	public void addrec(records rec);	//保存记录
	public void updaterec(records rec);		//更新修改记录
	
	public List<record2> queryallrec();
	public List<record2> queryallrecbyuname(String uname);	//查看指定用户的所有租赁记录
	public List<record2> queryallrecbydname(String dname);
	public List<record2> queryallrecflag(boolean flag,String uname);	//查看指定用户的归还记录(用flag标志是否归还)
}

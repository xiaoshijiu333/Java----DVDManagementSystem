package fei.impledao;

import java.util.List;

import fei.customer.record2;
import fei.customer.records;

public interface recorddao {
	public records queryrec1(int did);	//����DVD��id��ѯ��¼
	public records queryrec2(int rid);	//����records��id��ѯ��¼
	public void addrec(records rec);	//�����¼
	public void updaterec(records rec);		//�����޸ļ�¼
	
	public List<record2> queryallrec();
	public List<record2> queryallrecbyuname(String uname);	//�鿴ָ���û����������޼�¼
	public List<record2> queryallrecbydname(String dname);
	public List<record2> queryallrecflag(boolean flag,String uname);	//�鿴ָ���û��Ĺ黹��¼(��flag��־�Ƿ�黹)
}

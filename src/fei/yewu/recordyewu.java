package fei.yewu;

import java.util.List;

import fei.customer.record2;

public interface recordyewu {
	//�鿴ָ���û������޼�¼
	public List<record2> queryuserrecord(String uname);
	//�鿴ָ��DVD�����޼�¼
	public List<record2> querydvdrecord(String dname);
	//�鿴ָ���û����ѹ黹��¼
	public List<record2> queryhasreturnrecord(String uname);
	//�鿴ָ���û���δ�黹��¼
	public List<record2> querynotreturnrecord(String uname);
	//�鿴���еļ�¼
	public List<record2> queryallrecord();
}

package fei.yewu;

import java.util.List;

import fei.customer.dvds;

public interface dvdsyewu {
	public void adddvd(dvds d);  					//���DVD
	public void deletedvd(int id);				//ɾ��DVD
	public void modifydvd(dvds d);				//�޸�DVD
	public List<dvds> queryalldvd();		//��ѯ���е�DVD
	public dvds querydvdbyname(String dname); 	//�������ֲ�ѯ��Ӧ��DVD
	public dvds querydvdbyid(int id);		//����ID��ѯ��Ӧ��DVD
	public int lenddvd(int did,int uid);		//����DVD��ź��û������DVD
	public int returndvd(int did);			//��DVD����Ҫ����records���е�returntime��¼
	public List<dvds> canlenddvd();				//�ɽ�DVD
	public List<dvds> haslenddvd();				//���ɽ�DVD
}

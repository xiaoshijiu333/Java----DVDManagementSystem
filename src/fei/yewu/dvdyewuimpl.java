package fei.yewu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import fei.customer.dvds;
import fei.customer.records;
import fei.dao.imple.dvddaoimple;
import fei.dao.imple.recorddaoimpl;
import fei.impledao.dvddao;
import fei.impledao.recorddao;

public class dvdyewuimpl implements dvdsyewu {
	private dvddao ddao=null;
	private recorddao rdao=null;
	public dvdyewuimpl(){
		ddao=new dvddaoimple();
		rdao=new recorddaoimpl();
	}
	@Override
	public void adddvd(dvds d) {
		ddao.adddvd(d);
	}

	@Override
	public void deletedvd(int id) {
		ddao.deletedvd(id);
	}

	@Override
	public void modifydvd(dvds d) {
		ddao.updatedvd(d);
	}

	@Override
	public List<dvds> queryalldvd() {
		return ddao.querydvd3();
	}

	@Override
	public dvds querydvdbyname(String dname) {
		return ddao.querydvd2(dname);
	}

	@Override
	public dvds querydvdbyid(int id) {
		return ddao.querydvd1(id);
	}

	@Override
	public int lenddvd(int did, int uid) {
		dvds d=ddao.querydvd1(did);
		if(d==null){
			return 0;//�����ڸ�DVD			
		}else{
			if(d.getStatus()==1){
				return 1;//��DVD�ѱ����				
			}else{
				d.setStatus(1); 	//����״̬�������Ѿ��ɹ����
				d.setCount(d.getCount()+1);		//���½������
				ddao.updatedvd(d);				//������dvds���и���
				records r=new records(uid, did, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);		
				//ÿ���һ��DVD���ͻ��Ӧ����һ��record��¼
				rdao.addrec(r);			//��������record��¼�������ݿ�
				return 2;//�ɹ��赽DVD			
			}
		}
	}

	@Override
	public int returndvd(int rid) {
		records r=rdao.queryrec2(rid);
		int did=r.getDid();
		if(r==null){			
			return 0;//�黹ʧ�ܣ����������DVD��ID�Ƿ���ȷ
		}else if(r.getReturntime()!=null){
			return 1;//�黹ʧ�ܣ���DVD�Ѿ��ɹ��黹
		}else{
			r.setReturntime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//���ù黹ʱ�䣬��ʾ�ɹ��黹
			rdao.updaterec(r);			//����¼���µ����ݿ�
			dvds d=ddao.querydvd1(did);
			d.setStatus(0);			//��Ҫ�����ˣ��黹�ɹ��˾�Ҫ����DVD��״̬Ϊ�ɽ�
			ddao.updatedvd(d);
			return 2;//DVD�黹�ɹ�	
		}
	}

	@Override
	public List<dvds> canlenddvd() {
		return ddao.querydvd4(0);
	}

	@Override
	public List<dvds> haslenddvd() {
		return ddao.querydvd4(1);
	}

}

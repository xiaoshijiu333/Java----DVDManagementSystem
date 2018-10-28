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
			return 0;//不存在该DVD			
		}else{
			if(d.getStatus()==1){
				return 1;//该DVD已被借出				
			}else{
				d.setStatus(1); 	//更新状态，代表已经成功借出
				d.setCount(d.getCount()+1);		//更新借出次数
				ddao.updatedvd(d);				//将整个dvds进行更新
				records r=new records(uid, did, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);		
				//每借出一个DVD，就会对应产生一个record记录
				rdao.addrec(r);			//将产生的record记录存入数据库
				return 2;//成功借到DVD			
			}
		}
	}

	@Override
	public int returndvd(int rid) {
		records r=rdao.queryrec2(rid);
		int did=r.getDid();
		if(r==null){			
			return 0;//归还失败，请检查输入的DVD的ID是否正确
		}else if(r.getReturntime()!=null){
			return 1;//归还失败，该DVD已经成功归还
		}else{
			r.setReturntime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//设置归还时间，表示成功归还
			rdao.updaterec(r);			//将记录更新到数据库
			dvds d=ddao.querydvd1(did);
			d.setStatus(0);			//不要忘记了，归还成功了就要设置DVD的状态为可借
			ddao.updatedvd(d);
			return 2;//DVD归还成功	
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

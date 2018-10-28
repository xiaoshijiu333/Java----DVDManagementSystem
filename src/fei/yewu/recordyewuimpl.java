package fei.yewu;

import java.util.List;

import fei.customer.record2;
import fei.dao.imple.recorddaoimpl;
import fei.impledao.recorddao;

public class recordyewuimpl implements recordyewu{
	private recorddao rdao=null;
	public recordyewuimpl(){
		rdao=new recorddaoimpl();
	}
	@Override
	public List<record2> queryuserrecord(String uname) {
		return rdao.queryallrecbyuname(uname);
	}

	@Override
	public List<record2> querydvdrecord(String dname) {
		return rdao.queryallrecbydname(dname);
	}

	@Override
	public List<record2> queryhasreturnrecord(String uname) {
		return rdao.queryallrecflag(true, uname);
	}

	@Override
	public List<record2> querynotreturnrecord(String uname) {
		return rdao.queryallrecflag(false, uname);
	}

	@Override
	public List<record2> queryallrecord() {
		return rdao.queryallrec();
	}

}

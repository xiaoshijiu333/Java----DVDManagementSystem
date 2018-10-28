package fei.shituceng;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fei.customer.record2;


public class RecordTableModel implements TableModel {
	
	private List<record2> rlist=null;
	
	public RecordTableModel(List<record2> rlist){
		this.rlist=rlist;
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int arg0) {
		if(arg0==0){
			return "租赁记录编号";
		}
		else if(arg0==1){
			return "租赁人名称";
		}
		else if(arg0==2){
			return "租赁DVD名称";
		}
		else if(arg0==3){
			return "借出时间";
		}else if(arg0==4){
			return "归还时间";
		}
		else{
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return rlist.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		record2 r=rlist.get(arg0);
		if(arg1==0){
			return r.getId();
		}
		else if(arg1==1){
			return r.getUname();
		}
		else if(arg1==2){
			return r.getDname();
		}
		else if(arg1==3){
			return r.getLendtime();
		}else if(arg1==4){
			return r.getReturntime();
		}else{
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}

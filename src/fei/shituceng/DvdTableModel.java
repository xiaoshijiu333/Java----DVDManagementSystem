package fei.shituceng;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fei.customer.dvds;

public class DvdTableModel implements TableModel {

	private List<dvds> dlist=null;
	
	public DvdTableModel(List<dvds> dlist) {
		this.dlist=dlist;
	}
	
	//Jtable显示的列数的数据类型
	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	//Jtable显示的数据的列数
	@Override
	public int getColumnCount() {
		return 4;
	}

	//Jtable显示的列名称
	@Override
	public String getColumnName(int arg0) {
		if(arg0==0){
			return "DVD编号";
		}
		else if(arg0==1){
			return "DVD名称";
		}
		else if(arg0==2){
			return "DVD借出次数";
		}
		else if(arg0==3){
			return "DVD状态";
		}
		else{
			return null;
		}
	}

	//Jtable显示的数据行数
	@Override
	public int getRowCount() {
		return dlist.size();
	}

	//获取Jtable指定单元格的数据(给出了具体的行数和列数)
	@Override
	public Object getValueAt(int arg0, int arg1) {
		dvds d=dlist.get(arg0);
		if(arg1==0){
			return d.getId();
		}
		else if(arg1==1){
			return d.getDname();
		}
		else if(arg1==2){
			return d.getCount();
		}
		else if(arg1==3){
			return (d.getStatus()==0 ? "可借" : "已借");
		}else{
			return null;
		}	
	}

	//设置Jtable表格的单元格是否可以编辑
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	//对应上面的单元格是否可以编辑，不可编辑就用不上了
	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}
}

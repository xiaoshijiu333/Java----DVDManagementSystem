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
	
	//Jtable��ʾ����������������
	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	//Jtable��ʾ�����ݵ�����
	@Override
	public int getColumnCount() {
		return 4;
	}

	//Jtable��ʾ��������
	@Override
	public String getColumnName(int arg0) {
		if(arg0==0){
			return "DVD���";
		}
		else if(arg0==1){
			return "DVD����";
		}
		else if(arg0==2){
			return "DVD�������";
		}
		else if(arg0==3){
			return "DVD״̬";
		}
		else{
			return null;
		}
	}

	//Jtable��ʾ����������
	@Override
	public int getRowCount() {
		return dlist.size();
	}

	//��ȡJtableָ����Ԫ�������(�����˾��������������)
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
			return (d.getStatus()==0 ? "�ɽ�" : "�ѽ�");
		}else{
			return null;
		}	
	}

	//����Jtable���ĵ�Ԫ���Ƿ���Ա༭
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	//��Ӧ����ĵ�Ԫ���Ƿ���Ա༭�����ɱ༭���ò�����
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

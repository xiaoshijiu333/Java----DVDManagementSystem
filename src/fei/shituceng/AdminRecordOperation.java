package fei.shituceng;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fei.customer.dvds;
import fei.customer.record2;
import fei.tool.dvdTool;
import fei.yewu.recordyewu;
import fei.yewu.recordyewuimpl;

public class AdminRecordOperation extends JInternalFrame {
	

private JPanel jp_table=null;
private JPanel jp_btn=null;
private JTable jt_table=null;
private JLabel jl_type=null;
private JTextField jt_text=null;
private JButton jb_search=null;
private JButton jb_exit=null;
private JComboBox<String> jc_opersearch=null;
private JScrollPane scr=null;
private recordyewu ryewu=null;
private List<record2> rlist=null;
private RecordTableModel rtable=null;

public AdminRecordOperation(){
	ryewu=new recordyewuimpl();
	rlist=new ArrayList<record2>();
	
	this.setTitle("DVD租赁记录查询");
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setClosable(true);
	this.setIconifiable(true);
	this.setBounds(50,40,700,400);
	this.setResizable(false);
	
	
	jp_table=new JPanel(new BorderLayout());
	jt_table=new JTable();
	jt_table.setRowHeight(25);
	
	refreshTable(rlist);
	
	scr=new JScrollPane(jt_table);
	jp_table.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"本人租赁记录查询"));
	jp_table.add(scr);
	
	jp_btn=new JPanel(new GridLayout(7,1,0,30));
	jl_type=new JLabel("查询类型");
	jb_search=new JButton("查询");
	jb_search.setFocusPainted(false);
	
	jb_search.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			rlist.clear();
			int index=jc_opersearch.getSelectedIndex();
			String text=jt_text.getText().trim();
			if(index==1&&text.equals("")){
				JOptionPane.showMessageDialog(AdminRecordOperation.this, "输入内容不能为空");
			}else if(index==2&&text.equals("")){
				JOptionPane.showMessageDialog(AdminRecordOperation.this, "输入内容不能为空");
			}else if(index==0){
				rlist=ryewu.queryallrecord();
			}else if(index==1){
				rlist=ryewu.queryuserrecord(text);
				if(rlist.size()==0){
					JOptionPane.showMessageDialog(AdminRecordOperation.this, "没有你要查找的租赁记录");
				}
			}else if(index==2){
				rlist=ryewu.querydvdrecord(text);
				if(rlist.size()==0){
					JOptionPane.showMessageDialog(AdminRecordOperation.this, "没有你要查找的租赁记录");
				}
			}
			refreshTable(rlist);
		}
	});
	
	jt_text=new JTextField();
	jt_text.setEditable(false);
	jb_exit=new JButton("退出");
	jb_exit.setFocusPainted(false);
	
	jb_exit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int flag=JOptionPane.showConfirmDialog(AdminRecordOperation.this, "是否确定退出","确定信息", JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.YES_OPTION){
				AdminRecordOperation.this.dispose();
			}				
		}
	});
	
	
	jc_opersearch=new JComboBox<String>(new String[]{"全部租赁记录","指定用户租赁记录","指定DVD租赁记录"});
	
	jc_opersearch.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int k=jc_opersearch.getSelectedIndex();
			if(k!=0){
				jt_text.setText("");
				jt_text.setEditable(true);
			}else{
				jt_text.setText("");
				jt_text.setEditable(false);
			}
		}
	});
	
	
	jp_btn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"查询条件"));
	jp_btn.add(jl_type);
	jp_btn.add(jc_opersearch);
	jp_btn.add(jt_text);
	jp_btn.add(jb_search);
	jp_btn.add(new JLabel());
	jp_btn.add(new JLabel());
	jp_btn.add(jb_exit);
	
	this.add(jp_table,BorderLayout.CENTER);
	this.add(jp_btn, BorderLayout.EAST);
	this.setVisible(true);
}

	//给jt_table绑定数据的方法
	public void refreshTable(List<record2> rlist){
		rtable=new RecordTableModel(rlist);
		jt_table.setModel(rtable);
	}


}
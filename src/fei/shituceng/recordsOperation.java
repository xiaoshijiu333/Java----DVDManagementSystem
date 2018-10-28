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

import fei.customer.dvds;
import fei.customer.record2;
import fei.customer.users;
import fei.dao.imple.dvddaoimple;
import fei.impledao.dvddao;
import fei.yewu.dvdsyewu;
import fei.yewu.dvdyewuimpl;
import fei.yewu.recordyewu;
import fei.yewu.recordyewuimpl;

public class recordsOperation extends JInternalFrame {
	private JPanel jp_table=null;
	private JPanel jp_btn=null;
	private JTable jt_table=null;
	private JLabel jl_type=null;
	private JButton jb_search=null;
	private JButton jb_return=null;
	private JButton jb_exit=null;
	private JComboBox<String> jc_opersearch=null;
	private JScrollPane scr=null;
	private recordyewu ryewu=null;
	private dvdsyewu dyewu=null;
	private List<record2> rlist=null;
	private RecordTableModel rtable=null;
	private users u=null;
	private dvddao ddao=null;
	
	public recordsOperation(users u){
		ddao=new dvddaoimple();
		this.u=u;
		ryewu=new recordyewuimpl();
		dyewu=new dvdyewuimpl();
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
				if(index==0){
					rlist=ryewu.queryuserrecord(u.getUname());
					if(rlist.size()==0){
						JOptionPane.showMessageDialog(recordsOperation.this, "没有与你相关的租赁记录");
					}
				}else if(index==1){
					rlist=ryewu.queryhasreturnrecord(u.getUname());
					if(rlist.size()==0){
						JOptionPane.showMessageDialog(recordsOperation.this, "没有已归还的租赁记录");
					}
				}else if(index==2){
					rlist=ryewu.querynotreturnrecord(u.getUname());
					if(rlist.size()==0){
						JOptionPane.showMessageDialog(recordsOperation.this, "没有未归还的租赁记录");
					}
				}
				refreshTable(rlist);
			}
		});
		
		jb_return=new JButton("还DVD");
		jb_return.setFocusPainted(false);
		
		jb_return.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row=jt_table.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(recordsOperation.this, "请选择需要还的DVD");
				}else{
					int rid=Integer.valueOf(jt_table.getValueAt(row, 0).toString());
					String dname=jt_table.getValueAt(row, 2).toString();
					int flag1=JOptionPane.showConfirmDialog(recordsOperation.this, "是否确定归还该DVD","确定信息", JOptionPane.YES_NO_OPTION);
					if(flag1==JOptionPane.YES_OPTION){
						int flag2=dyewu.returndvd(rid);
						if(flag2==1){
							JOptionPane.showMessageDialog(recordsOperation.this, "归还失败，该DVD已经归还");
						}else if(flag2==2){
							JOptionPane.showMessageDialog(recordsOperation.this, "成功归还 "+dname+" DVD");
						}
					}else{
						JOptionPane.showMessageDialog(recordsOperation.this, "归还DVD失败");
					}
				}
			}
		});
		
		jb_exit=new JButton("退出");
		jb_exit.setFocusPainted(false);
		
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int flag=JOptionPane.showConfirmDialog(recordsOperation.this, "是否确定退出","确定信息", JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					recordsOperation.this.dispose();
				}	
				
			}
		});
		
		jc_opersearch=new JComboBox<String>(new String[]{"本人全部租赁记录","已归还租赁记录","未归还租赁记录"});
		jp_btn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"查询条件"));
		jp_btn.add(jl_type);
		jp_btn.add(new JLabel());
		jp_btn.add(jc_opersearch);
		jp_btn.add(jb_search);
		jp_btn.add(jb_return);	
		jp_btn.add(new JLabel());
		jp_btn.add(jb_exit);
		
		this.add(jp_table,BorderLayout.CENTER);
		this.add(jp_btn, BorderLayout.EAST);
		this.setVisible(true);
	}
	
	public void refreshTable(List<record2> rlist){
		rtable=new RecordTableModel(rlist);
		jt_table.setModel(rtable);
	}

}

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
import javax.swing.border.Border;

import fei.customer.dvds;
import fei.customer.users;
import fei.tool.dvdTool;
import fei.yewu.dvdsyewu;
import fei.yewu.dvdyewuimpl;

public class DVDoperation extends JInternalFrame {
	//继承JInternalFrame类，它就必须依附在某个窗体内部
	private JPanel jp_table=null;
	private JPanel jp_butten=null;
	private JLabel jl_type=null;
	private JTable jt_table=null;
	private JComboBox<String> jc_opertype=null;
	private JTextField jt_text=null;
	private JButton jb_rend=null;
	private JButton jb_search=null;
	private JButton jb_exit=null;
	private JScrollPane scr=null;
	private List<dvds> dlist=null;
	private dvdsyewu dyewu=null;
	private DvdTableModel dtable=null;
	private users u=null;
	
	public DVDoperation(users u){
		this.u=u;
		dyewu=new dvdyewuimpl();
		dlist=new ArrayList<dvds>();
		
		this.setTitle("DVD查询租赁操作");
		this.setResizable(false);
		this.setClosable(true);			//窗体可以被关闭
		this.setIconifiable(true);		//窗体可最小化
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(50,40,700,400);
		
		jp_table=new JPanel(new BorderLayout());
		//设置边框
		jp_table.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),"查询信息"));
		jt_table=new JTable();
		scr=new JScrollPane(jt_table);
		jp_table.add(scr);
		jt_table.setRowHeight(25);
		
		refreshTable(dlist);
		
		jp_butten=new JPanel(new GridLayout(7,1,0,30));
		jl_type=new JLabel("查询类型");
		jc_opertype=new JComboBox<String>(new String[]{"全部DVD","可借DVD","已借DVD","指定DVD名称","指定DVD的ID"});		
		jt_text=new JTextField();
		jt_text.setEditable(false);
		
		jc_opertype.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int count=jc_opertype.getSelectedIndex();
				jt_text.setText("");
				if(count==3||count==4){
					jt_text.setEditable(true);
				}else{				
					jt_text.setEditable(false);
				}
			}
		});
		
		jb_search=new JButton("查询");
		jb_search.setFocusPainted(false);
		jb_rend=new JButton("租DVD");
		jb_rend.setFocusPainted(false);
		
		jb_rend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row=jt_table.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(DVDoperation.this, "请选择需要租的DVD");
				}else{
					Object id=jt_table.getValueAt(row, 0);
					String dname=jt_table.getValueAt(row, 1).toString();
					int value=Integer.valueOf(id.toString());
					int flag1=JOptionPane.showConfirmDialog(DVDoperation.this, "是否确定租该DVD","确定信息", JOptionPane.YES_NO_OPTION);
					if(flag1==JOptionPane.YES_OPTION){
						int flag2=dyewu.lenddvd(value, u.getId());
						if(flag2==1){
							JOptionPane.showMessageDialog(DVDoperation.this, "租赁失败，该DVD已经被借出");
						}else if(flag2==2){
							JOptionPane.showMessageDialog(DVDoperation.this, "成功租到 "+dname+" DVD");
						}
					}else{
						JOptionPane.showMessageDialog(DVDoperation.this, "租DVD失败");
					}
				}
			}
		});
		
		
		jb_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dlist.clear();
				int index=jc_opertype.getSelectedIndex();				
				String values=jt_text.getText().trim();
				if(index==3&&values.equals("")){
					JOptionPane.showMessageDialog(DVDoperation.this, "输入内容不能为空");
				}else if(index==4&&values.equals("")){
					JOptionPane.showMessageDialog(DVDoperation.this, "输入内容不能为空");
				}else if(index==4&&dvdTool.isNumber(values)==false){
					JOptionPane.showMessageDialog(DVDoperation.this, "只能输入数字");
				}else if(index==0){
					dlist=dyewu.queryalldvd();
				}else if(index==3){
					dvds d=dyewu.querydvdbyname(values);
					if(d==null){
						JOptionPane.showMessageDialog(DVDoperation.this, "没有你要查找的DVD");
					}else{
						dlist.add(d);
					}					
				}else if(index==4){
					dvds d=dyewu.querydvdbyid(Integer.valueOf(values));
					if(d==null){
						JOptionPane.showMessageDialog(DVDoperation.this, "没有你要查找的DVD");
					}else{
						dlist.add(d);
					}	
				}else if(index==1){
					dlist=dyewu.canlenddvd();
					if(dlist.size()==0){
						JOptionPane.showMessageDialog(DVDoperation.this, "没有可借的DVD");
					}
				}else if(index==2){
					dlist=dyewu.haslenddvd();
					if(dlist.size()==0){
						JOptionPane.showMessageDialog(DVDoperation.this, "没有已借的DVD");
					}
				}
				refreshTable(dlist);
			}
		});
		
		jb_exit=new JButton("退出");
		jb_exit.setFocusPainted(false);
		
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int flag=JOptionPane.showConfirmDialog(DVDoperation.this, "是否确定退出","确定信息", JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					DVDoperation.this.dispose();
				}				
			}
		});
		
		//设置边框
		jp_butten.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"查询条件"));
		jp_butten.add(jl_type);
		jp_butten.add(jc_opertype);
		jp_butten.add(jt_text);	
		jp_butten.add(jb_search);
		jp_butten.add(new JLabel());
		jp_butten.add(jb_rend);	
		jp_butten.add(jb_exit);
		
		this.add(jp_table,BorderLayout.CENTER);
		this.add(jp_butten,BorderLayout.EAST);
		this.setVisible(true);
	}
	
	public void refreshTable(List<dvds> dlist){
		dtable=new DvdTableModel(dlist);
		jt_table.setModel(dtable);
	}
}

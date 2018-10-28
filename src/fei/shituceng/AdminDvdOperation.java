package fei.shituceng;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fei.customer.dvds;
import fei.tool.dvdTool;
import fei.yewu.dvdsyewu;
import fei.yewu.dvdyewuimpl;

public class AdminDvdOperation extends JInternalFrame {
	private JPanel jp_table=null;
	private JScrollPane scr=null;
	private JPanel jp_butten=null;
	private JPanel jp_search=null;
	private JButton jb_search=null;
	private JComboBox<String> jc_searchtype=null;
	private JTextField jt_text=null;
	private JTable jt_table=null;
	private JButton jb_add=null;
	private JButton jb_delete=null;
	private JButton jb_update=null;
	private JButton jb_exit=null; 
	private dvdsyewu dyewu=null;
	private List<dvds> dlist=null;
	private DvdTableModel dtable=null;
	
	public AdminDvdOperation(){
		dyewu=new dvdyewuimpl();
		dlist=new ArrayList<dvds>();
		this.setTitle("����ԱDVD����");
		this.setResizable(false);
		this.setClosable(true);			//������Ա��ر�
		this.setIconifiable(true);		//�������С��
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(50,40,700,400);
		
		jp_table=new JPanel(new BorderLayout());	
		//���ñ߿�
		jp_table.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),"��ѯ��Ϣ"));
		jt_table=new JTable();
		//��table��ģ�ͣ��������
		refreshTable(dlist);
		scr=new JScrollPane(jt_table);	
		//���ñ����и�
		jt_table.setRowHeight(25);
			
		jp_search=new JPanel(new GridLayout(1,3,50,0));
		jc_searchtype=new JComboBox<String>(new String[]{"ȫ��DVD","�ɽ�DVD","�ѽ�DVD","ָ��DVD���Ʋ���","ָ��DVD��Ų���"});
		jt_text=new JTextField();
		jt_text.setEditable(false);
		
		jc_searchtype.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int count=jc_searchtype.getSelectedIndex();
				jt_text.setText("");
				if(count==3||count==4){
					jt_text.setEditable(true);
				}else{				
					jt_text.setEditable(false);
				}
			}
		});
		
		jb_search=new JButton("��ѯ");
		jb_search.setFocusPainted(false);
		jp_search.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"��ѯ����"));
		jp_search.add(jc_searchtype);
		jp_search.add(jt_text);
		jp_search.add(jb_search);
		
		jb_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dlist.clear();
				int index=jc_searchtype.getSelectedIndex();				
				String values=jt_text.getText().trim();
				if(index==3&&values.equals("")){
					JOptionPane.showMessageDialog(AdminDvdOperation.this, "�������ݲ���Ϊ��");
				}else if(index==4&&values.equals("")){
					JOptionPane.showMessageDialog(AdminDvdOperation.this, "�������ݲ���Ϊ��");
				}else if(index==4&&dvdTool.isNumber(values)==false){
					JOptionPane.showMessageDialog(AdminDvdOperation.this, "ֻ����������");
				}else if(index==0){
					dlist=dyewu.queryalldvd();
				}else if(index==3){
					dvds d=dyewu.querydvdbyname(values);
					if(d==null){
						JOptionPane.showMessageDialog(AdminDvdOperation.this, "û����Ҫ���ҵ�DVD");
					}else{
						dlist.add(d);
					}					
				}else if(index==4){
					dvds d=dyewu.querydvdbyid(Integer.valueOf(values));
					if(d==null){
						JOptionPane.showMessageDialog(AdminDvdOperation.this, "û����Ҫ���ҵ�DVD");
					}else{
						dlist.add(d);
					}	
				}else if(index==1){
					dlist=dyewu.canlenddvd();
					if(dlist.size()==0){
						JOptionPane.showMessageDialog(AdminDvdOperation.this, "û�пɽ��DVD");
					}
				}else if(index==2){
					dlist=dyewu.haslenddvd();
					if(dlist.size()==0){
						JOptionPane.showMessageDialog(AdminDvdOperation.this, "û���ѽ��DVD");
					}
				}
				refreshTable(dlist);
			}
		});
	    
		
		
		jp_table.add(scr,BorderLayout.CENTER);
		jp_table.add(jp_search,BorderLayout.SOUTH);
		
		jp_butten=new JPanel(new GridLayout(7, 1,0,30));
		jb_add=new JButton("���DVD");
		jb_add.setFocusPainted(false);
		
		jb_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new addDvd();
			}
		});
		
		jb_delete=new JButton("ɾ��DVD");
		jb_delete.setFocusPainted(false);
		
		jb_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row=jt_table.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(AdminDvdOperation.this, "��ѡ����Ҫɾ��������");
				}else{
					Object id=jt_table.getValueAt(row, 0);
					int value=Integer.valueOf(id.toString());
					int flag=JOptionPane.showConfirmDialog(AdminDvdOperation.this, "�Ƿ�ȷ��ɾ����DVD","ȷ����Ϣ", JOptionPane.YES_NO_OPTION);
					if(flag==JOptionPane.YES_OPTION){
						dyewu.deletedvd(value);
					}else{
						JOptionPane.showMessageDialog(AdminDvdOperation.this, "ɾ��ʧ��");
					}
				}
			}
		});
		
		jb_update=new JButton("����DVD");
		jb_update.setFocusPainted(false);
		
		jb_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row=jt_table.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(AdminDvdOperation.this, "��ѡ����Ҫ���µ�����");
				}else{
					Object id=jt_table.getValueAt(row, 0);
					int value=Integer.valueOf(id.toString());
					new updateDvd(value);
				}
				
			}
		});
		
		jb_exit=new JButton("�˳�");
		jb_exit.setFocusPainted(false);
		//���ñ߿�
		
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int flag=JOptionPane.showConfirmDialog(AdminDvdOperation.this, "�Ƿ�ȷ���˳�","ȷ����Ϣ", JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					AdminDvdOperation.this.dispose();
				}			
			}
		});
		
		jp_butten.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"����ѡ��"));
		jp_butten.add(new JLabel());
		jp_butten.add(new JLabel());
		jp_butten.add(jb_add);
		jp_butten.add(jb_delete);
		jp_butten.add(jb_update);
		jp_butten.add(new JLabel());
		jp_butten.add(jb_exit);
		
		this.add(jp_table,BorderLayout.CENTER);
		this.add(jp_butten,BorderLayout.EAST);
		this.setVisible(true);
	}		
	
	//��jt_table�����ݵķ���
	public void refreshTable(List<dvds> dlist){
		dtable=new DvdTableModel(dlist);
		jt_table.setModel(dtable);
	}
	
	}


package fei.shituceng;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fei.customer.dvds;
import fei.tool.dvdTool;
import fei.yewu.dvdsyewu;
import fei.yewu.dvdyewuimpl;

public class updateDvd  extends JFrame{
	private JPanel jp_main=null;
	private JPanel[] jp=null;
	private JLabel jl_name=null;
	private JTextField jt_name=null;
	private JLabel jl_count=null;
	private JTextField jt_count=null;
	private JLabel jl_type=null;
	private JComboBox<String> jc_type=null;
	private JButton jb_confirm=null;
	private JButton jb_exit=null;
	private dvdsyewu dyewu=null;
	
	public updateDvd(int value){
		dyewu=new dvdyewuimpl();
		this.setTitle("���DVD");
		this.setResizable(false);
		this.setBounds(250,300,350,250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		jp_main=new JPanel(new GridLayout(5,1,0,5));
		jl_name=new JLabel("DVD����:");
		jt_name=new JTextField(15);
		jl_count=new JLabel("�������:");
		jt_count=new JTextField(15);
		jl_type=new JLabel("DVD״̬:");
		jc_type=new JComboBox<String>(new String[]{"�ɽ�","�ѽ�"});
		jc_type.setPreferredSize(new Dimension(170, 23));
		jb_confirm=new JButton("ȷ��");
		jb_confirm.setFocusPainted(false);
		jb_exit=new JButton("ȡ��");
		jb_exit.setFocusPainted(false);
		
		jb_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dname=jt_name.getText().trim();
				String count=jt_count.getText().trim();
				int status=jc_type.getSelectedIndex();
				if(dname.equals("")){
					JOptionPane.showMessageDialog(updateDvd.this,"DVD������Ϊ��");
				}else if(count.equals("")){
					JOptionPane.showMessageDialog(updateDvd.this,"�����������Ϊ��");
				}else if(!dvdTool.isNumber(count)){
					JOptionPane.showMessageDialog(updateDvd.this,"�������ֻ����д����");
				}else{					
					int flag=JOptionPane.showConfirmDialog(updateDvd.this, "�Ƿ�ȷ������DVD","ȷ����Ϣ", JOptionPane.YES_NO_OPTION);
					if(flag==JOptionPane.YES_OPTION){
						dvds d=new dvds(value,dname, Integer.valueOf(count), status);
						dyewu.modifydvd(d);
						JOptionPane.showMessageDialog(updateDvd.this, "���³ɹ���");
						updateDvd.this.dispose();
					}else{
						JOptionPane.showMessageDialog(updateDvd.this, "����ʧ�ܣ�");
						jt_name.setText("");
						jt_count.setText("");
					}
				}
			}
		});
		
		
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int flag=JOptionPane.showConfirmDialog(updateDvd.this, "�Ƿ�ȷ���˳�","ȷ����Ϣ", JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					updateDvd.this.dispose();
				}	
				
			}
		});
		
		jp=new JPanel[4];
		for(int i=0;i<4;i++){
			jp[i]=new JPanel();
		}
		
		jp[0].add(jl_name);
		jp[0].add(jt_name);
		jp[1].add(jl_count);
		jp[1].add(jt_count);
		jp[2].add(jl_type);
		jp[2].add(jc_type);
		jp[3].add(jb_confirm);
		jp[3].add(jb_exit);
		jp_main.add(new JLabel());
		jp_main.add(jp[0]);
		jp_main.add(jp[1]);
		jp_main.add(jp[2]);
		jp_main.add(jp[3]);
		
		this.getContentPane().add(jp_main);
		this.setVisible(true);
	}
}

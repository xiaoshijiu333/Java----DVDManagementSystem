package fei.shituceng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import fei.customer.users;

public class Useroperation extends JFrame implements Runnable{
	private JPanel jp_main=null;
	private JPanel jp_wel=null;
	private JPanel jp_btn=null;
	private JDesktopPane jp_desk=null;
	private JLabel jb_wel=null;
	private JLabel jb_icon=null;
	private ImageIcon image=null;
	private JButton btn_user_operation=null;
	private JButton btn_dvd_records=null;
	private JButton btn_exit=null;
	private Thread t=new Thread(this);
	private users u=null;
	
	public Useroperation(users u){
		this.u=u;
		this.setTitle("用户操作窗口");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(150,150,950,550);
		
		jp_main=new JPanel(new BorderLayout());
		jp_btn=new JPanel(new GridLayout(7, 1,0,35));
		btn_user_operation=new JButton("DVD查询租赁操作");
		
		//给这个按钮添加监听器，让继承JInternalFrame的窗口依附在桌面面板上
		btn_user_operation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DVDoperation dvdo=new DVDoperation(u);
				jp_desk.add(dvdo);
				dvdo.toFront();			//让它永远存在于最上层
			}
		});
		
		//让按钮中的哪个框框消失
		btn_user_operation.setFocusPainted(false);
		btn_dvd_records=new JButton("DVD租赁记录查询");
		btn_dvd_records.setFocusPainted(false);
		
		//给这个按钮添加监听器，让继承JInternalFrame的窗口依附在桌面面板上
		btn_dvd_records.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				recordsOperation recOper=new recordsOperation(u);
				jp_desk.add(recOper);
				recOper.toFront();
			}
		});
		
		btn_exit=new JButton("退出");
		btn_exit.setFocusPainted(false);
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int flag=JOptionPane.showConfirmDialog(Useroperation.this, "是否确定退出","确定信息", JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					Useroperation.this.dispose();
					new loginView();
				}					
			}
		});
		
		
		jp_btn.add(new JPanel());
		jp_btn.add(new JPanel());
		jp_btn.add(btn_user_operation);
		jp_btn.add(btn_dvd_records);
		jp_btn.add(btn_exit);
		jp_btn.add(new JPanel());
		jp_btn.add(new JPanel());
		jp_btn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"快捷功能区"));
		
		jp_wel=new JPanel();
		jb_wel=new JLabel("欢  迎  "+u.getUname()+"  使  用  影  碟  租  赁  管  理  系  统");
		jb_wel.setFont(new Font("宋体", Font.BOLD, 25));
		jb_wel.setForeground(Color.blue);
		jp_wel.add(jb_wel);
		
		jp_desk=new JDesktopPane();
		image=new ImageIcon(this.getClass().getResource("2.png"));
		jb_icon=new JLabel(image);
		jb_icon.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		//这里add方法的第二个参数的意思是把这个空间添加到桌面面板的哪个位置，定义一个最小值代表放在最底层
		jp_desk.add(jb_icon, new Integer(Integer.MIN_VALUE));
		
		jp_main.add(jp_btn, BorderLayout.EAST);
		jp_main.add(jp_wel,BorderLayout.NORTH);
		jp_main.add(jp_desk, BorderLayout.CENTER);
		
		t.start();
		
		this.getContentPane().add(jp_main);
		this.setVisible(true);
	}

	//利用线程让欢迎面板中的字一直动起来
	@Override
	public void run() {
		while(true){
			for(int i=950;i>-750;i--){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				jb_wel.setLocation(i, 5);
			}
		}
	}
	
}

package xueshengguanli;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
	Addstu 增加学生界面;
	Updatastu 修改学生界面;
	Delstu 删除学生界面;
	AddC 增加课程界面;
	DelC 删除课程界面;
	UpdateC 修改课程界面;
	AddSC 增加选课界面;
	DelSC 删除选课界面;
	UpdateSC 修改选课界面;
	Selstu 学生查询界面;
	JPanel pCenter;
	CardLayout card=null;
	JLabel label=null;

	JMenuBar mb=new JMenuBar();//菜单栏
	JMenu m1=new JMenu("学生管理");
	JMenuItem add1=new JMenuItem("添加学生   ");
	JMenuItem updata1=new JMenuItem("更新学生   ");
	JMenuItem delete1=new JMenuItem("删除学生 ");

	JMenu m3=new JMenu("成绩管理");
	JMenuItem add3=new JMenuItem("添加成绩   ");
	JMenuItem updata3=new JMenuItem("更新成绩  ");
	JMenuItem delete3=new JMenuItem("删除成绩  ");
	JMenu m4=new JMenu("查询管理");
	JMenuItem 学生查询=new JMenuItem("查询信息   ");
	JMenuItem m5=new JMenuItem("系统退出");
	Font t=new Font ("sanerif",Font.PLAIN,12);
	public Menu (){
		this.setTitle("成绩管理系统");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch(Exception e){System.err.println("不能设置外观:   "+e);}
		//组合菜单
		addMenu1();
		addMenu3();
		addMenu4();
		addJMenuBar();
		setJMenuBar(mb);
		ImageIcon image=new ImageIcon("744.jpg");
		label=new JLabel(image);
		this.add(label);
		this.setIconImage(new ImageIcon("744.jpg").getImage());
		/*label=new JLabel("选课管理系统",JLabel.CENTER);
	label.setFont(new Font("宋体",Font.BOLD,36));
	label.setHorizontalTextPosition(SwingConstants.CENTER);
	label.setForeground(Color.red);*/
		//点击事件
		add1.addActionListener(this);
		updata1.addActionListener(this);
		delete1.addActionListener(this);
		m5.addActionListener(this);
		add3.addActionListener(this);
		delete3.addActionListener(this);
		updata3.addActionListener(this);
		学生查询.addActionListener(this);

		card=new CardLayout();
		pCenter=new JPanel();
		pCenter.setLayout(card);


		增加学生界面=new Addstu();
		修改学生界面=new Updatastu(); 
		删除学生界面=new Delstu();
		增加课程界面=new AddC();
		删除课程界面=new DelC();
		修改课程界面=new UpdateC();
		增加选课界面=new AddSC();
		删除选课界面=new DelSC();
		修改选课界面=new UpdateSC();
		学生查询界面=new Selstu();

		pCenter.add("欢迎界面",label);
		pCenter.add("增加学生界面",增加学生界面);
		pCenter.add("修改学生界面",修改学生界面);
		pCenter.add("删除学生界面",删除学生界面);
		pCenter.add("增加课程界面",增加课程界面);
		pCenter.add("删除课程界面",删除课程界面);
		pCenter.add("修改课程界面",修改课程界面);
		pCenter.add("增加选课界面",增加选课界面);
		pCenter.add("删除选课界面",删除选课界面);
		pCenter.add("修改选课界面",修改选课界面);
		pCenter.add("学生查询界面", 学生查询界面);

		add(pCenter,BorderLayout.CENTER);
		validate();
		setVisible(true);
		setBounds(400,150,500,380);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter(){//关闭程序时的操作
			public void windowClosing(WindowEvent e){
				System.exit(0);}
		});
		validate();
	}

	private void addJMenuBar() {
		mb.add(m1);
		mb.add(m3);mb.add(m4);mb.add(m5);
	}
	private void addMenu4() {
		m4.add(学生查询);
		m4.setFont(t);
	}
	private void addMenu3() {
		m3.add(add3);
		m3.add(updata3);
		m3.add(delete3);
		m3.setFont(t);
	}

	private void addMenu1() {
		m1.add(add1);
		m1.add(updata1);
		m1.add(delete1);
		m1.setFont(t);//字体
	}

	public void actionPerformed(ActionEvent e){
		Object obj=e.getSource();
		if(obj==m5){
			System.exit(0);
		}else{
			if(obj==add1){
				card.show(pCenter,"增加学生界面");
			}else{
				if(obj==updata1){
					card.show(pCenter,"修改学生界面");
				}else{
					if(obj==delete1){
						card.show(pCenter, "删除学生界面");
					}else{
						if(obj==add3){
							card.show(pCenter, "增加选课界面");
						}else{
							if(obj==delete3){
								card.show(pCenter, "删除选课界面");
							}else{
								if(obj==updata3){
									card.show(pCenter, "修改选课界面");
								}else{
									if(obj==学生查询){
										card.show(pCenter, "学生查询界面");
									}
								}
							}
						}
					}
				}
			}
		}
	}



	public static void main(String[] args) {
		new Menu();
	}
}

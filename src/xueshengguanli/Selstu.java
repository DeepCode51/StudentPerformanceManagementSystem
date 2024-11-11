package xueshengguanli;

	import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
	public class Selstu extends JPanel implements ActionListener{
		JTextField 学号,姓名,系别;
		JButton 查找;
		JTextField 课号,课名;
		JButton 查找1;
		JTextField 课号1,学号1,成绩;
		JButton 查找2;

		public Selstu (){
			学号=new JTextField(12);
			姓名=new JTextField(12);
			系别=new JTextField(12);
			课号=new JTextField(12);
			课名=new JTextField(12);
			课号1=new JTextField(12);
			学号1=new JTextField(12);
			成绩=new JTextField(12);
			查找=new JButton("查找学生信息");
			查找1=new JButton("查找科目信息");
			查找2=new JButton("查看成绩信息");
		
			
			Box box1=Box.createHorizontalBox();//横放box
			Box box2=Box.createHorizontalBox();
			Box box4=Box.createHorizontalBox();
			Box box5=Box.createHorizontalBox();
			Box box6=Box.createHorizontalBox();
			Box box7=Box.createHorizontalBox();
			box1.add(new JLabel("学号:",JLabel.CENTER));
			box1.add(学号);
			box1.add(new JLabel("姓名:",JLabel.CENTER));
			box1.add(姓名);
			box1.add(new JLabel("系别:",JLabel.CENTER));
			box1.add(系别);
			box2.add(查找);
			
			box4.add(new JLabel("课号:",JLabel.CENTER));
			box4.add(课号);
			box4.add(new JLabel("课名:",JLabel.CENTER));
			box4.add(课名);
			box6.add(查找1);
			
			box5.add(new JLabel("课号:",JLabel.CENTER));
			box5.add(课号1);
			box5.add(new JLabel("学号:",JLabel.CENTER));
			box5.add(学号1);
			box5.add(new JLabel("成绩:",JLabel.CENTER));
			box5.add(成绩);
			box7.add(查找2);

			Box boxH1=Box.createVerticalBox();//竖放box
			boxH1.add(box1);
			boxH1.add(box2);
			boxH1.add(Box.createVerticalGlue());
			Box boxH2=Box.createVerticalBox();//竖放box
			boxH2.add(box4);
			boxH2.add(box6);
			boxH2.add(Box.createVerticalGlue()); 
			Box boxH3=Box.createVerticalBox();//竖放box
			boxH3.add(box5);
			boxH3.add(box7);
			boxH3.add(Box.createVerticalGlue()); 
	               
			查找.addActionListener(this);
			查找1.addActionListener(this);
			查找2.addActionListener(this);
	        
			JPanel messPanel=new JPanel();
			JPanel picPanel=new JPanel();
			JPanel threePanel=new JPanel();
			messPanel.add(boxH1);
			picPanel.add(boxH2);
			threePanel.add(boxH3);
			setLayout(new BorderLayout());
			JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
			add(splitV,BorderLayout.CENTER);
			JSplitPane splitV1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitV,threePanel);//分割
			add(splitV1,BorderLayout.CENTER);
			validate();
			
		
		}
		
		public void actionPerformed(ActionEvent c){
			Jdbc jd=new Jdbc();
			Object obj=c.getSource();
			Statement stmt=null;
			ResultSet rs=null;
			int row=0;
			int i=0;
		    String sql=null;
		    Students K;
		    SelC K1;
		    SelSC K2;
			if(obj==查找){
				if(学号.getText().equals("")&&姓名.getText().equals("")&&系别.getText().equals("")){
					sql="select * from S ";
					System.out.print("000");//000
				}
				else{
					if(学号.getText().equals("")){
						if(姓名.getText().equals("")){
							sql="select * from S where Sx like'%"+系别.getText()+"%'";
							System.out.print("001");}
						else{
							if(系别.getText().equals("")){
								sql="select * from S where Sname like'%"+姓名.getText()+"%'";
								System.out.print("010");}
						    else{
						    	sql="select * from S where Sname like'%"+姓名.getText()+"%'and Sx like'%"+系别.getText()+"%'";
						    	System.out.print("011");}}}
					else{
						if(姓名.getText().equals("")){
						     if(系别.getText().equals("")){
						    	 sql="select * from S where Sno like'%"+学号.getText()+"%'";
						    	 System.out.print("100");}
					         else{
					        	 sql="select * from S where Sno like'%"+学号.getText()+"%' and Sx like'%"+系别.getText()+"%'";
					        	 System.out.print("101");}}
					     else{
					    	 if(系别.getText().equals("")){
					    		 sql="select * from S where  Sno like'%"+学号.getText()+"%' and Sname like'%"+姓名.getText()+"%'";
					    		 System.out.print("110");}
					          else{
					        	  sql="select * from S where  Sno like'%"+学号.getText()+"%' and Sname like'%"+姓名.getText()+"%' and Sx like'%"+系别.getText()+"%'";
					        	  System.out.print("111");
					        	  }
					    	 }
						}
			}
				K=new Students(sql);
			 }
			else{if(obj==查找1){
				if(课号.getText().equals("")&&课名.getText().equals("")){
					sql="select * from C ";
					System.out.print("00");//00
				}
				else{
					if(课号.getText().equals("")){
						sql="select * from C where Cname like'%"+课名.getText()+"%'";
						System.out.print("01");
					}
					else{
						if(系别.getText().equals("")){
							sql="select * from C where Cno like'%"+课号.getText()+"%'";
							System.out.print("10");
						}else{
							sql="select * from C where  Cno like'%"+课号.getText()+"%' and Cname like'%"+姓名.getText()+"%'";
							System.out.print("11");
						 }
					}
				}
				 K1=new SelC(sql);}
			else{
				if(obj==查找2){
					if(课号1.getText().equals("")&&学号1.getText().equals("")&&成绩.getText().equals("")){
						sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where C.Cno=SC.Cno and S.Sno=SC.Sno";						
					}
				else{
					if(课号1.getText().equals("")){
						if(学号1.getText().equals("")){
							sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where C like'%"+成绩.getText()+"%' and C.Cno=SC.Cno and S.Sno=SC.Sno";
							System.out.print("001");}
						else{
							if(成绩.getText().equals("")){
								sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where SC.Sno like'%"+学号1.getText()+"%' and C.Cno=SC.Cno and S.Sno=SC.Sno";
								System.out.print("010");}
						    else{
						    	sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where SC.Sno like'%"+学号1.getText()+"%'and C like'%"+成绩.getText()+"%' and C.Cno=SC.Cno and S.Sno=SC.Sno";
						    	System.out.print("011");
						    	}
							}
						}
					else{if(学号1.getText().equals("")){
						      if(成绩.getText().equals("")){
						    	  sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where SC.Cno like'%"+课号1.getText()+"%' and C.Cno=SC.Cno and S.Sno=SC.Sno";
						    	  System.out.print("100");
						    	  }
					          else{
					        	  sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where SC.Cno like'%"+课号1.getText()+"%' and C like'%"+成绩.getText()+"%' and C.Cno=SC.Cno and S.Sno=SC.Sno";
					        	  System.out.print("101");
					        	  }
						      }
					     else{
					    	 if(成绩.getText().equals("")){
					    		 sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where  SC.Cno like'%"+课号1.getText()+"%' and SC.Sno like'%"+学号1.getText()+"%' and C.Cno=SC.Cno and S.Sno=SC.Sno";
					    		 System.out.print("110");
					    	 } else{
					    		 sql="select SC.Cno,Cname,SC.Sno,Sname,C from SC,C,S where  SC.Cno like'%"+课号1.getText()+"%' and SC.Sno like'%"+学号1.getText()+"%' and C like'%"+成绩.getText()+"%' and C.Cno=SC.Cno and S.Sno=SC.Sno";
					    		 System.out.print("111");
					    		 }
					    	 }
					}
				}
				K2=new SelSC(sql);
				}
			}
			}
			}
		
//		连接数据库方法
		/*public static Connection CONN(){
			   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //加载JDBC驱动
			   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";   //连接服务器和数据库test
			   String userName = "sa";   //默认用户名
			   String userPwd = "whs1993";   //密码
			   Connection dbConn=null;

			   try {
			   Class.forName(driverName);
			   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
			   } catch (Exception e) {
			   e.printStackTrace();
			   }
			   return dbConn;
		}*/
	}

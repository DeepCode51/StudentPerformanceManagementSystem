package xueshengguanli;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Updatastu extends JPanel implements ActionListener{
	String save=null;
	JTextField 学号1,学号,姓名,系别;
	JButton 修改,查找;
	
public Updatastu(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	学号1=new JTextField(12);
	学号=new JTextField(12);
	姓名=new JTextField(12);
	系别=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	box1.add(new JLabel("学号:",JLabel.CENTER));
	box1.add(学号);
	box2.add(new JLabel("姓名:",JLabel.CENTER));
	box2.add(姓名);
	box3.add(new JLabel("系别:",JLabel.CENTER));
	box3.add(系别);
	box4.add(修改);
	box5.add(new JLabel("学号:",JLabel.CENTER));
	box5.add(学号1);
	box5.add(查找);
	
	修改.addActionListener(this);
    查找.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Jdbc jd=new Jdbc();
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC;
	
	if(obj==查找){if(学号1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写查询的学号！" );
	else{
	     
	    sql1="select * from S where Sno='"+学号1.getText()+"'";
	    try{
	    Connection dbConn1=jd.CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){
	    	学号.setText(rs1.getString("Sno").trim());
	        姓名.setText(rs1.getString("Sname").trim());
	        系别.setText(rs1.getString("Sx").trim());
	        save=学号1.getText();	    	
	    }
	    else{
	    	JOptionPane.showMessageDialog(this,"没有这个学号的学生" );}
	    	stmt.close();
	    	rs1.close();
	    }catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==修改){if(save==null){JOptionPane.showMessageDialog(this,"还没查找需要修改的学生" );}
	else{
		if(学号.getText().equals("")||姓名.getText().equals("")||系别.getText().equals("")){
			JOptionPane.showMessageDialog(this,"学生信息填满才能修改！" );
		}
		else{sql="update S set Sno='"+学号.getText()+"',Sname='"+姓名.getText()+"',Sx='"+系别.getText()+"'"+"where Sno='"+save+"'";
		if(save.trim().equals(学号.getText().trim())){
		try{
		    Connection dbConn1=jd.CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"修改完成" );
			学号.setText("");
            姓名.setText("");
            系别.setText("");
			stmt.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
	}
		else{sql1="select * from S where Sno='"+学号.getText()+"'";
		try{
		    Connection dbConn1=jd.CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	
		    	JOptionPane.showMessageDialog(this,"已存在此学号学生" );
		    }
		    else{
		    	sqlSC="update SC set Sno='"+学号.getText()+"' where Sno='"+save+"'";
		    	stmt.executeUpdate(sql);
		    	stmt.executeUpdate(sqlSC);
		    	save=null;
		    	JOptionPane.showMessageDialog(null,"修改完成" );
		    	学号.setText("");
		    	姓名.setText("");
		    	系别.setText("");
            }
		    	stmt.close();
		    	rs1.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		
		}
		
	}}}}
}



}

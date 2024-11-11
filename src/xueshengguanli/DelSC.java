package xueshengguanli;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class DelSC extends JPanel implements ActionListener{
	String saveC=null;
	String saveS=null;
	JTextField 课号1,学号1,学号,课号,成绩;
	JButton 删除,查找;
	
public DelSC(){
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			System.err.println("不能设置外观:   "+e);
			}
	
		学号1=new JTextField(12);
		课号1=new JTextField(12);
		课号=new JTextField(12);
		学号=new JTextField(12);
		成绩=new JTextField(12);
		删除=new JButton("删除");
		查找=new JButton("查找");
	
		Box box1=Box.createHorizontalBox();//横放box
		Box box2=Box.createHorizontalBox();
		Box box3=Box.createHorizontalBox();
		Box box4=Box.createHorizontalBox();
		Box box5=Box.createHorizontalBox();
		box1.add(new JLabel("课号:",JLabel.CENTER));
		box1.add(课号);
		box2.add(new JLabel("学号:",JLabel.CENTER));
		box2.add(学号);
		box3.add(new JLabel("成绩:",JLabel.CENTER));
		box3.add(成绩);
		box4.add(删除);
		box5.add(new JLabel("课号:",JLabel.CENTER));
		box5.add(课号1);
		box5.add(new JLabel("学号:",JLabel.CENTER));
		box5.add(学号1);
		box5.add(查找);
		Box boxH=Box.createVerticalBox();//竖放box
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
	
		删除.addActionListener(this);
		查找.addActionListener(this);
	
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
    String sql=null,sql1=null;
	
	if(obj==查找){
		if(课号1.getText().equals("")||学号1.getText().equals(""))
			JOptionPane.showMessageDialog(this,"请填写完成查询的信息！" );
		else{
	     
			sql1="select * from SC where Cno='"+课号1.getText()+"' and Sno='"+学号1.getText()+"'";
			try{
				Connection dbConn1=jd.CONN();
				stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs1=stmt.executeQuery(sql1);
				if(rs1.next()){
					课号.setText(rs1.getString("Cno").trim());
	                学号.setText(rs1.getString("Sno").trim());
	                成绩.setText(rs1.getString("C").trim());
	                saveC=课号1.getText().trim();	
	                saveS=学号1.getText().trim();
				}else{
					JOptionPane.showMessageDialog(this,"没有这个课号的学生" );}
					stmt.close();
					rs1.close();
			}catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	    }
	}else{
		if(obj==删除){
			if(saveC==null||saveS==null)
				JOptionPane.showMessageDialog(this,"还没查找需要修改的学生/课程" );
			else{
				sql="delete from SC where Cno='"+saveC+"' and Sno='"+saveS+"'";
				try{
					Connection dbConn1=jd.CONN();
					stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					stmt.executeUpdate(sql);
					saveC=null;
					saveS=null;
					JOptionPane.showMessageDialog(this,"删除完成" );
					课号.setText("");
					学号.setText("");
					成绩.setText("");
					stmt.close();
				}catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
			}
		}
	}
}

}

package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame{

    
    MiniStatement(String pinnumber){
      
        
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel mini = new JLabel();
       // mini.setBounds(20, 140,400,200);
        add(mini);
        
        JLabel bank = new JLabel("Nirala Bank");
        bank.setBounds(150,20,300,20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);
        
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("Select * from login where pin = '"+pinnumber+"'");
            while(rs.next()){
                card.setText("Card Number : " + rs.getString("cardnumber").substring(0,4) + "xxxxxxxx" + rs.getString("cardnumber").substring(12));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Conn conn = new Conn();
            int bal =0;
             ResultSet rs = conn.s.executeQuery("Select * from bank where pin = '"+pinnumber+"'");
             while(rs.next()){
                  mini.setText(mini.getText() + "<html>" + rs.getString("data") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");  
                   if(rs.getString("type").equals("Deposite")){
                      bal += Integer.parseInt(rs.getString("amount"));
                }else {
                      bal -= Integer.parseInt(rs.getString("amount"));
                }
             }
             balance.setText("Your Current balance is Rs: " +bal);
             
        }catch(Exception e){
            System.out.println(e);
        }
       
        mini.setBounds(20, 140,400,200);
         
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MiniStatement("");
    }
}

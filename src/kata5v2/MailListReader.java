/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class MailListReader {
    
    public static ArrayList<String> read (String nameFile) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{
        ArrayList<String> mailList = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection conect = DriverManager.getConnection("jdbc:sqlite:KATA.DB");
        Statement state = conect.createStatement();
        String query = "SELECT * FROM MAILS";
        ResultSet rs = state.executeQuery(query);
        
        BufferedReader reader = new BufferedReader(new FileReader(new File(nameFile)));
        String mail;
        
                         
        while (rs.next()){
            mail=rs.getString("MAIL");
            if(!mail.contains("@")) continue;
            mailList.add(mail);
        }
        rs.close();
        state.close();
        conect.close();
        return mailList;
        
    }
    
}

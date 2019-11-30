/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sana
 */
public class dbConnectivity {
    Connection con;
    Statement stmt;
    
    dbConnectivity() //cons
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");  
            String s = "jdbc:sqlserver://localhost:1433;databaseName=Donations";
            con = DriverManager.getConnection(s,"sa","12345678");
            stmt = con.createStatement();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void displayAll()
    {
        
        try
        {
            ResultSet rs=stmt.executeQuery("select * from project");
             while(rs.next())
             {
                 
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void addProject(String n, String d, int b){
        ResultSet rs = null;
        int id = 0;
        try {
            rs = stmt.executeQuery("select top 1 projectID from Project order by projectID desc");
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            id++;
            int i=stmt.executeUpdate("insert into Project (projectID, projectName, projectDescription, requiredBudget, projectManager) values ("+id+",'"+n+"','"+d+"','"+b+"',' ')");
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    List<String> getVolunteers(){
        ResultSet rs = null;
        List<String> vol = new ArrayList<String>();
        try {
            rs = stmt.executeQuery("select volunteerName from volunteer");
            int i = 1;
            while(rs.next()){
                vol.add(rs.getString(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vol;
    }
    
    List<String> getTeam(String proj){
        ResultSet rs = null;
        List<String> team = null;
        String temp = null;
        try {
            rs = stmt.executeQuery("select team from project where projectName='"+proj+"'");
            int i = 1;
            while(rs.next()){
                temp = rs.getString(i);
            }
            if (temp!=null){
                String[] arr = temp.split(",");
                team = new ArrayList<String>(Arrays.asList(arr));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return team;
    }
    
    void setTeamMember(String s, String p){
        ResultSet rs = null;
        String temp = null;
        try
        {
            rs = stmt.executeQuery("select team from project where projectName='"+p+"'");
            while(rs.next()){
                temp = rs.getString(1);
            }
            if (temp != null){
                temp = temp.concat(",");
                temp = temp.concat(s);
            }
            else{
                temp = s;
            }
            int i=stmt.executeUpdate("update project set team='"+temp+"' where projectName='"+p+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    
    void setPM(String s, String p){
        try
        {
            int i=stmt.executeUpdate("update project set projectManager='"+s+"' where projectName='"+p+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void removeTM(String name, String proj){
        ResultSet rs = null;
        String temp = null;
        String temp1 = null;
        try
        {
            rs = stmt.executeQuery("select team from project where projectName='"+proj+"'");
            while(rs.next()){
                temp = rs.getString(1);
            }
            if (temp != null){
                int i = temp.indexOf(name);
                int l = name.length();
                i+=l;
                if (temp.charAt(i)==','){
                    name = name.concat(",");
                }
                temp = temp.replace(name, "");
            }
            int i=stmt.executeUpdate("update project set team='"+temp+"' where projectName='"+proj+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    List<String> getProjects(){
        ResultSet rs = null;
        List<String> proj = new ArrayList<String>();
        String temp = null;
        try {
            rs = stmt.executeQuery("select projectName from Project");
            int i = 1;
            while(rs.next()){
                temp = rs.getString(i);
                proj.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proj;
    }
    
    String getDesc(String name){
        ResultSet rs = null;
        String desc = null;
        try {
            rs = stmt.executeQuery("select projectDescription from Project where projectName='"+name+"'");
            int i = 1;
            while(rs.next()){
                desc = rs.getString(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desc;
    }
    
    String getPM(String proj){
        ResultSet rs = null;
        String temp = null;
        try {
            rs = stmt.executeQuery("select projectManager from Project where projectName='"+proj+"'");
            int i = 1;
            while(rs.next()){
                temp = rs.getString(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    String searchPMName(String name){
        ResultSet rs = null;
        String temp = null;
        try {
            rs = stmt.executeQuery("select projectName from Project where projectManager='"+name+"'");
            int i = 1;
            while(rs.next()){
                temp = rs.getString(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    Boolean isProj(String name){
        ResultSet rs = null;
        String temp = null;
        Boolean ret = true;
        try {
            rs = stmt.executeQuery("select count(projectName) from Project where projectName='"+name+"'");
            int i = 1;
            while(rs.next()){
                temp = rs.getString(i);
            }
            if (Integer.parseInt(temp) == 0){
                ret = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    void addDonor(String name, String proj, int type, int quant){
        ResultSet rs = null;
        int fundID = 0;
        int projID = 0;
        try {
            rs = stmt.executeQuery("select top 1 fundID from Funding order by fundID desc");
            while(rs.next()){
                fundID = rs.getInt(1);
            }
            fundID++;
            rs = stmt.executeQuery("select projectID from Project where projectName='"+proj+"'");
            while(rs.next()){
                projID = rs.getInt(1);
            }
            int i=stmt.executeUpdate("insert into Funding (fundID, fundType, fundAmount, projectID) values ("+fundID+","+type+","+quant+","+projID+")");
            rs = stmt.executeQuery("select top 1 donorID from Donor order by donorID desc");
            int donorID = 0;
            while(rs.next()){
                donorID = rs.getInt(1);
            }
            donorID++;
            String funds = fundID+"";
            i=stmt.executeUpdate("insert into Donor (donorID, donorName, fundsGiven) values ("+donorID+",'"+name+"','"+funds+"')");
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 

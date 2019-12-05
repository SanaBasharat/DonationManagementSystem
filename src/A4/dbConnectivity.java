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
    
    void addApplicant (String p, Beneficiary b){
        ResultSet rs = null;
        int count = 0;
        int benID = 0;
        try {
            rs = stmt.executeQuery("select count(*) BeneficiaryID from Beneficiary");
            while(rs.next()){
                count = rs.getInt(1);
            }
            if (count!=0){
                rs = stmt.executeQuery("select top 1 BeneficiaryID from Beneficiary order by BeneficiaryID desc");
                while(rs.next()){
                    benID = rs.getInt(1);
                }
            }
            benID++;
            int i=stmt.executeUpdate("insert into Beneficiary (BeneficiaryID,BeneficiaryName,BeneficiaryAddress,BeneficiaryPhone,BeneficiaryDOB,BeneficiaryIncome) values ("+benID+",'"+b.getName()+"','"+b.getAddress()+"','"+b.getPhoneNo()+"','"+b.getDob()+"',"+b.getIncome()+")");
            rs = stmt.executeQuery("select applicants from Project where projectName='"+p+"'");
            String temp = null;
            while(rs.next()){
                temp = rs.getString(1);
            }
            if (temp!=null){
                temp = temp.concat(",");
                temp = temp.concat(Integer.toString(benID));
            }
            else{
                temp = Integer.toString(benID);
            }
            i=stmt.executeUpdate("update Project set applicants='"+temp+"' where projectName='"+p+"'");
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    List<Beneficiary> getApplicants(String projname){
        ResultSet rs = null;
        String temp = null;
        List<Beneficiary> ben = new ArrayList<Beneficiary>();
        try {
            rs = stmt.executeQuery("select applicants from Project where projectName='"+projname+"'");
            while(rs.next()){
                temp = rs.getString(1);
            }
            if (temp!=null){
                String[] arr = temp.split(",");
                int i=0;
                while (i<arr.length){
                    rs = stmt.executeQuery("select beneficiaryName from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String name = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryAddress from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String add = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryPhone from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String ph = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryDOB from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String dob = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryIncome from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    int income = rs.getInt(1);
                    Beneficiary b = new Beneficiary(name,dob,income,ph,add);
                    ben.add(b);
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ben;
    }
        
    List<Beneficiary> getBeneficiaries(String projname){
        ResultSet rs = null;
        String temp = null;
        List<Beneficiary> ben = null;
        try {
            rs = stmt.executeQuery("select beneficiaries from Project where projectName='"+projname+"'");
            while(rs.next()){
                temp = rs.getString(1);
            }
            if (temp!=null){
                String[] arr = temp.split(",");
                int i=0;
                ben = new ArrayList<Beneficiary>();
                while (i<arr.length){
                    rs = stmt.executeQuery("select beneficiaryName from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String name = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryAddress from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String add = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryPhone from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String ph = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryDOB from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    String dob = rs.getString(1);
                    rs = stmt.executeQuery("select beneficiaryIncome from Beneficiary where BeneficiaryID="+arr[i]);
                    rs.next();
                    int income = rs.getInt(1);
                    Beneficiary b = new Beneficiary(name,dob,income,ph,add);
                    ben.add(b);
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ben;
    }
    
    void setBeneficiaries(Beneficiary b, String projname){
        ResultSet rs = null;
        String temp = null;
        try
        {
            rs = stmt.executeQuery("select beneficiaryID from Beneficiary where BeneficiaryName='"+b.getName()+"'");
            rs.next();
            int id = rs.getInt(1);
            rs = stmt.executeQuery("select beneficiaries from Project where projectName='"+projname+"'");
            while(rs.next()){
                temp = rs.getString(1);
            }
            if (temp != null){
                temp = temp.concat(",");
                temp = temp.concat(Integer.toString(id));
            }
            else{
                temp = Integer.toString(id);
            }
            int i=stmt.executeUpdate("update Project set beneficiaries='"+temp+"' where projectName='"+projname+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    List<Funding> getProjectFunds(String projname){
        ResultSet rs = null;
        int projID = 0;
        List<Funding> funds = null;
        try {
            rs = stmt.executeQuery("select projectID from Project where projectName='"+projname+"'");
            rs.next();
            int id = rs.getInt(1);
            rs = stmt.executeQuery("select * from Funding where projectID="+id);
            int fundID=0, type=0, amount=0;
            while(rs.next()){
                fundID = rs.getInt(1);
                type = rs.getInt(2);
                amount = rs.getInt(3);
            
                if (funds==null){
                    funds = new ArrayList<Funding>();
                }
                if (type==1){
                    Cash c = new Cash(amount);
                    funds.add(c);
                }
                else if (type==2){
                    FoodItems f = new FoodItems(amount);
                    funds.add(f);
                }
                else {
                    Clothes c = new Clothes(amount);
                    funds.add(c);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funds;
    }
    
    void issueDonation (String name, String projname, int cash, int clothes, int food){
        ResultSet rs = null;
        int count = 0;
        int benID = 0;
        int projID = 0;
        int fundID = 0;
        try {
            rs = stmt.executeQuery("select count(*) issuedID from FundsIssued");
            while(rs.next()){
                count = rs.getInt(1);
            }
            if (count!=0){
                rs = stmt.executeQuery("select top 1 issuedID from FundsIssued order by issuedID desc");
                while(rs.next()){
                    fundID = rs.getInt(1);
                }
            }
            fundID++;
            rs = stmt.executeQuery("select BeneficiaryID from Beneficiary where BeneficiaryName='"+name+"'");
            rs.next();
            benID = rs.getInt(1);
            rs = stmt.executeQuery("select projectID from Project where projectName='"+projname+"'");
            rs.next();
            projID = rs.getInt(1);
            int i=stmt.executeUpdate("insert into FundsIssued (issuedID,BeneficiaryID,projectID,cash,clothes,food) values ("+fundID+",'"+benID+"','"+projID+"','"+cash+"','"+clothes+"',"+food+")");
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    List<String> getDonorProjects(String name){
        ResultSet rs = null;
        List<String> proj = new ArrayList<String>();
        String temp = null;
        try {
            rs = stmt.executeQuery("select projectName from (select * from Funding join Donor on Donor.fundsGiven=Funding.fundID) as t1 join Project on t1.projectID=Project.projectID where t1.donorName='"+name+"'");
            while(rs.next()){
                temp = rs.getString(1);
                proj.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proj;
    }
    
    List<String> getDonors(){
        ResultSet rs = null;
        List<String> proj = new ArrayList<String>();
        String temp = null;
        try {
            rs = stmt.executeQuery("select donorName from Donor");
            while(rs.next()){
                temp = rs.getString(1);
                proj.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proj;
    }
    
    List<Integer> getDonorDetails(String name){
        ResultSet rs = null;
        int type = 0, amount = 0;
        int cash = 0, clothes = 0, food = 0;
        List<Integer> ret = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select fundtype, fundAmount from Donor join Funding on Donor.fundsGiven=Funding.fundID where donorName='"+name+"'");
            while(rs.next()){
                type = rs.getInt(1);
                amount = rs.getInt(2);
                
                if (type == 1){
                    cash+=amount;
                }
                else if (type == 2){
                    clothes+=amount;
                }
                else if (type == 3){
                    food+=amount;
                }
                
            }
            ret.add(cash);
            ret.add(clothes);
            ret.add(food);
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    List<String> getDonorsOfProject(String projname){
        ResultSet rs = null;
        List<String> proj = new ArrayList<String>();
        String temp = null;
        try {
            rs = stmt.executeQuery("select donorName from (select * from Donor join Funding on Donor.fundsGiven=Funding.fundID where projectID=(select projectID from Project where projectName='"+projname+"')) as t1 join Project on Project.projectID=t1.projectID");
            while(rs.next()){
                temp = rs.getString(1);
                proj.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proj;
    }
    
    List<Beneficiary> getAllBeneficiaries(){
        ResultSet rs = null;
        List<Beneficiary> ben = new ArrayList<Beneficiary>();
        String temp = null;
        try {
            rs = stmt.executeQuery("select * from Beneficiary");
            while(rs.next()){
                Beneficiary b = new Beneficiary();
                b.setName(rs.getString(2));
                b.setAddress(rs.getString(3));
                b.setPhoneNo(rs.getString(4));
                b.setDob(rs.getString(5));
                b.setIncome(Integer.parseInt(rs.getString(6)));
                ben.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ben;
    }
    
    List<Integer> getBenDetails(Beneficiary b){
        ResultSet rs = null;
        int type = 0, amount = 0;
        int cash = 0, clothes = 0, food = 0;
        List<Integer> ret = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select cash from FundsIssued join Beneficiary on FundsIssued.BeneficiaryID=Beneficiary.BeneficiaryID where Beneficiary.BeneficiaryName='"+b.getName()+"'");
            while(rs.next()){
                cash+=rs.getInt(1);
            }
            rs = stmt.executeQuery("select clothes from FundsIssued join Beneficiary on FundsIssued.BeneficiaryID=Beneficiary.BeneficiaryID where Beneficiary.BeneficiaryName='"+b.getName()+"'");
            while(rs.next()){
                food+=rs.getInt(1);
            }
            rs = stmt.executeQuery("select food from FundsIssued join Beneficiary on FundsIssued.BeneficiaryID=Beneficiary.BeneficiaryID where Beneficiary.BeneficiaryName='"+b.getName()+"'");
            while(rs.next()){
                clothes+=rs.getInt(1);
            }
            ret.add(cash);
            ret.add(clothes);
            ret.add(food);
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    List<String> getBenProjects(Beneficiary b){
        ResultSet rs = null;
        List<String> proj = new ArrayList<String>();
        String temp = null;
        try {
            rs = stmt.executeQuery("select projectName from (select projectID from FundsIssued join Beneficiary on Beneficiary.BeneficiaryID=FundsIssued.BeneficiaryID where BeneficiaryName='"+b.getName()+"') as t1 join Project on Project.projectID=t1.projectID");
            while(rs.next()){
                temp = rs.getString(1);
                proj.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proj;
    }
    
    String searchDonorName(String name){
        ResultSet rs = null;
        String temp = null;
        try {
            rs = stmt.executeQuery("select donorName from Donor where donorName='"+name+"'");
            while(rs.next()){
                temp = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
}


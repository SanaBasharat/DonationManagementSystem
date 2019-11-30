/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A4;

import java.util.List;

/**
 *
 * @author Sana
 */
public class Controller {
    
    public void addProject(String n, String d, String m, int b){
        dbConnectivity con = new dbConnectivity();
        con.addProject(n, d, b);
        con.closeConnection();
    }
    
    public List<String> getVolunteers(){
        dbConnectivity con = new dbConnectivity();
        List<String> r = con.getVolunteers();
        con.closeConnection();
        return r;
    }
    
    public List<String> getTeam(String proj){
        dbConnectivity con = new dbConnectivity();
        List<String> r = con.getTeam(proj);
        con.closeConnection();
        return r;
    }
    
    public void setTeamMember(String s, String p){
        dbConnectivity con = new dbConnectivity();
        con.setTeamMember(s, p);
        con.closeConnection();
    }
    
    public void setPM(String s, String p){
        dbConnectivity con = new dbConnectivity();
        con.setPM(s, p);
        con.closeConnection();
    }
    
    public void addProject(String n,String d, int b){
        dbConnectivity con = new dbConnectivity();
        con.addProject(n, d, b);
        con.closeConnection();
    }
}


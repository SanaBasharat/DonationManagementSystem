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
    
    public void addProject(String n, String d, int b){
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
    
    public void removeTeamMember(String name, String proj){
        dbConnectivity con = new dbConnectivity();
        con.removeTM(name, proj);
        con.closeConnection();
    }
    
    public List<String> getProjects(){
        dbConnectivity con = new dbConnectivity();
        List<String> r = con.getProjects();
        con.closeConnection();
        return r;
    }
    
    public String getDesc(String name){
        dbConnectivity con = new dbConnectivity();
        String r = con.getDesc(name);
        con.closeConnection();
        return r;
    }
    
    public String getPM(String proj){
        dbConnectivity con = new dbConnectivity();
        String r = con.getPM(proj);
        con.closeConnection();
        return r;
    }
    
    public String searchPMName(String n){
        dbConnectivity con = new dbConnectivity();
        String r = con.searchPMName(n);
        con.closeConnection();
        return r;
    }
    
    public Boolean isProj(String n){
        dbConnectivity con = new dbConnectivity();
        Boolean r = con.isProj(n);
        con.closeConnection();
        return r;
    }
    
    public void addDonor(String n, String p, int type, int quant){
        dbConnectivity con = new dbConnectivity();
        con.addDonor(n, p, type, quant);
        con.closeConnection();
    }
    
    public void addApplicant (String p, Beneficiary b){
        dbConnectivity con = new dbConnectivity();
        con.addApplicant(p, b);
        con.closeConnection();
    }
    
    public List<Beneficiary> getApplicants (String p){
        dbConnectivity con = new dbConnectivity();
        List<Beneficiary> b = con.getApplicants(p);
        con.closeConnection();
        return b;
    }
    
    public List<Beneficiary> getBeneficiaries (String p){
        dbConnectivity con = new dbConnectivity();
        List<Beneficiary> b = con.getBeneficiaries(p);
        con.closeConnection();
        return b;
    }
    
    public void setBeneficiaries (Beneficiary b, String proj){
        dbConnectivity con = new dbConnectivity();
        con.setBeneficiaries(b, proj);
        con.closeConnection();
    }
    
    public List<Funding> getProjectFunds (String proj){
        dbConnectivity con = new dbConnectivity();
        List<Funding> b = con.getProjectFunds(proj);
        con.closeConnection();
        return b;
    }
    
    public void issueDonation(String name, String proj, int cash, int clothes, int food){
        dbConnectivity con = new dbConnectivity();
        con.issueDonation(name,proj,cash,clothes,food);
        con.closeConnection();
    }
    
    public List<String> getDonorProjects(String name){
        dbConnectivity con = new dbConnectivity();
        List<String> b = con.getDonorProjects(name);
        con.closeConnection();
        return b;
    }
    
    public List<String> getDonors(){
        dbConnectivity con = new dbConnectivity();
        List<String> b = con.getDonors();
        con.closeConnection();
        return b;
    }
    
    public List<Integer> getDonorDetails(String name){
        dbConnectivity con = new dbConnectivity();
        List<Integer> b = con.getDonorDetails(name);
        con.closeConnection();
        return b;
    }
    
    public List<String> getDonorsOfProject(String name){
        dbConnectivity con = new dbConnectivity();
        List<String> b = con.getDonorsOfProject(name);
        con.closeConnection();
        return b;
    }
}


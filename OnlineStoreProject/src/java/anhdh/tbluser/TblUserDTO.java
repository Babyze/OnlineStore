/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.tbluser;

import java.io.Serializable;

/**
 *
 * @author babyz
 */
public class TblUserDTO implements Serializable {

    private String uUsername;
    private String uPassword;
    private String uFullname;
    private String uAddress;
    private boolean isAdmin;

    public TblUserDTO() {
    }

    public TblUserDTO(String uFullname, String uAddress) {
        this.uFullname = uFullname;
        this.uAddress = uAddress;
    }

    public TblUserDTO(String uUsername, String uPassword, String uFullname, String uAddress, boolean isAdmin) {
        this.uUsername = uUsername;
        this.uPassword = uPassword;
        this.uFullname = uFullname;
        this.uAddress = uAddress;
        this.isAdmin = isAdmin;
    }

    /**
     * @return the uUsername
     */
    public String getuUsername() {
        return uUsername;
    }

    /**
     * @param uUsername the uUsername to set
     */
    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    /**
     * @return the uPassword
     */
    public String getuPassword() {
        return uPassword;
    }

    /**
     * @param uPassword the uPassword to set
     */
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    /**
     * @return the uFullname
     */
    public String getuFullname() {
        return uFullname;
    }

    /**
     * @param uFullname the uFullname to set
     */
    public void setuFullname(String uFullname) {
        this.uFullname = uFullname;
    }

    /**
     * @return the uAddress
     */
    public String getuAddress() {
        return uAddress;
    }

    /**
     * @param uAddress the uAddress to set
     */
    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}

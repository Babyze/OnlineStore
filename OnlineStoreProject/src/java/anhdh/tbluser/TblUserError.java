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
public class TblUserError implements Serializable {

    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmPasswordMatched;
    private String addressLengthErr;
    private String fullNameLengthErr;
    private String usernameIsExisted;
    private String emptyParameter;

    public TblUserError() {
    }

    /**
     * @return the usernameLengthErr
     */
    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLengthErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the confirmPasswordMatched
     */
    public String getConfirmPasswordMatched() {
        return confirmPasswordMatched;
    }

    /**
     * @param confirmPasswordMatched the confirmPasswordMatched to set
     */
    public void setConfirmPasswordMatched(String confirmPasswordMatched) {
        this.confirmPasswordMatched = confirmPasswordMatched;
    }

    /**
     * @return the addressLengthErr
     */
    public String getAddressLengthErr() {
        return addressLengthErr;
    }

    /**
     * @param addressLengthErr the addressLengthErr to set
     */
    public void setAddressLengthErr(String addressLengthErr) {
        this.addressLengthErr = addressLengthErr;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    /**
     * @return the emptyParameter
     */
    public String getEmptyParameter() {
        return emptyParameter;
    }

    /**
     * @param emptyParameter the emptyParameter to set
     */
    public void setEmptyParameter(String emptyParameter) {
        this.emptyParameter = emptyParameter;
    }

}

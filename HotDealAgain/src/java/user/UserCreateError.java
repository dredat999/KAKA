/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class UserCreateError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String firstnameLengthErr;
    private String lastnameLengthErr;
    private String confirmNotMacthed;
    private String usernameIsExisted;
    private String emailIsExisted;

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
     * @return the confirmNotMacthed
     */
    public String getConfirmNotMacthed() {
        return confirmNotMacthed;
    }

    /**
     * @param confirmNotMacthed the confirmNotMacthed to set
     */
    public void setConfirmNotMacthed(String confirmNotMacthed) {
        this.confirmNotMacthed = confirmNotMacthed;
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
     * @return the emailIsExisted
     */
    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    /**
     * @param emailIsExisted the emailIsExisted to set
     */
    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    /**
     * @return the firstnameLengthErr
     */
    public String getFirstnameLengthErr() {
        return firstnameLengthErr;
    }

    /**
     * @param firstnameLengthErr the firstnameLengthErr to set
     */
    public void setFirstnameLengthErr(String firstnameLengthErr) {
        this.firstnameLengthErr = firstnameLengthErr;
    }

    /**
     * @return the lastnameLengthErr
     */
    public String getLastnameLengthErr() {
        return lastnameLengthErr;
    }

    /**
     * @param lastnameLengthErr the lastnameLengthErr to set
     */
    public void setLastnameLengthErr(String lastnameLengthErr) {
        this.lastnameLengthErr = lastnameLengthErr;
    }
    
    
    
}

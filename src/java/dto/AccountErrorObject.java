/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author mevrthisbang
 */
public class AccountErrorObject {
    private String emailError, currentPasswordError, newPasswordError, confirmPasswordError, firstnameError, lastnameError,
            phoneError, addressError, descriptionError;

    public AccountErrorObject() {
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getCurrentPasswordError() {
        return currentPasswordError;
    }

    public void setCurrentPasswordError(String currentPasswordError) {
        this.currentPasswordError = currentPasswordError;
    }

    public String getNewPasswordError() {
        return newPasswordError;
    }

    public void setNewPasswordError(String newPasswordError) {
        this.newPasswordError = newPasswordError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getFirstnameError() {
        return firstnameError;
    }

    public void setFirstnameError(String firstnameError) {
        this.firstnameError = firstnameError;
    }

    public String getLastnameError() {
        return lastnameError;
    }

    public void setLastnameError(String lastnameError) {
        this.lastnameError = lastnameError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }
    
}

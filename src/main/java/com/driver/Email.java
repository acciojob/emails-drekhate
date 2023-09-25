package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public Email() {
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals(password)) {
            int len = newPassword.length();
            if(len >= 8) {
                int uppercaseLetter = 0;
                int lowercaseLetter = 0;
                int digit = 0;
                int specialCharacter = 0;
                for(int i = 0; i < len; i ++) {
                    char ch = newPassword.charAt(i);
                    int ascii = ch;
                    if(ascii >= 97 && ascii <= 122) {
                        lowercaseLetter ++;
                    }
                    if(ascii >= 65 && ascii <= 90) {
                        uppercaseLetter ++;
                    }
                    if(ascii >= 48 && ascii <= 57) {
                        digit ++;
                    }
                    if((ascii >= 32 && ascii <= 47) || (ascii >= 58 && ascii <= 64) ||
                    (ascii >= 91 && ascii <= 96) || (ascii >= 123 && ascii <= 126)) {
                        specialCharacter ++;
                    }
                }
                if(uppercaseLetter >= 1 && lowercaseLetter >= 1 && digit >= 1 && specialCharacter >= 1) {
                    password = newPassword;
                }
            }
        }
    }
}

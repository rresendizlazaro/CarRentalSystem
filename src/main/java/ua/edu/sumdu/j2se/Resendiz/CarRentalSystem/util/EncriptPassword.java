package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptPassword {
    public static void main(String[] args) {
        //Algorithm to encript password
        String password = "12345678";
        System.out.println("password: " + password);
        System.out.println("Encripted password: " + encript(password));
    }
    
    public static String encript(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}

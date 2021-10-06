package utils;

public class StringUtils {
    public static boolean checkPassword(String password){
        return password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}");
    }

    public static boolean checkEmail(String email){
        return email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
    }

    public static boolean checkNumber(String number){
        return number.matches( "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
    }
}

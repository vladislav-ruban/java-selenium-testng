package Dto;

public class UserCredentialsDto {
    private static String email;
    private static String password;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserCredentialsDto.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserCredentialsDto.password = password;
    }
}

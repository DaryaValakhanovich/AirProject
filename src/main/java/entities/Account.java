package entities;

public class Account extends BaseEntity {
    private String email;
    private String password;
    private Role role;
    private String number;

    public Account() {
    }

    public Account(String email, String password, String number) {
        this.password = password;
        this.email = email;
        this.number = number;
        role = Role.USER;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Account{" +
                "password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

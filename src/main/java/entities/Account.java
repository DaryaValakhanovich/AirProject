package entities;

import javax.persistence.*;

@Entity
@Table(name = "accounts", uniqueConstraints = {
  @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "email")})
public class Account{

    @Column(name = "email", unique = true, nullable = false, length = 250)
    private String email;

    @Column(name = "password", unique = false, nullable = false, length = 250)
    private String password;

    //@Column(name = "role", unique = false, nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition="enum('USER','ADMIN')", nullable = false, length = 50)
    private Role role;

    @Column(name = "number", unique = true, nullable = false, length = 250)
    private String number;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

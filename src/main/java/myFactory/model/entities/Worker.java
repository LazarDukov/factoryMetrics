package myFactory.model.entities;

import jakarta.persistence.*;
import myFactory.util.PasswordMatchValidatorInterface;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@MappedSuperclass
//@PasswordMatchValidatorInterface(password = "password", confirmPassword = "confirmPassword", message = "should matching these passwords!")
public abstract class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String workerIdentityNickname;


    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;

    @Column
    private String password;

    @Transient
    private String confirmPassword;

    @Column
    private String email;


    @ManyToMany
    private List<WorkerRole> role;

    protected Worker(UUID id, String workerIdentityNickname, String firstName, String lastName, int age, String password, String email) {
        this.id = id;
        this.workerIdentityNickname = workerIdentityNickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.email = email;
    }

    protected Worker() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWorkerIdentityNickname() {
        return workerIdentityNickname;
    }

    public Worker setWorkerIdentityNickname(String workerIdentityNickname) {
        this.workerIdentityNickname = workerIdentityNickname;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Worker setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Worker setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<WorkerRole> getRole() {
        return role;
    }

    public Worker setRole(List<WorkerRole> role) {
        this.role = role;
        return this;
    }
}

package com.tneshcheret.entity;

public class User extends BaseEntity {
    private String userName;
    private String password;
    private UserRole userRole;

    public User() {
    }

    public User(String userName, String password, UserRole userRole) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public User(Integer id, String userName, String password, UserRole userRole) {
        super(id);
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

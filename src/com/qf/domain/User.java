package com.qf.domain;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/25 14:54
 * description:用户类
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private Integer flag;
    private Integer role;
    private String code;

    public User() {
    }

    public User(Integer id, String username, String password, String email, String gender, Integer flag, Integer role, String code) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.flag = flag;
        this.role = role;
        this.code = code;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return flag
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置
     * @param flag
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取
     * @return role
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置
     * @param role
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * 获取
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", email = " + email + ", gender = " + gender + ", flag = " + flag + ", role = " + role + ", code = " + code + "}";
    }
}

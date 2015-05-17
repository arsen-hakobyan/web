package com.backend.admin.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "User")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @XmlElement(name = "userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @XmlElement(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
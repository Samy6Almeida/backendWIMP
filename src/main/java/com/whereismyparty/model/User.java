package com.whereismyparty.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "user")
public class User {
	//------------------------------------------------------------------	
    @Id
	@Cascade(CascadeType.ALL)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	//------------------------------------------------------------------	
    @Column(name="username", unique=true, length = 50)
    @NotNull
    private String userName;
	//------------------------------------------------------------------	
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="password", length = 255)
    @NotNull
    private String password;
	//------------------------------------------------------------------	
    /*
    @Column(name="roles", length = 20)
    private String roles;
    */
	//------------------------------------------------------------------	
    @Column(name="Name", length = 200)
    @NotNull
    private String Name;
	//------------------------------------------------------------------	 
    @Column(name="Sex", length = 1)
    private String Sex;
	//------------------------------------------------------------------	
   @Column(name = "birth_date")
   @Temporal(TemporalType.DATE)
   private Date birth_date;
	//------------------------------------------------------------------	

    public String getSex() {
	return Sex;
}

public void setSex(String sex) {
	Sex = sex;
}

public Date getBirth_date() {
	return birth_date;
}

public void setBirth_date(Date birth_date) {
	this.birth_date = birth_date;
}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
/*
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
*/
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}
}

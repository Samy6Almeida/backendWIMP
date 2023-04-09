package com.whereismyparty.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "denunciation")
public class Denunciation {
	//------------------------------------------------------------------	
      @Id
	  @GeneratedValue(strategy= GenerationType.IDENTITY)
	  private Long id;
    //------------------------------------------------------------------	
      @Column(name = "denunciation_date")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date denunciation_date;
    //------------------------------------------------------------------	
      @OneToOne(orphanRemoval=true,cascade = {CascadeType.REMOVE})
	  @NotNull
	  private User user;
    //------------------------------------------------------------------	
      @OneToOne(orphanRemoval=true,cascade = {CascadeType.REMOVE})
	  @NotNull
	  private Party party;
    //------------------------------------------------------------------	 
	  @Column(name="denunciation", length = 200)
	  @NotNull
	  private String denunciation;
	//------------------------------------------------------------------	
	public Long getId() {
		return id;
	}
	//------------------------------------------------------------------	

	public void setId(Long id) {
		this.id = id;
	}
	//------------------------------------------------------------------	

	public Date getDenunciation_date() {
		return denunciation_date;
	}
	//------------------------------------------------------------------	

	public void setDenunciation_date(Date denunciation_date) {
		this.denunciation_date = denunciation_date;
	}
	//------------------------------------------------------------------	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public String getDenunciation() {
		return denunciation;
	}

	public void setDenunciation(String denunciation) {
		this.denunciation = denunciation;
	}
}

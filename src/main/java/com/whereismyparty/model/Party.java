package com.whereismyparty.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "party")
public class Party {
	//------------------------------------------------------------------	
      @Id
	  @GeneratedValue(strategy= GenerationType.IDENTITY)
	  private Long id;
    //------------------------------------------------------------------
	  @Column(name="title", length = 200)
	  @NotNull
	  private String title;
	//------------------------------------------------------------------
	  @Column(name="description", length = 200)
	  @NotNull
	  private String description;
	//------------------------------------------------------------------
	  @Column(name = "party_start_date")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date party_start_date;
	//------------------------------------------------------------------
	  @Column(name = "party_ends_date")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date party_ends_date;
	//------------------------------------------------------------------
	  @Column(name = "publication_date")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date publication_date;
	//------------------------------------------------------------------
	  @Column(name = "latitude", precision = 5, scale = 15, nullable = false)
	  @Type(type = "double")
	  private double latitude;
	//------------------------------------------------------------------
	  @Column(name = "longitude", precision = 5, scale = 15, nullable = false)
	  @Type(type = "double")
	  private double longitude;
	//------------------------------------------------------------------
	  @Column(name="instagram_link", length = 200)
	    private String instagram_link;
	//------------------------------------------------------------------
	  @Column(name="address", length = 500)
      private String address;
	//------------------------------------------------------------------
	  // FK para a tabela User com o Id do usuario referencia.
	  // O JPA sempre pega o PK e faz referencia
	  @ManyToOne(targetEntity=com.whereismyparty.model.User.class)
	  @NotNull
	  private User user  ; 
	//------------------------------------------------------------------
	  @ManyToMany(fetch = FetchType.LAZY, targetEntity=com.whereismyparty.model.PartyKind.class)
	  @JoinTable(  name = "party_x_partykind",
       joinColumns = @JoinColumn(name = "party_id"), 
       inverseJoinColumns = @JoinColumn(name = "partykind_id")
	  )
 		private Set<PartyKind> kinds = new HashSet<>();
	//--------------------------------------------------------------------------------------------- 
	  @ManyToMany(fetch = FetchType.LAZY ,targetEntity=com.whereismyparty.model.PartyFeatures.class)
	  @JoinTable(  name = "party_x_partfeatures",
      joinColumns = @JoinColumn(name = "party_id"), 
      inverseJoinColumns = @JoinColumn(name = "partyfeatures_id")
	   )
		private Set<PartyFeatures> partyFeatures = new HashSet<>();
	//--------------------------------------------------------------------------------------------- 
	  @ManyToMany(fetch = FetchType.LAZY,targetEntity=com.whereismyparty.model.Music.class)
	  @JoinTable(  name = "party_x_music", 
      joinColumns = @JoinColumn(name = "party_id"), 
      inverseJoinColumns = @JoinColumn(name = "music_id")
	   )
		private Set<Music> music = new HashSet<>();
	//---------------------------------------------------------------------------------------------

	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getParty_start_date() {
		return party_start_date;
	}


	public void setParty_start_date(Date party_start_date) {
		this.party_start_date = party_start_date;
	}


	public Date getParty_ends_date() {
		return party_ends_date;
	}


	public void setParty_ends_date(Date party_ends_date) {
		this.party_ends_date = party_ends_date;
	}


	public Date getPublication_date() {
		return publication_date;
	}


	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}


	public String getInstagram_link() {
		return instagram_link;
	}


	public void setInstagram_link(String instagram_link) {
		this.instagram_link = instagram_link;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
	public Set<PartyKind> getKinds() {
		return kinds;
	}

	public void setKinds(Set<PartyKind> kinds) {
		this.kinds = kinds;
	}

	public Set<Music> getMusic() {
		return music;
	}


	public Set<PartyFeatures> getPartyFeatures() {
		return partyFeatures;
	}


	public void setPartyFeatures(Set<PartyFeatures> partyFeatures) {
		this.partyFeatures = partyFeatures;
	}


	public void setMusic(Set<Music> music) {
		this.music = music;
	}
	  
	  
	  

}

package com.whereismyparty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;

@Entity
@Table(name = "partykind")
public class PartyKind {
	
	//------------------------------------------------------------------	
      @Id
	  @GeneratedValue(strategy= GenerationType.IDENTITY)
	  private Long id;
  	//------------------------------------------------------------------	
	  @Column(name="kind", length = 200)
	  @NotNull
	  private String kind;
	//------------------------------------------------------------------	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}



	
	

}

package com.whereismyparty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "partyfeatures")
public class PartyFeatures {
	
	//------------------------------------------------------------------	
	      @Id
		  @GeneratedValue(strategy= GenerationType.IDENTITY)
		  private Long id;
  	//------------------------------------------------------------------	
		  @Column(name="partyfeatures", length = 200)
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
package com.whereismyparty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "musickind")
public class Music {
	

		//------------------------------------------------------------------	
 		  @Id
		  @GeneratedValue(strategy= GenerationType.IDENTITY)
		  private Long id;
		//------------------------------------------------------------------	
		  @Column(name="music_kind", length = 200)
		  @NotNull
		  private String music_kind;
		//------------------------------------------------------------------
		  
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMusic_kind() {
			return music_kind;
		}

		public void setMusic_kind(String music_kind) {
			this.music_kind = music_kind;
		}

}



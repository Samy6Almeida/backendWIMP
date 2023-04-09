package com.whereismyparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.whereismyparty.model.Music;
import com.whereismyparty.model.Party;

public interface MusicService {
	

	
	
	Music createMusic(Music music);

	Page<Music> getAllMusic(int page, int limit);

	Music updateMusic(Long id, Music music);

	 void deleteMusic(Long id);

}


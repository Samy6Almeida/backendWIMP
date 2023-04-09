package com.whereismyparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.whereismyparty.exception.ResourceNotFoundException;
import com.whereismyparty.model.Music;
import com.whereismyparty.model.Party;
import com.whereismyparty.model.PartyFeatures;
import com.whereismyparty.model.User;
import com.whereismyparty.repository.MusicRepository;

@Service
public class MusicServiceImpl  implements MusicService{

	
	@Autowired
	MusicRepository musicRepository;
	//---------------------------------------------------------------------------------
	@Override
	public Music createMusic(Music music) {
		return musicRepository.save(music);
	}
	//---------------------------------------------------------------------------------
	@Override
	public Page<Music> getAllMusic(int page, int limit) {
		Page<Music> resultPage = musicRepository.findAll(new PageRequest(page,limit));
        if (page > resultPage.getTotalPages())
             throw new ResourceNotFoundException("Music", "id", resultPage);
        return resultPage;
	}
	//---------------------------------------------------------------------------------
	@Override
	public Music updateMusic(Long id, Music music) {
		Music musicupdate = musicRepository.findById(id);
        if(musicupdate == null)
            throw new ResourceNotFoundException("Party", "id", id);
        musicupdate.setMusic_kind(music.getMusic_kind());
        return musicRepository.save(musicupdate);
	}
	//---------------------------------------------------------------------------------
	@Override
	public void deleteMusic(Long id) {
		Music music = musicRepository.findById(id);
	        if(music == null)
	            throw new ResourceNotFoundException("Music", "id", id);
	        musicRepository.delete(music);		
	}

//---------------------------------------------------------------------------------
}

package com.whereismyparty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.whereismyparty.exception.ResourceNotFoundException;
import com.whereismyparty.model.Denunciation;
import com.whereismyparty.model.Party;
import com.whereismyparty.repository.DenunciationRepository;

@Service
public class DenunciationServiceImpl implements DenunciationService {

	
	
	  //---------------------------------------------------------------------
    @Autowired
    DenunciationRepository DenunciationRepository;
    //---------------------------------------------------------------------

    @Override
    public Denunciation createDenunciation(Denunciation denunciation) {
        return DenunciationRepository.save(denunciation);
    }
    //---------------------------------------------------------------------

	@Override
	public Page<Denunciation> getAllDenunciation(int page, int limit) {
		 Page<Denunciation> resultPage = DenunciationRepository.findAll(new PageRequest(page,limit));
	        if (page > resultPage.getTotalPages())
	             throw new ResourceNotFoundException("Denunciation", "id", resultPage);
	        return resultPage;
	}
    //---------------------------------------------------------------------

/*
	@Override
	public Denunciation updateDenunciation(Long id, Denunciation denunciationDetails) {
		Denunciation denunciation = DenunciationRepository.findById(id);
	        if(denunciation == null)
	            throw new ResourceNotFoundException("Denunciation", "id", id);

	        denunciation.setData_inicio(denunciationDetails.getData_inicio());
	        denunciation.setData_fim(denunciationDetails.getData_fim());
	        denunciation.setData_publicacao(denunciationDetails.getData_publicacao());
	        denunciation.setDescricao(denunciationDetails.getDescricao());
	        denunciation.setInstagram_link(denunciationDetails.getInstagram_link());
	        denunciation.setTitulo(denunciationDetails.getTitulo());
	        denunciation.setUser(denunciationDetails.getUser());;
	        return DenunciationRepository.save(denunciation);

	}
	*/
	
    //---------------------------------------------------------------------


	@Override
	public void deleteDenunciation(Long id) {
		Denunciation denunciation = DenunciationRepository.findById(id);
	        if(denunciation == null)
	            throw new ResourceNotFoundException("Denunciation", "id", id);

	        DenunciationRepository.delete(denunciation);		
	}
    //---------------------------------------------------------------------

	@Override
	public List<Denunciation> searchDenunciation(Specification<Denunciation> spec) {
	    return DenunciationRepository.findAll(spec);
	}

	@Override
	public Party updateDenunciation(Long id, Party DenunciationDetails) {
		// TODO Auto-generated method stub
		return null;
	}

    //---------------------------------------------------------------------

}

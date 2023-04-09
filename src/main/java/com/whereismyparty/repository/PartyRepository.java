package com.whereismyparty.repository;

import java.security.Principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.whereismyparty.model.Music;
import com.whereismyparty.model.Party;
import com.whereismyparty.model.PartyFeatures;
import com.whereismyparty.model.PartyKind;

@Repository
public interface PartyRepository extends PagingAndSortingRepository<Party, Long>, JpaSpecificationExecutor<Party>, JpaRepository<Party, Long> {
    
    Party findByUser(PageRequest pageRequest);
	Party findById(Long id);    
    Party findByUserId(User user, Long id);
    Party findByUser(User user);
    
    public static final Music music = new Music();
    @Query("SELECT p FROM Party p JOIN p.music music WHERE music.id = :musicId")
    List<Party> findByMusicId(@Param("musicId") Long musicId);
    
    public static final PartyFeatures partyFeatures = new PartyFeatures();
    @Query("SELECT p FROM Party p JOIN p.partyFeatures pf WHERE pf.id = :partyFeaturesId")
    List<Party> findByPartyFeaturesId(@Param("partyFeaturesId") Long partyFeaturesId);
    
    public static final PartyKind partyKind = new PartyKind();
    @Query("SELECT p FROM Party p JOIN p.kinds k WHERE k.id = :kindId")
    List<Party> findByKindId(@Param("kindId") Long kindId);
}

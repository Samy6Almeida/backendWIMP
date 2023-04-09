package com.whereismyparty.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.whereismyparty.constants.JWTConstants;
import com.whereismyparty.model.User;
import com.whereismyparty.repository.UserRepository;
import com.whereismyparty.service.UserService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
	

	@Autowired
    UserService userService; //---Use este para referenciar abaixo.
		
	public String getAuthorityFromToken(String authority) {
        return getClaimFromToken(authority, Claims::getSubject);
    }
 
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWTConstants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
        
        
    }
    
    
    public String getClaimsRole(String token) {
    	  Claims claims = Jwts.parser().setSigningKey(JWTConstants.SIGNING_KEY).parseClaimsJws(token).getBody();
    	  System.out.print("claims.get(\"scopes\")==>"+claims.get("scopes")+"\n");
    	  //List roleNames = (List) claims.get("scopes");
    	  
      	//System.out.print("getClaimsRole(String token) ==> userDetails.getAuthorities()==>"+((UserDetails) claims).getAuthorities().toString()+"\n");

          String roleNames = (String) claims.get("scopes").toString();
    	  //System.out.print("AuthorityUtils.commaSeparatedStringToAuthorityList(claims.toString())===>"+AuthorityUtils.commaSeparatedStringToAuthorityList(claims.toString())+"\n");
    	  return roleNames;
    	}
    
    
    

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return doGenerateToken(user.getUserName());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
   
        
        //NUNO
        System.out.print("JwtTokenUtil.class -> doGenerateToken to the user===> "+subject+"\n");
        if (subject.equals("admin")) {
        	
        	claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        	System.out.print("AuthorityUtils.commaSeparatedStringToAuthorityList(subject)===>"+AuthorityUtils.commaSeparatedStringToAuthorityList(subject)+"\n");
        	
  
        }else {
        	claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("USER")));
        	claims.put("userid", userService.findOne(subject).getId());   // Vai inserir no JWT token o  UserID
        	//claims.put("userid", ((Claims) userService.findOne(subject)).getId());   // Vai inserir no JWT token o  UserID
        	
        }
        
        System.out.print("claims.put==>"+claims);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("admin")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWTConstants.ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, JWTConstants.SIGNING_KEY)
                .compact();
        
        
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}

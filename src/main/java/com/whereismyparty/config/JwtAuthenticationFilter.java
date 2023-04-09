package com.whereismyparty.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.whereismyparty.constants.JWTConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(JWTConstants.HEADER_STRING);
        System.out.print("\n"+"==========================================Estou no JwtAuthenticationFilter.java========================================================"+"\n"); 
        System.out.print("=req.getHeader(JWTConstants.HEADER_STRING)===>"+header+"\n");
        /*
        String authToken = header.replace(JWTConstants.TOKEN_PREFIX,"");
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        System.out.print("authToken==>"+authToken+"\n");
        System.out.print("username===>"+username+"\n");
        */
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(JWTConstants.TOKEN_PREFIX)) {
            authToken = header.replace(JWTConstants.TOKEN_PREFIX,"");
            System.out.print("===========================if (header != null && header.startsWith(JWTConstants.TOKEN_PREFIX))================================>"+"\n");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                System.out.print("username===>"+username+"\n");
            	System.out.print("getAuthorities==>"+jwtTokenUtil.getAuthorityFromToken(authToken)+"\n");

                System.out.print("jwtTokenUtil.getAuthorityFromToken(authToken)===>"+jwtTokenUtil.getAuthorityFromToken(authToken)+"\n");
                System.out.print("jjwtTokenUtil.getAllClaimsFromToken(authToken)===>"+jwtTokenUtil.getAllClaimsFromToken(authToken)+"\n");
                System.out.print("jwtTokenUtil.getAllClaimsFromToken(authToken).containsValue(\"authority\")===>"+jwtTokenUtil.getAllClaimsFromToken(authToken).containsValue("scopes")+"\n");
                System.out.print("===>"+jwtTokenUtil.getClaimsRole(authToken)+"\n");
                System.out.print("jwtTokenUtil.getClaimsRole(authToken)==+>"+jwtTokenUtil.getClaimsRole(authToken)+"\n");
                
                
                
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	
        	System.out.print("SecurityContextHolder.getContext().getAuthentication()===>"+SecurityContextHolder.getContext().getAuthentication()+"\n");
        	System.out.print("============if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)============"+"\n");
        	
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.print("authToken====>"+authToken+"\n");
            System.out.print("userDetails==>"+userDetails+"\n");
            
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                //NUNO
            	
            	/*
            	System.out.print("loadUserByUsername(username)==>"+userDetailsService.loadUserByUsername(username)+"\n");
            	//System.out.print(""+userDetailsService.loadUserByUsername(username).getAuthorities().equals("ROLE_ADMIN")+"\n");
            	System.out.print(".loadUserByUsername(username).getAuthorities()==>"+userDetailsService.loadUserByUsername(username).getAuthorities()+"\n");
            	System.out.print(".loadUserByUsername(username).getUsername()==>"+userDetailsService.loadUserByUsername(username).getUsername()+"\n");
            	Collection<? extends GrantedAuthority> getAuthorities=userDetailsService.loadUserByUsername(username).getAuthorities();
            	//jwtTokenUtil.getAuthorityFromToken(authToken);
            	System.out.print("jwtTokenUtil.getAuthorityFromToken(authToken)==>"+jwtTokenUtil.getAuthorityFromToken(authToken)+"\n");
            	
            	//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority(getAuthorities.toString().replaceAll("[^a-zA-Z]", ""))));
            	
            	//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority(String.valueOf(jwtTokenUtil.getAllClaimsFromToken(authToken).containsValue("scopes")) )));
            	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority(String.valueOf(jwtTokenUtil.getClaimsRole(authToken).replaceAll("[^=a-zA-Z]", "")) )));
            	//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority(String.valueOf(jwtTokenUtil.getClaimsRole(authToken).replaceAll("(?<=\\{)(.*)(?=\\})", "")) )));
            	//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority(String.valueOf(jwtTokenUtil.getClaimsRole(authToken).endsWith("(?<=\\{)(.*)(?=\\})"))))); 
            	
            	System.out.print("userDetails.getAuthorities()==>"+userDetails.getAuthorities()+"\n");
            	
            	//System.out.print("jwtTokenUtil.getClaimsRole(authToken)==>"+jwtTokenUtil.getClaimsRole(authToken)+"\n");
            	System.out.print("jwtTokenUtil.getClaimsRole(authToken)==>"+jwtTokenUtil.getClaimsRole(authToken).formatted("(?<=\\{)(.*)(?=\\})")+"\n");
            	System.out.print("jwtTokenUtil.getClaimsRole(authToken)=2==>"+jwtTokenUtil.getClaimsRole(authToken)+"\n");
            	System.out.print("authentication==>"+authentication+"\n");
            	System.out.print("getAuthorities==>"+getAuthorities+"\n");
            	
            	
            	//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("USER")));
            	//System.out.print("authentication==>"+authentication+"\n");
            	SecurityContextHolder.getContext().setAuthentication(authentication);
            	
            	*/
            	
            	
            	if (userDetailsService.loadUserByUsername(username).getUsername().equals("admin")) {
            		 System.out.print("estou no if"+"\n");
            		 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            		 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                     SecurityContextHolder.getContext().setAuthentication(authentication);
                     
                     System.out.print("SecurityContextHolder.getContext().getAuthentication()==>"+SecurityContextHolder.getContext().getAuthentication()+"\n");
                     System.out.print("authentication.getDetails()==>"+authentication.getDetails()+"\n");
                 	System.out.print("userDetails.getAuthorities()==>"+authentication.getAuthorities()+"\n");


            	 }else {
            		 System.out.print("estou aqui no else + JwtAuthenticationFilter.java "+"\n");
            		 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("USER")));
                     authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                     SecurityContextHolder.getContext().setAuthentication(authentication);
                     System.out.print("SecurityContextHolder.getContext().getAuthentication()===>"+SecurityContextHolder.getContext().getAuthentication()+"\n");
                     System.out.print("authentication.getDetails()===>"+authentication.getDetails()+"\n");
                 	System.out.print("userDetails.getAuthorities()===>"+authentication.getAuthorities()+"\n");
            	 }
            	
            	
            	//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            	//UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("USER")));
            	//System.out.print("userDetails.getAuthorities()==>"+userDetails.getAuthorities()+"\n");
            	logger.info("authenticated user " + username + ", setting security context");
            }
        }

        chain.doFilter(req, res);
    }
}
package com.learningspring.springbootsecurity.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;


    //get JWT from HTTP Request
    private String getJWTFromRequest(HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain
            ) throws ServletException, java.io.IOException {
        //Get JWT from HTTP Request
        String jwt = getJWTFromRequest(httpServletRequest);

        //ValidateToken
        if(StringUtils.hasText(jwt) && jwtTokenProvider.isTokenValid(jwt)) {
            //Get username from Token
            String username = jwtTokenProvider.extractUsername(jwt);

            //Retrieve UserDetails via UserDetails.loadUserByUsername
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //Create **UnamePwdAuthToken(userDetails, credentials:null, userDetails.getAuthorities())
            UsernamePasswordAuthenticationToken unamePwdAuthToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            //Set Details UnamePwdAuthToken.setDetails(..)
            unamePwdAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            //Set UnamePwdAuthToken to SecurityContextHolder.getContext().setAuthentication(**)
            SecurityContextHolder.getContext().setAuthentication(unamePwdAuthToken);

        }

        //Do Filter via filterChain.doFilter(request, response)
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}

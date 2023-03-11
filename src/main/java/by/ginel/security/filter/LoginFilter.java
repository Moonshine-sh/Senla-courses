package by.ginel.security.filter;

import by.ginel.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public LoginFilter(JwtService jwtService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String token = prepareJwt(authResult);
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
    }

    private String prepareJwt(Authentication authResult) {
        return jwtService.generateToken(userDetailsService.loadUserByUsername(authResult.getName()));
    }

}

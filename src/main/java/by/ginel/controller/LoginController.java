package by.ginel.controller;

import by.ginel.dto.LoginDto;
import by.ginel.dto.PersonDto;
import by.ginel.dto.RegisterDto;
import by.ginel.entity.MyUser;
import by.ginel.entity.Person;
import by.ginel.entity.VerificationToken;
import by.ginel.event.OnRegistrationCompleteEvent;
import by.ginel.handler.exception.VerificationException;
import by.ginel.handler.exception.VerificationExpiredException;
import by.ginel.security.JwtService;
import by.ginel.service.PersonCredentialsService;
import by.ginel.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PersonCredentialsService personCredentialsService;
    private final ApplicationEventPublisher eventPublisher;
    private final PersonService personService;

    //TODO implement login and registration functionality
    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
        );
        MyUser user = (MyUser) userDetailsService.loadUserByUsername(loginDto.getUsername());

        String token = jwtService.generateToken(user);
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto, HttpServletResponse response, HttpServletRequest request) {
        PersonDto personDto = personCredentialsService.isUserValid(registerDto);
        String appUrl = request.getContextPath();
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(personDto, request.getLocale(), appUrl));

        MyUser user = (MyUser) userDetailsService.loadUserByUsername(registerDto.getUsername());

        String token = jwtService.generateToken(user);
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
        return ResponseEntity.ok("Registration successful");
    }

    @GetMapping("/regitrationConfirm")
    public ResponseEntity<String> confirmRegistration(HttpServletRequest request, @RequestParam("token") String token){
        Locale locale = request.getLocale();

        VerificationToken verificationToken = personService.getVerificationToken(token);
        if(verificationToken == null){
            throw new VerificationException("Could not find such verification token");
        }

        Person person = verificationToken.getPerson();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new VerificationExpiredException("Your verification token has expired");
        }
        person.setEnabled(true);
        personService.activateUser(person.getId());
        return ResponseEntity.ok("Your account has been activated");
    }
}
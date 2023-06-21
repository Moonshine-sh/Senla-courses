package by.ginel.controller;

import by.ginel.dto.ChangePasswordDto;
import by.ginel.dto.PersonCredentialsDto;
import by.ginel.dto.PersonDto;
import by.ginel.entity.MyUser;
import by.ginel.handler.exception.ActionOnAdminException;
import by.ginel.handler.exception.IncorrectPersonUpdateException;
import by.ginel.handler.exception.PasswordUpdateException;
import by.ginel.security.JwtService;
import by.ginel.security.UserDetailsServiceImpl;
import by.ginel.service.PersonCredentialsService;
import by.ginel.service.PersonService;
import by.ginel.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonCredentialsService personCredentialsService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<PersonDto>> getPeopleList(@RequestBody Pageable pageable) {
        List<PersonDto> allPeople = personService.getAll(pageable);
        return ResponseEntity.ok(allPeople);
    }

    @PostMapping("/{id}/lock")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> lockPerson(@PathVariable Long id) {
        log.info("executed method lockPerson");

        if(personService.isAdmin(id)){
            throw new ActionOnAdminException("Cant lock Admin account");
        }
        personService.updateLocked(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<PersonDto> personProfile(HttpServletRequest request) {
        PersonDto personDto = personService.getById(getUser(request).getId());
        return ResponseEntity.ok(personDto);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updatePersonInfo(@RequestBody PersonDto personDto, HttpServletRequest request) {
        MyUser user = getUser(request);
        if(!Objects.equals(personDto.getId(), user.getId())){
            throw new IncorrectPersonUpdateException("Cant update personal information of person with different Id");
        }
        personService.update(personDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updatePassword(@RequestBody ChangePasswordDto changePasswordDto, HttpServletRequest request) {
        MyUser user = getUser(request);
        PersonCredentialsDto personCredentialsDto = personCredentialsService.findByLogin(user.getUsername());
        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(), personCredentialsDto.getPassword())){
            throw new PasswordUpdateException("Old password does not match");
        }
        if(!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())){
            throw new PasswordUpdateException("New and Confirm passwords does not match");
        }
        personCredentialsDto.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        personCredentialsService.update(personCredentialsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private MyUser getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        return (MyUser) userDetailsService.loadUserByUsername(username);
    }
}

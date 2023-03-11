package by.ginel.security;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.entity.PersonCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonCredentialsDao personCredentialsDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonCredentials personCredentials = Optional.ofNullable(personCredentialsDao.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        return User.builder()
                .username(personCredentials.getLogin())
                .password(personCredentials.getPassword())
                .authorities(personCredentials.getPerson().getRoles().stream().map(role -> String.valueOf(role.getName())).toList().toArray(String[]::new))
                .accountLocked(personCredentials.getPerson().getLocked())
                .disabled(!personCredentials.getPerson().getEnabled())
                .build();
    }
}

package by.ginel.security;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.entity.MyUser;
import by.ginel.entity.PersonCredentials;
import by.ginel.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonCredentialsDao personCredentialsDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonCredentials personCredentials = personCredentialsDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        //        UserDetails myUser = MyUser.builder()
//                .username(personCredentials.getLogin())
//                .password(personCredentials.getPassword())
//                .authorities(personCredentials.getPerson().getRoles().stream().map(role -> String.valueOf(role.getName())).toList().toArray(String[]::new))
//                .accountLocked(personCredentials.getPerson().getLocked())
//                .disabled(!personCredentials.getPerson().getEnabled())
//                .build();

        return new MyUser(
                personCredentials.getPerson().getId(),
                personCredentials.getLogin(),
                personCredentials.getPassword(),
                personCredentials.getPerson().getEnabled(),
                true,
                true,
                !personCredentials.getPerson().getLocked(),
                personCredentials.getPerson().getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }
}

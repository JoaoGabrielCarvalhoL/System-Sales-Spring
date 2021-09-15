package br.com.carv.sales.sales.services;

import br.com.carv.sales.sales.entities.User;
import br.com.carv.sales.sales.exceptions.PasswordInvalidException;
import br.com.carv.sales.sales.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails authenticate(User user) {
        UserDetails userDetails = loadUserByUsername(user.getLoginUser());
        boolean isEqualsPassword = passwordEncoder.matches(user.getPasswordUser(), userDetails.getPassword());
        if (isEqualsPassword) {
            return userDetails;
        } else {
            throw new PasswordInvalidException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginUser(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        String[] roles = user.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return org.springframework.security.core.userdetails.User.
                builder().
                username(user.getLoginUser()).
                password(user.getPasswordUser()).
                roles(roles).build();
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

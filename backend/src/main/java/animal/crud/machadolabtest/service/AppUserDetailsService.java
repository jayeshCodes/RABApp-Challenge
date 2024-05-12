package animal.crud.machadolabtest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AppUserDetailsService {
    public UserDetails loadUserByUsername(String username);

}

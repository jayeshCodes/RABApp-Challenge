package animal.crud.machadolabtest.service.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import animal.crud.machadolabtest.entity.AppUser;
import animal.crud.machadolabtest.repository.AppUserRepository;
import animal.crud.machadolabtest.service.AppUserDetailsService;

@Service
public class AppUserServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found with email:"+email));

        return new User(appUser.getEmail(), appUser.getPassword(), Collections.emptyList());
    }
    
}

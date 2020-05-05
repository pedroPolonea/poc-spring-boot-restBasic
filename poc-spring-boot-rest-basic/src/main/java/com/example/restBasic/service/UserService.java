package com.example.restBasic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.restBasic.entity.UserEntity;
import com.example.restBasic.entity.enumeration.RolesEnum;
import com.example.restBasic.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserEntity userEntity = Optional.ofNullable(userRepository.findByLogin(username)).orElseThrow(() -> new ExpressionException(""));

        return new User(userEntity.getLogin(), userEntity.getPassword(), toGrantedAuthority(userEntity.getRoles()));
    }

    private List<GrantedAuthority> toGrantedAuthority(final Collection<RolesEnum> roles){
        final List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(rolesEnum -> authorities.add(new SimpleGrantedAuthority(rolesEnum.getRole())));

        return authorities;
    }

    /*$2a$10$0BHsRqTi5ZMBrbHvZllM.uf0PUvCrDh9dj9WBPCdfoRSrYHTBwD/6*/
}

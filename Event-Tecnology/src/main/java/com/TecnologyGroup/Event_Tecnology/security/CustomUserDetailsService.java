package com.TecnologyGroup.Event_Tecnology.security;

import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findAnyUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRol().getNombreRol());

        return new UserPrincipal(
                user.getId(),
                user.getUserEmail(),
                user.getUserPassword(),
                Collections.singletonList(authority),
                user
        );

    }
}

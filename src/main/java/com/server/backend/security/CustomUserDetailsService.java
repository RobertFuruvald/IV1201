package com.server.backend.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.backend.dto.RegistrationDTO;
import com.server.backend.entity.Person;
import com.server.backend.entity.Role;
import com.server.backend.repository.PersonRepository;
import com.server.backend.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Try to find by username first
        Optional<Person> personOpt = personRepository.findByUsername(username);

        // Try to find by email if not found by username
        boolean foundByEmail = false;
        if (!personOpt.isPresent()) {
            personOpt = personRepository.findByEmail(username);
            foundByEmail = personOpt.isPresent();
        }

        // If found set the person object otherwise throw exception
        Person person = personOpt.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Use email as username if found by email, otherwise use the original username
        String principalUsername = foundByEmail ? person.getEmail() : person.getUsername();

        // Get role for person, it needs to be in a list for principal
        Optional<Role> roleOpt = roleRepository.findById(person.getRoleId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Adding standard role in case the role wasn't found as error handling for now
        if (roleOpt.isPresent())
            authorities.add(new SimpleGrantedAuthority(roleOpt.get().getName()));
        else
            authorities.add(new SimpleGrantedAuthority("applicant"));

        // Create new userprincipal for found user
        return new CustomUserDetailsPrincipal(principalUsername,
                person.getPassword(), authorities,
                person.getEmail(), person.getPersonId());
    }

}

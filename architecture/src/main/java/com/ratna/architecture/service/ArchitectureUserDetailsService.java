package com.ratna.architecture.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ratna.architecture.architecturemodel.Role;
import com.ratna.architecture.architecturemodel.User;

@Service
@Transactional
public class ArchitectureUserDetailsService implements UserDetailsService {
	@PersistenceContext(unitName = "architectureSchema")
	private EntityManager architectureSchema;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		org.springframework.security.core.userdetails.User user = null;
		Query createQuery = architectureSchema.createQuery("From User where emailId=:emailID");
		createQuery.setParameter("emailID", username);
		User res = (User) createQuery.getSingleResult();
		if (res != null) {
			// assigning our user values to spring security user object for validation
			user = new org.springframework.security.core.userdetails.User(res.getEmailId(), res.getPassword(),
					getGrantedAuthorities(res));
		} else {
			throw new UsernameNotFoundException("No user with name " + username);
		}
		return user;
	}

	// getting authorities
	public List<SimpleGrantedAuthority> getGrantedAuthorities(User res) {
		// we must save ROLE_USER in database if we used SimpleGrantedAuthority
		List<SimpleGrantedAuthority> grantList = new ArrayList<>();
		Collection<Role> role = res.getRole();
		role.forEach(roleObj -> {
			grantList.add(new SimpleGrantedAuthority(roleObj.getRole()));
		});
		return grantList;
	}

}

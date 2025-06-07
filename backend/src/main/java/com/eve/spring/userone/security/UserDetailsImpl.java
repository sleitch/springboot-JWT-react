package com.eve.spring.userone.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eve.spring.userone.models.UserOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author sleitch
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String displayName;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

  
    public UserDetailsImpl(UserOne user, List<GrantedAuthority> authorities2) {
    	 this.id = user.getId();
         this.username =  user.getUsername();
         this.email =  user.getEmail();
         this.password = user.getPwd();
         this.displayName =user.getDisplayName();
         this.firstName = user.getFirstName();
         this.lastName = user.getLastName();
         this.authorities = authorities2;
      }

	public static UserDetailsImpl build(UserOne user) {
    	
    	SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole());
    	
    	 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	 authorities.add(role);    			               

    	 return new UserDetailsImpl(user,authorities);
    }
    
    

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Long id;

   @Column(name = "firstname")
   private String firstName;

   @Column(name = "lastname")
   private String lastName;

   @Column(name = "age")
   private int age;

   @Column(name = "email", unique = true, nullable = false)
   private String username;

   @Column(name = "password")
   private String password;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<Role> roles;

   public User(String username, String password) {
      this.username = username;
      this.password = password;
   }

   public User() {
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }

   @Override
   public String getUsername() {
      return username;
   }
   @Override
   @JsonIgnore
   public boolean isAccountNonExpired() {
      return true;
   }
   @Override
   @JsonIgnore
   public boolean isAccountNonLocked() {
      return true;
   }
   @Override
   @JsonIgnore
   public boolean isCredentialsNonExpired() {
      return true;
   }
   @Override
   @JsonIgnore
   public boolean isEnabled() {
      return true;
   }

   public void setRole(Role role) {
      roles.add(role);
   }

}

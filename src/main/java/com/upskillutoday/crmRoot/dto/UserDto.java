//package com.upskillutoday.crmRoot.dto;
//
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.userdetails.User;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Entity
//public class UserDto extends User {
//    private String id;
//
//    public UserDto(String username, String password) {
//        super(username, password, new ArrayList<>());
//    }
//
//    public UserDto(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }
//
//    public UserDto() {
//        super("foo", "foo", new ArrayList<>());
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Id
//    public String getId() {
//        return id;
//    }
//}

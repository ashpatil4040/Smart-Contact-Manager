package com.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)  
    private String email;
    
    private String password;
    
    @Column(length = 64000)
    private String about;
    
    @Column(length = 64000)
    private String profilePic ;
    
    private String phoneNumber;

    //information 
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    //SElf,GOOGLE,FACEBOOK,TWITTER,INSTAGRAM
    @Enumerated(value = EnumType.STRING)
    private Providers provider= Providers.SELF;
    private String providerUserId;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)    
    private List<Contact> contacts=new ArrayList<>();

   

}

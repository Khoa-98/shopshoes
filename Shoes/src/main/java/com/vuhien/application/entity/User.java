package com.vuhien.application.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
@TypeDef(
        name ="json",
        typeClass = JsonStringType.class
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email",nullable = false,length = 200)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "avatar")
    private String avatar;
    @Type(type = "json")
    @Column(name = "roles",nullable = false,columnDefinition = "json")
    private List<String> roles;
    @Column(name = "status",columnDefinition = "BOOLEAN")
    private boolean status;
    @Column(name = "created_at")

    private Timestamp createdAt;
    @Column(name = "modified_at")
    private Timestamp modifiedAt;
}

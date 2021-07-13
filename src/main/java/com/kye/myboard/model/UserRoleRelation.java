package com.kye.myboard.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "r_user_role")
@Data
public class UserRoleRelation implements Serializable {

    @Id
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @Id
    @Column(name = "role_id", insertable = false, updatable = false)
    private int roleId;

    @Enumerated(EnumType.STRING)
    private RoleType role;
}

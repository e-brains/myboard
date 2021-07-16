package com.kye.myboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "roles") // User클래스에 있는 private List<Role> roles 변수명을 적어준다.
    @JsonIgnore    // 무한참조를 막기 위해 추가한다.
    private List<User> users = new ArrayList<User>();

}

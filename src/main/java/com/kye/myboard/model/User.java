package com.kye.myboard.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String email;
    private boolean enabled;

    @ManyToMany
    @JoinTable( name = "r_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private List<Role> roles = new ArrayList<Role>();

    // 사용자가 작성한 게시글만 보기위해
    @OneToMany(mappedBy="user",            // 키 컬럼을 만들지 않고 참조한다. board에 선언해 두었던 변수명 사용
            cascade = CascadeType.REMOVE)  // user정보 삭제시 먼저 board삭제 후 user정보가 삭제됨
    private List<Board> boards = new ArrayList<>();

    // 양방행 조인인 경우 일반적으로 Many에 해당하는 쪽에서 즉 board에서 조인을 걸고
    // One에 해당하는 user에서는 mappedBy를 이용하여 참조한다.
    // cascade옵션이외에도 orphanRemoval = true하면 부모가 없는 board는 모두 자동으로 삭제해 준다.

    @CreationTimestamp
    private Timestamp createDate;

}

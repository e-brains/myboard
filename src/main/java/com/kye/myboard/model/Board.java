package com.kye.myboard.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Data
public class Board {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=2, max=30, message="제목은 2자 이상 30자 이하입니다.") // 최대값 최소값 설정
    private String title;
    private String content;

    // board에 user_id 컬럼을 생성하여 user의 PK컬럼을 조인한다.
    // 만약 user에 pk가 없으면 여기서 referencedColumnName="id"라고 강제 명명을 해야한다.
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore   // Json생성 시 재귀호출 방출
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

}

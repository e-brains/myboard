package com.kye.myboard.dto;

import lombok.Data;

@Data
public class Greeting {

    private final long id;  // 생성자를 통해 초기값을 할당 = 생성자 생성될때 마다 값을 할당, 이외에는 값변경 불가
    private final String content;

    public Greeting( long id, String content){
        this.id = id;
        this.content = content;
    }

}

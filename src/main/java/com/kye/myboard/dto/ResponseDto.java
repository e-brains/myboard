package com.kye.myboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ajax에서 공통으로 사용하는 응답 DTO를 하나 만든다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {

    int status;
    T data;

}

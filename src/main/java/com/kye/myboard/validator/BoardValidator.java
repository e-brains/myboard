package com.kye.myboard.validator;

import com.kye.myboard.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        Board board = (Board) obj; // 형변환

        // 프레임워크에서 제공하는 StringUtils 사용
        // isEmpty대신  hasLength(String) or hasText(String)을 사용한다.
        //if (StringUtils.isEmpty(board.getTitle())){
        if (!StringUtils.hasLength(board.getTitle())){  // 타이틀이 비어 있으면

            // 원래는 key값에 따라서 다국어 처리 되지만 여기서는 key 및 다국어 처리를 하지 않고 있으르모
            // key는 아무거나 주고 3번째 파라미터에 내용을 적는다.
            errors.rejectValue("title", "key", "타이틀을 입력해 주세요");

        } else if (!StringUtils.hasLength(board.getContent())){ // 내용이 비어 있으면
            errors.rejectValue("content", "key", "내용을 입력해 주세요");
        }
    }
}

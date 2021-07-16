package com.kye.myboard.service;

import com.kye.myboard.model.Role;
import com.kye.myboard.model.User;
import com.kye.myboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void 회원가입저장(User user){

        String encPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encPassword);
        user.setEnabled(true);

        // 관리자 권한은 서버에서 직접 넣어 줘야 하니 여기서 셋팅
        Role role = new Role();
        role.setId(1);
        user.getRoles().add(role);

        userRepository.save(user);
    }

}

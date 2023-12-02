package com.hummersoft.eira.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummersoft.eira.common.jwtUtil;
import com.hummersoft.eira.dto.LoginDTO;
import com.hummersoft.eira.model.User;
import com.hummersoft.eira.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private jwtUtil jwtutil;

    @Override
    public Object authenticateUser(LoginDTO loginDTO) {
        String emailId = loginDTO.getEmailID();
        String password = loginDTO.getPassword();

        User user = userRepository.findByEmailID(emailId);

        if (user != null && user.getPassword().equals(password)) {
            String token = jwtutil.generateJwt(user);

            Map<String, Object> data = new HashMap<>();
            data.put("accessToken", token);
            return data;
        } else {
            // You might want to provide more details in the error response
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Login failed. Invalid credentials.");
            return error;
        }
    }
}

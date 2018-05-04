package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.User;
import io.github.frapples.osbrainsystem.dal.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;


    public ResponseDTO<Object> addUser(User user) {
        boolean success = userRepository.addUser(user);
        return ResponseDTO.ofSuccess(success);
    }


    public ResponseDTO<List<User>> getUsers() {
        List<User> users = userRepository.getUsers();
        return ResponseDTO.ofSuccess(users);
    }

    public ResponseDTO<User> getUser(String studentId) {
        return userRepository.getUser(studentId).map(ResponseDTO::ofSuccess).orElse(ResponseDTO.ofFailed());
    }

}

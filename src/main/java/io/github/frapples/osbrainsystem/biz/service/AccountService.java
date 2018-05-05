package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.dto.ResponseStatusEnum;
import io.github.frapples.osbrainsystem.biz.model.SchoolClass;
import io.github.frapples.osbrainsystem.biz.model.User;
import io.github.frapples.osbrainsystem.dal.repository.SchoolClassRepository;
import io.github.frapples.osbrainsystem.dal.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;


    public ResponseDTO<Object> addUser(User user) {
        boolean success = userRepository.addUser(user);
        return ResponseDTO.ofSuccess(success);
    }


    public ResponseDTO<List<User>> getUsers() {
        List<User> users = userRepository.getUsers();
        return ResponseDTO.ofSuccess(users);
    }

    public ResponseDTO<User> getUser(String studentId) {
        return userRepository.getUser(studentId)
            .map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(ResponseStatusEnum.NOT_EXIST_STATUS));
    }

    public ResponseDTO addClass(SchoolClass schoolClass) {
        boolean success = schoolClassRepository.addClass(schoolClass);
        return ResponseDTO.ofSuccess(success);
    }

    public ResponseDTO<List<SchoolClass>> getClasses() {
        List<SchoolClass> classes = schoolClassRepository.getClasses();
        return ResponseDTO.ofSuccess(classes);
    }

    public ResponseDTO<SchoolClass> getClassById(Integer id) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.getClassById(id);
        return schoolClass.map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(ResponseStatusEnum.NOT_EXIST_STATUS));
    }

    public ResponseDTO updateClass(SchoolClass schoolClass) {
        boolean success = schoolClassRepository.updateClass(schoolClass);
        return ResponseDTO.ofSuccess(success);
    }

}

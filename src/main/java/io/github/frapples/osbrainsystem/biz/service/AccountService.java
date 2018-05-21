package io.github.frapples.osbrainsystem.biz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Strings;
import io.github.frapples.osbrainsystem.biz.dto.response.apistatus.UserLoginStatusEnum;
import io.github.frapples.osbrainsystem.dal.query.UserFilterQuery;
import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.dto.response.DefaultResponseStatusEnum;
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


    public ResponseDTO<Page<User>> getUsers(int page, int size, UserFilterQuery userFilterQuery) {
        Page<User> users = userRepository.getUsers(new Page<User>(page, size),
            userFilterQuery);
        return ResponseDTO.ofSuccess(users);
    }

    public ResponseDTO<User> getUser(String studentId) {
        return userRepository.getUser(studentId)
            .map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(DefaultResponseStatusEnum.NOT_EXIST_STATUS));
    }

    public ResponseDTO userLoginFirst(String name, String phone, String studentId, Integer classId) {
        Optional<User> student = userRepository.getUserByPhone(phone);
        if (student.isPresent()) {
            return ResponseDTO.ofFailed(UserLoginStatusEnum.USER_EXISTS_STATUS);
        } else if (userRepository.getUser(studentId).isPresent()) {
            return ResponseDTO.ofFailed(UserLoginStatusEnum.STUDENTID_EXISTS_STATUS);
        } else {
            User user = User.builder()
                .name(name)
                .phone(phone)
                .studentId(studentId)
                .classId(classId).build();
            userRepository.addUser(user);
            return ResponseDTO.ofSuccess();
        }
    }

    public ResponseDTO userLogin(String phone) {
        Optional<User> student = userRepository.getUserByPhone(phone);
        return student.isPresent() ? ResponseDTO.ofSuccess() : ResponseDTO.ofFailed(UserLoginStatusEnum.USER_NOT_EXISTS_STATUS);
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
            .orElse(ResponseDTO.ofFailed(DefaultResponseStatusEnum.NOT_EXIST_STATUS));
    }

    public ResponseDTO updateClass(SchoolClass schoolClass) {
        boolean success = schoolClassRepository.updateClass(schoolClass);
        return ResponseDTO.ofSuccess(success);
    }

    public ResponseDTO<User> getUserByPhone(String phone) {
        return userRepository.getUserByPhone(phone)
            .map(ResponseDTO::ofSuccess)
            .orElse(ResponseDTO.ofFailed(DefaultResponseStatusEnum.NOT_EXIST_STATUS));
    }
}

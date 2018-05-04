package io.github.frapples.osbrainsystem.biz.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.User;
import io.github.frapples.osbrainsystem.dal.dao.UserDO;
import io.github.frapples.osbrainsystem.dal.mapper.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserMapper userMapper;


    public ResponseDTO<Object> addUser(User user) {
        userMapper.insert(ModelConverter.convert(user, UserDO.class));
        return ResponseDTO.ofSuccess(null);
    }


    public ResponseDTO<List<User>> getUsers() {
        List<User> users = ModelConverter.convert(userMapper.selectList(new EntityWrapper<UserDO>()), User.class);
        return ResponseDTO.ofSuccess(users);
    }

    public ResponseDTO<User> getUser(String studentId) {
        if (studentId == null) {
            return ResponseDTO.ofFailed();
        }
        List<UserDO> resultDO = userMapper.selectList(new EntityWrapper<UserDO>().eq("studentId", studentId));
        List<User> result = ModelConverter.convert(resultDO, User.class);
        if (result.isEmpty()) {
            return  ResponseDTO.ofFailed();
        } else {
            return ResponseDTO.ofSuccess(result.get(0));
        }
    }

}

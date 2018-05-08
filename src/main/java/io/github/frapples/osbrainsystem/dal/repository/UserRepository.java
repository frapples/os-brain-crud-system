package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.User;
import io.github.frapples.osbrainsystem.dal.dao.UserDO;
import io.github.frapples.osbrainsystem.dal.ext.ConditionExt;
import io.github.frapples.osbrainsystem.dal.mapper.UserMapper;
import io.github.frapples.osbrainsystem.dal.query.UserFilterQuery;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public boolean addUser(User user) {
        UserDO userDO = ModelConverter.convert(user, UserDO.class);
        if (userDO == null) {
            return false;
        } else {
            return userMapper.insert(userDO) >= 1;
        }
    }

    public Page<User> getUsers(Page<User> page, UserFilterQuery userFilterQuery) {
        // List<UserDO> dos = userMapper.selectList(new EntityWrapper<>());
        Wrapper ew =  ConditionExt.create()
            .eqWhenNotEmpty("user.name", userFilterQuery.getName())
            .eqWhenNotEmpty("user.student_id", userFilterQuery.getStudentId())
            .eqWhenNotEmpty("user.class_id", userFilterQuery.getClassId());
        List<UserDO> dos = userMapper.selectWithClass(page, (Wrapper<UserDO>) ew);
        List<User> users = ModelConverter.convert(dos, User.class);
        page.setRecords(users);
        return page;
    }

    public Optional<User> getUser(String studentId) {
        if (studentId == null) {
            return Optional.empty();
        }
        List<UserDO> resultDO = userMapper.selectList(
            new EntityWrapper<UserDO>()
                .eq("studentId", studentId));
        List<User> result = ModelConverter.convert(resultDO, User.class);
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }

}

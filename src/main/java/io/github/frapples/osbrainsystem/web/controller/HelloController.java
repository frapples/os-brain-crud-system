package io.github.frapples.osbrainsystem.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.ImmutableMap;
import io.github.frapples.osbrainsystem.dal.dao.UserDO;
import io.github.frapples.osbrainsystem.dal.mapper.UserMapper;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class HelloController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> hello() {
        return ImmutableMap.of("message", "hello!");
    }

    @RequestMapping(value = "/dbhello", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> dbhello() {
        List<UserDO> message = userMapper.selectList(new EntityWrapper<UserDO>());
        return ImmutableMap.of("message", message);
    }
}

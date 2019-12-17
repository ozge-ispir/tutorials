package com.example.demo.service;

import com.example.demo.helper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserJSON;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    public List<UserJSON> getAllUser() {
        log.info("Called for getAllUser...");
        List<User> userList = userRepository.findAll();
        return userMapper.mapTo(userList);
    }

    @Cacheable(cacheManager = "redisCacheManager", cacheNames = "users", key = "#id")
    public UserJSON getUserById(long id) {
        log.info("Called for getUserById ...");
        User u = userRepository.getOne(id);
        return userMapper.mapTo(u);
    }

    public UserJSON addUser(UserJSON user) {
        User u = userRepository.save(userMapper.mapTo(user));
        return userMapper.mapTo(u);
    }
}

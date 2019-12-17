package com.example.demo.controller;

import com.example.demo.pojo.UserJSON;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = {"User"})
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "Get all User")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserJSON> getAll() {
        return userService.getAllUser();
    }
}

package cn.karent.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karent.entity.User;
import cn.karent.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by wan on 2017/1/17.
 */
@Controller
@RequestMapping("/hibernate")
@EnableAutoConfiguration
@Api
public class HibernateController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("getUserById/{id}")
    @ResponseBody
    @ApiOperation(httpMethod="GET",value="根据用户ID获取用户详细信息！")
    public User getUserById(@ApiParam(required=true,name="用户ID")@PathVariable Long id) {
        User u = userRepository.findOne(id);
		System.out.println("userRepository: " + userRepository);
		System.out.println("id: " + id);
		u.setBirthDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(u.getBirthDayStr()));
        return u;
    }

    @RequestMapping("saveUser/{username}/{address}/{sex}")
    @ResponseBody
    @ApiOperation(httpMethod="POST",value="保存一个用户")
    public String saveUser(	@ApiParam(required=true,value="用户名")@PathVariable String username,
    						@ApiParam(required=true,value="用户地址")@PathVariable String address,
    						@ApiParam(required=true,value="用户性别")@PathVariable String sex) {
        User u = new User();
        u.setUserName(username);
        u.setAddress(address);
        u.setBirthDayStr(new Date());
        u.setSex(sex);
        userRepository.save(u);
        return "200 OK";
    }
}
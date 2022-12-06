package io.github.yangxj96.service.user.controller;

import io.github.yangxj96.bean.user.User;
import io.github.yangxj96.common.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/users")
    public R users() {
        var list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            list.add(new User(i + 1, "name" + i, (i / 2 == 0) ? "男" : "女"));
        }
        return R.success(list);
    }

}

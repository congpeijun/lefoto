/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.popularize;

import com.lefoto.common.base.Const;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping(value = "/populize")
public class UserPopulize {

    @Autowired
    UserService userService;
    static String userNamePath = "D:/NBWS/lefoto/web/WEB-INF/englishUserName.txt";

    @RequestMapping(value = "/addUser")
    public @ResponseBody
    String userCreation() throws FileNotFoundException, IOException {
        LeUser user = new LeUser();
        String userName = getUserName();
        user.setNickName(userName);
        String email = "";
        int index = 0;
        while (true) {
            if (index == 0) {
                email = userName + "@lefoto.me";
            } else {
                email = userName + String.valueOf(index) + "@lefoto.me";
            }
            if (userService.checkEmailExist(email)) {
                index++;
            } else {
                user.setEmail(userName);
                break;
            }
        }
        user.setPassword(userName);
        Random random = new Random();
        boolean result = random.nextBoolean();
        if (result) {
            user.setSex(1);
        } else {
            user.setSex(0);
        }
        System.out.println(result);
        user.setSex(random.nextBoolean() == true ? 1 : 0);
        System.out.println(user.getSex());
        userService.addUser(user);
        return Const.SUCCESS;
    }

    private static String getUserName() throws FileNotFoundException, IOException {
        File file = new File(userNamePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String userName = null;
        Random random = new Random();
        int randomNum = random.nextInt(4900);
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((userName = reader.readLine()) != null) {
            // 显示行号
            if (line == randomNum) {
                reader.close();
                return userName;
            }
            line++;
        }
        reader.close();
        return null;
    }
}
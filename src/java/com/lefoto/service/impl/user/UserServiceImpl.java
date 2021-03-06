/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.user;

import com.lefoto.common.cache.UserCache;
import com.lefoto.dao.iface.user.RelationDao;
import com.lefoto.dao.iface.user.UserDao;
import com.lefoto.model.user.LeDefaultUserFace;
import com.lefoto.model.user.LeRelationship;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeUserInfo;
import com.lefoto.model.user.LeUserStatus;
import com.lefoto.service.iface.user.UserService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eric
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RelationDao relationDao;

    @Override
    public void addUser(LeUser user) {
        this.userDao.addUser(user);
    }

    @Override
    public void delUser(LeUser user) {
        this.userDao.delUser(user);
    }

    @Override
    public void updateUser(LeUser user) {
        this.userDao.updateUser(user);
    }

    @Override
    public void updateUserFace(String userFace, int userId) {
        this.userDao.updateUserFace(userFace, userId);
    }

    @Override
    public List<LeUser> findAllUsers() {
        return this.userDao.findAllUsers();
    }

    @Override
    public LeUser findUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }

    @Override
    public LeUser finUserById(int userId) {
        return this.userDao.findUserById(userId);
    }

    @Override
    public boolean checkUser(String email, String password) {
        return this.userDao.checkUser(email, password);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return this.userDao.checkEmailExist(email);
    }

    @Override
    public void addOrUpdateUserInfo(LeUserInfo userInfo) {
        this.userDao.addOrUpdateUserInfo(userInfo);
    }

    @Override
    public void addDefaultUserFace(LeDefaultUserFace defaultUserFace) {
        this.userDao.addDefaultUserFace(defaultUserFace);
    }

    @Override
    public LeDefaultUserFace findDefaultUserFaceById(int id) {
        return this.userDao.findDefaultUserFaceById(id);
    }

    @Override
    public List<LeDefaultUserFace> findAllDefaultUserFace() {
        return this.userDao.findAllDefaultUserFace();
    }

    @Override
    public LeDefaultUserFace findRandomDefaultUserFace() {
        List<LeDefaultUserFace> defaultUserFaces = this.findAllDefaultUserFace();
        Random random = new Random();
        int randomNumber = random.nextInt(defaultUserFaces.size());
        return defaultUserFaces.get(randomNumber);
    }

    @Override
    public List<LeRelationship> findAllRelationships() {
        return this.relationDao.findAllRelationships();
    }

    @Override
    public void updateUserStatus(LeUserStatus userStatus) {
        this.userDao.updateUserStatus(userStatus);
    }

    @Override
    public List<LeUserStatus> findAllUserStatus() {
        return this.userDao.findAllUserStatus();
    }

    @Override
    public LeUserStatus findUserStatus(int userId) {
        return this.userDao.findUserStatus(userId);
    }
}

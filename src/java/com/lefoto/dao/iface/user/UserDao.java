/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.user;

import com.lefoto.model.user.LeAtUser;
import com.lefoto.model.user.LeDefaultUserFace;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeUserInfo;
import com.lefoto.model.user.LeUserStatus;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface UserDao {

    public void addUser(LeUser user);

    public void delUser(LeUser user);

    public List<LeUser> findAllUsers();

    public LeUser findUserByEmail(String email);

    public LeUser findUserById(int id);

    public void updateUser(LeUser user);

    public void updateUserFace(String userFace, int userId);

    public boolean checkUser(String email, String password);

    public boolean checkEmailExist(String email);

    public LeUserInfo findUserInfoByUserId(String userId);

    public void addOrUpdateUserInfo(LeUserInfo userInfo);

    public void addDefaultUserFace(LeDefaultUserFace defaultUserFace);

    public LeDefaultUserFace findDefaultUserFaceById(int id);

    public List<LeDefaultUserFace> findAllDefaultUserFace();
    
    public void updateUserStatus(LeUserStatus userStatus);
    
    public List<LeUserStatus> findAllUserStatus();
    
    public LeUserStatus findUserStatus(int userId);
    
    public void addAtUser(LeAtUser atUser);
}

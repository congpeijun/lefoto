/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.impl.user;

import com.lefoto.dao.iface.user.UserDao;
import com.lefoto.model.user.LeDefaultUserFace;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeUserInfo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eric
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(LeUser user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public void delUser(LeUser user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(user);
//        this.sessionFactory.getCurrentSession().delete(userInfo);
        session.getTransaction().commit();
    }

    @Override
    public List<LeUser> findAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeUser.class);
        List users = criteria.list();
        session.getTransaction().commit();
        if (users != null && !users.isEmpty()) {
            return users;
        } else {
            return null;
        }
    }

    @Override
    public LeUser findUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeUser.class);
        criteria.add(Restrictions.eq("email", email));
        List users = criteria.list();
        session.getTransaction().commit();
        if (users != null && !users.isEmpty()) {
            return (LeUser) users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public LeUser findUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeUser.class);
        criteria.add(Restrictions.eq("id", id));
        List users = criteria.list();
        session.getTransaction().commit();
        if (users != null && !users.isEmpty()) {
            return (LeUser) users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updateUser(LeUser user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
    }

    @Override
    public void updateUserFace(String userFace, int userId) {
        LeUser user = this.findUserById(userId);
        if (user != null) {
            user.setFace(userFace);
            this.updateUser(user);
        }
    }

    @Override
    public boolean checkUser(String email, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeUser.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.eq("password", password));
        List users = criteria.list();
        session.getTransaction().commit();
        if (users != null && users.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkEmailExist(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeUser.class);
        criteria.add(Restrictions.eq("email", email));
        List users = criteria.list();
        session.getTransaction().commit();
        if (users != null && !users.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addOrUpdateUserInfo(LeUserInfo userInfo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(userInfo);
        session.getTransaction().commit();
    }

    @Override
    public LeUserInfo findUserInfoByUserId(String userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        LeUserInfo leUserInfo = (LeUserInfo) session.get(LeUserInfo.class, userId);
        session.getTransaction().commit();
        return leUserInfo;
    }

    @Override
    public void addDefaultUserFace(LeDefaultUserFace defaultUserFace) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(defaultUserFace);
        session.getTransaction().commit();
    }

    @Override
    public LeDefaultUserFace findDefaultUserFaceById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeDefaultUserFace.class);
        criteria.add(Restrictions.eq("id", id));
        List defaultUserFaces = criteria.list();
        session.getTransaction().commit();
        if (defaultUserFaces != null && !defaultUserFaces.isEmpty()) {
            return (LeDefaultUserFace) defaultUserFaces.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<LeDefaultUserFace> findAllDefaultUserFace() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(LeDefaultUserFace.class);
        List defaultUserFaces = criteria.list();
        session.getTransaction().commit();
        if (defaultUserFaces != null && !defaultUserFaces.isEmpty()) {
            return defaultUserFaces;
        } else {
            return null;
        }
    }
}

package com.boardproject.ch4.service;

import com.boardproject.ch4.dao.UserDao;
import com.boardproject.ch4.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int getCount() throws Exception{
        return userDao.count();
    }

    @Override
    public int remove(UserDto user) throws Exception{
        return userDao.deleteUser(user);
    }

    @Override
    public int insert(UserDto user) throws Exception{
        return userDao.insertUser(user);
    }

    @Override
    public int update(UserDto user) throws Exception{
        return userDao.updateUser(user);
    }

    @Override
    public List<UserDto> getAllUser() throws Exception{
        return userDao.selectAll();
    }

    public UserDto selectUserById(String id) throws Exception{
        return userDao.selectUser(id);
    }
}

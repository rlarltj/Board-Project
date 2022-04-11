package com.boardproject.ch4.dao;

import com.boardproject.ch4.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SqlSession session;

    String namespace= "com.boardproject.ch4.dao.UserMapper.";
//    @Override
//    public int deleteUser(String id) throws Exception {
//        return session.delete(namespace+"delete", id);
//    }


    @Override
    public UserDto selectUser(String id) throws Exception {
        return session.selectOne(namespace+ "select", id);
    }

    // 사용자 정보를 user_info테이블에 저장하는 메서드

    @Override
    public int insertUser(UserDto user) throws Exception {
        return session.insert(namespace+"insert", user);
    }


    @Override
    public int updateUser(UserDto user) throws Exception {
        return session.update(namespace+"update", user);
    }

    @Override
    public List<UserDto> selectAll() throws Exception{
        return session.selectList(namespace+"selectAll");
    }
    @Override
    public int count() throws Exception {
       return session.selectOne(namespace+"count");
    }


    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int deleteUser(UserDto user) throws Exception{
        Map map = new HashMap();
        map.put("id", user.getId());
        map.put("pwd", user.getPwd());
        return session.delete(namespace+"delete", map);
    }
}
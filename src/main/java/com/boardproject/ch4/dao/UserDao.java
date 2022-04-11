package com.boardproject.ch4.dao;

import com.boardproject.ch4.domain.UserDto;

import java.util.List;

public interface UserDao {
    int deleteUser(UserDto user) throws Exception;

    UserDto selectUser(String id) throws Exception;

    int insertUser(UserDto user) throws Exception;

    int updateUser(UserDto user) throws Exception;

    List<UserDto> selectAll() throws Exception;

    int count() throws Exception;

    int deleteAll() throws Exception;
}

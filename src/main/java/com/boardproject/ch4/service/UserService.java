package com.boardproject.ch4.service;

import com.boardproject.ch4.domain.UserDto;

import java.util.List;

public interface UserService {
    int getCount() throws Exception;

    int remove(UserDto user) throws Exception;

    int insert(UserDto user) throws Exception;

    int update(UserDto user) throws Exception;

    List<UserDto> getAllUser() throws Exception;

    public UserDto selectUserById(String id) throws Exception;
}

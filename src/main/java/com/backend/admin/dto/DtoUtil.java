package com.backend.admin.dto;

import com.backend.admin.entity.User;

import java.util.ArrayList;
import java.util.List;


public class DtoUtil {

    public static UserDTO entityToDto(User user) {
        UserDTO newUserDTO = new UserDTO();
        if (user != null) {
            newUserDTO.setPassword(user.getPassword());
            newUserDTO.setUserName(user.getUserName());
            newUserDTO.setUserId(user.getId());
        }
        return newUserDTO;
    }

    public static List<UserDTO> entityListToDtoList(List<User> userList_) {
        List<UserDTO> userLIst = new ArrayList<UserDTO>();
        for (User curr : userList_) {
            userLIst.add(entityToDto(curr));
        }
        return userLIst;
    }
}

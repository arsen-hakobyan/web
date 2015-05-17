package com.backend.admin.rest;

import com.backend.admin.db.UserDAO;
import com.backend.admin.entity.User;
import com.backend.admin.dto.DtoUtil;
import com.backend.admin.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Component
@Scope("request")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    @Autowired
    private UserDAO userDAO;
    private User user;

    @GET
    @Path("/id/{userId}")
    public UserDTO getUserById(@PathParam("userId") Long userId) {

        User user = userDAO.getById(userId);
        return DtoUtil.entityToDto(user);
    }

    @GET
    public List<UserDTO> getAllUsers() {
        List<User> userList = userDAO.getAll();
        System.out.println("userList " + userList.size());

        return DtoUtil.entityListToDtoList(userList);
    }


}
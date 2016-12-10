package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fdflib.model.entity.FdfEntity;
import com.google.gson.Gson;
import models.Course;
import models.CourseSection;
import models.Section;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.CourseService;
import services.SectionService;
import services.UserService;

import java.util.List;

/**
 * Created by Mitin on 11/24/2016.
 */

public class UserController  extends Controller{


    public Result getUser(String username) {

        UserService us = new UserService();
        List<User> allUsers = us.getUserByUserName(username);
        return ok(Json.toJson(allUsers));
    }

    public Result getAllUsers(){
        UserService us = new UserService();
        List<User> allUsers = us.getAllUserss();
        return ok(Json.toJson(allUsers));
    }

}

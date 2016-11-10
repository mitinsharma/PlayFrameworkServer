package controllers;

import models.Course;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.CourseService;

import java.util.List;

/**
 * Created by brian.gormanly on 9/26/16.
 */
public class CourseController extends Controller {

    public Result getAllCources() {
        CourseService cs = new CourseService();
        List<Course> allCourses = cs. getAllCourses();
        return ok(Json.toJson(allCourses));
    }

    public Result getCourseByName(String courseName) {
        CourseService cs = new CourseService();
        //do we want this to be a list? i made the assumption that course will
        //only be things that exist and name is unique
        Course course = cs.getCourseByName(courseName);

        return ok(Json.toJson(course));
    }
//
//    @BodyParser.Of(BodyParser.Json.class)
//    public Result saveUser() {
//        JsonNode json = request().body().asJson();
//
//        System.out.println("controller:  " + json);
//
//        Gson gson = new Gson();
//
//        User me = gson.fromJson(json.toString(), User.class);
//        UserService us = new UserService();
//        us.save(me);
//
//        return ok("success!");
//    }
}
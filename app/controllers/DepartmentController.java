package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import models.Course;
import models.Department;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.DepartmentService;

import java.util.List;

/**
 * Created by Dominic Rossillo on 11/16/2016.
 */
public class DepartmentController extends Controller {
    @BodyParser.Of(BodyParser.Json.class)
    public Result saveDepartment(){
        JsonNode json = request().body().asJson();
        System.out.println("controller:  " + json);
        Gson gson = new Gson();

        Department newDepartment = gson.fromJson(json.toString(), Department.class);
        DepartmentService ds = new DepartmentService();
        ds.saveDepartment(newDepartment);
        return ok("success!");

    }
//this is getting a weird type error not totally sure why
//    public Result getDepartmentByName(String departmentName){
//        DepartmentService ds = new DepartmentService();
//        List<FdfEntity<Department>> departments = ds.getDepartmentByName(departmentName);
//        Department department= departments.get(0).current;
//        return ok(Json.toJson(department));
//    }
//tried to use this POST    /saveCoursesForDepartment/:departmentId/:courseList      controllers.DepartmentController.saveCoursesForDepartment(departmentId: Long, courseList: List[Course])
//for the route but was unsure how to handle lists of object for routes
    public Result saveCoursesForDepartment(long departmentId, List<Course> courses){
        JsonNode json = request().body().asJson();
        System.out.println("controller:  " + json);
        Gson gson = new Gson();

        Course newCourse = gson.fromJson(json.toString(), Course.class);
        DepartmentService ds = new DepartmentService();
        ds.saveCoursesForDepartment(departmentId, courses);
        return ok("success!");
    }
}

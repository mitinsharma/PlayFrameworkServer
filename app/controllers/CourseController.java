package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fdflib.model.entity.FdfEntity;
import com.google.gson.Gson;
import models.Course;
import models.CourseSection;
import models.Section;
import play.libs.Json;
import play.mvc.BodyParser;
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

    @BodyParser.Of(BodyParser.Json.class)
    public Result saveCourse() {
        JsonNode json = request().body().asJson();

        System.out.println("controller:  " + json);

        Gson gson = new Gson();

        Course newCourse = gson.fromJson(json.toString(), Course.class);
        CourseService cs = new CourseService();
        cs.saveCourse(newCourse);


        return ok("success!");
    }
    @BodyParser.Of(BodyParser.Json.class)
    public Result saveSectionsForCourse(Long courseId, List<Section> sections){
        JsonNode json = request().body().asJson();

        System.out.println("controller:  " + json);

        Gson gson = new Gson();

        Course newCourse = gson.fromJson(json.toString(), Course.class);
        CourseService cs = new CourseService();
        cs.saveSectionsForCourse(courseId,sections);


        return ok("success!");
    }

    public Result getCourseSectionByIds(long courseId,long sectionId){
        CourseService cs = new CourseService();

        List<FdfEntity<CourseSection>> course = cs.getCourseSectionByIds(courseId,sectionId);

        return ok(Json.toJson(course));
    }
}
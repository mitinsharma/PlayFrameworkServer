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
import services.SectionService;

import java.util.List;

/**
 * Created by Dominic Rossillo on 9/26/16.
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


//course controller function calls

    @BodyParser.Of(BodyParser.Json.class)
    public Result saveSection() {
        JsonNode json = request().body().asJson();

        System.out.println("controller:  " + json);

        Gson gson = new Gson();

        Section newSection = gson.fromJson(json.toString(), Section.class);
        SectionService ss = new SectionService();
        ss.saveSection(newSection);


        return ok("success!");
    }
    public Result getAllSections(){

            SectionService ss = new SectionService();
            List<Section> allSections = ss.getAllSections();
            return ok(Json.toJson(allSections));

    }
    public Result getAllSectionsWithHistory(){
        SectionService ss = new SectionService();
        List<FdfEntity<Section>> allSections = ss.getAllSectionsWithHistory();
        return ok(Json.toJson(allSections));

    }
    public Result getSectionByNumber(String sectionNumber){
        SectionService ss = new SectionService();

        Section section = ss.getSectionByNumber(sectionNumber);

        return ok(Json.toJson(section));
    }

    public Result getSectionByCourseName(String courseName){
        SectionService ss = new SectionService();

        List<FdfEntity<Section>> section = ss.getSectionByCourseName(courseName);

        return ok(Json.toJson(section));
    }
    public Result getSectionById(long sectionId){
        SectionService ss = new SectionService();

        Section section = ss.getSectionById(sectionId);

        return ok(Json.toJson(section));
    }
    public Result getSectionByNameAndNumber(String name, int sectionNumber){

        SectionService ss = new SectionService();

        FdfEntity<Section> section = ss.getSectionByNameAndNumber(name,sectionNumber);

        return ok(Json.toJson(section));
    }
}

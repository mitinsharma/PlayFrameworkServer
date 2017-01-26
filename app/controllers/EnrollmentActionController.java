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
import services.EnrollmentActionService;
import services.SectionService;

import java.util.List;

/**
 * Created by Mitin on 12/1/2016.
 */
public class EnrollmentActionController  extends Controller{



    public Result enrollMySelf(Long studentId,Long sectionId){

        EnrollmentActionService es = new EnrollmentActionService();
        es.enrollSelf(studentId, sectionId);
        return ok("200");
    }

    public Result denrollMySelf(Long studentId,Long sectionId){

        EnrollmentActionService es = new EnrollmentActionService();
        es.disenrollSelf(studentId, sectionId);
        return ok("200");
    }
}

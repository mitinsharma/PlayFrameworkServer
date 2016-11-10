package init;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdflib.persistence.database.DatabaseUtil;
import com.fdflib.service.FdfServices;
import com.fdflib.util.FdfSettings;
import models.*;
import play.Environment;
import services.*;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tsmanner on 10/22/2016.
 */
@Singleton
public class DbInit {
    private static Logger logger = Logger.getLogger("app.init.DbInit");

    private static DbInit sInstance;

    public static DbInit getInstance() {
        if(sInstance == null) sInstance = new DbInit(Environment.simple());
        return sInstance;
    }

    @Inject
    DbInit(Environment env) {
        logger.info("Team2 LMS Start");

        // Get the 4DFLib settings singleton
        FdfSettings fdfSettings = FdfSettings.getInstance();
        FdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
        FdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;
        FdfSettings.DB_ENCODING = DatabaseUtil.DatabaseEncoding.UTF8;
        FdfSettings.DB_HOST = "localhost";
        FdfSettings.DB_ROOT_USER = "team2_root";
        FdfSettings.DB_ROOT_PASSWORD = "isbestteam_root";
        FdfSettings.DB_USER = "team2";
        FdfSettings.DB_PASSWORD = "isbestteam";
        FdfSettings.DB_NAME = "mscs_lms";

        // Create an array that makes up our 4df data model.
        List<Class> myModel = new ArrayList<>();

        // Add model objects.
        myModel.add(Assignment.class);
        myModel.add(Course.class);
        myModel.add(CourseSection.class);
        myModel.add(Department.class);
        myModel.add(DepartmentCourse.class);
        myModel.add(EnrollmentAction.class);
        myModel.add(Institution.class);
        myModel.add(InstitutionDepartment.class);
        myModel.add(InstitutionTerm.class);
        myModel.add(LMS.class);
        myModel.add(MeetingTime.class);
        myModel.add(Post.class);
        myModel.add(Section.class);
        myModel.add(SectionPost.class);
        myModel.add(SectionTerm.class);
        myModel.add(SectionToMeetingTime.class);
        myModel.add(Term.class);
        myModel.add(User.class);
        myModel.add(UserAccess.class);
        myModel.add(UserPost.class);

        // Call the initialization of the library
        FdfServices.initializeFdfDataModel(myModel);

        elementTest();
        userTest();
    }

    /**
     * Calls all the model test methods
     */
    private void elementTest() {
        AccessService as = AccessService.getInstance();
        CourseService cs = new CourseService();
        LmsService ls = new LmsService();
        SectionService ss = new SectionService();
        TermService ts = new TermService();
        UserService us = new UserService();

        LMS lms = new LMS("team2-LMS");
        Institution marist = new Institution("Marist College");
        Department mscs = new Department("MSCS");
        Term fall2016 = new Term("fall 2016",
                LocalDate.of(2016, Month.AUGUST, 29),
                LocalDate.of(2016, Month.DECEMBER, 16));

        lms.institutionId = marist.id;
        marist.lmsId = lms.id;

        ts.saveTerm(fall2016);
        ls.saveLms(lms);
    }

    /**
     * Tests functionality around Users, including
     * Posts and Assignments(posting, grading)
     * UserAccess/isAuthorized(Enroll, Drop, grading, etc)
     *
     */
    public void userTest() {
        AccessService accessService = AccessService.getInstance();
        PostService postService = new PostService();
        SectionService sectionService = new SectionService();
        UserService userService = new UserService();

        User student = new User("Stan", "Student", "sstudent", "hashtagswag");
        User ta = new User("Tony", "TeeAy", "tteeay", "powertrippin");
        User faculty = new User("Frank", "Faculty", "ffaculty", "TheMan");
        Section section = new Section("TestSection", 1);

        userService.saveUser(student);
        userService.saveUser(ta);
        userService.saveUser(faculty);
        sectionService.saveSection(section);

        UserAccess sectionStudent = new UserAccess(student.id, section.id, AccessLevel.STUDENT);
        UserAccess sectionTa = new UserAccess(ta.id, section.id, AccessLevel.TA);
        UserAccess sectionFaculty = new UserAccess(faculty.id, section.id, AccessLevel.FACULTY);
        accessService.saveUserAccess(sectionStudent);
        accessService.saveUserAccess(sectionTa);
        accessService.saveUserAccess(sectionFaculty);

        List<Assignment> assignments = new ArrayList<>();
        Assignment a1 = new Assignment(60, "Stan's Essay",
                "Pretend this is an essay or something", section.id);
        Assignment a2 = new Assignment(100, "Stan's Lab",
                "Some lab that the TA has to grade", section.id);

        postService.submitAssignment(student.id, section.id, a1);
        postService.submitAssignment(student.id, section.id, a2);

        postService.gradeAssignment(student.id, a1.id, 60);
        postService.gradeAssignment(faculty.id, a1.id, 53);
        postService.gradeAssignment(ta.id, a2.id, 87);
    }
}

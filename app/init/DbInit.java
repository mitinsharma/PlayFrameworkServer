package init;

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

        serviceTest();
        assigmentTest();
        userTest();
    }

    /**
     * serviceTest calls all the model test methods
     */
    private void serviceTest() {
        AccessService as = new AccessService();
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
     * Create an Assignment as STUDENT.
     * Associate it with a Section
     * Apply a grade as TA
     * Apply a grade as FACULTY
     */
    private void assigmentTest() {
        AccessService accessService = new AccessService();
        PostService postService = new PostService();
        SectionService sectionService = new SectionService();
        UserService userService = new UserService();

        User student = new User("Stan", "Student", "sstudent", "hashtagswag");
        User ta = new User("Tony", "TeeAy", "tteeay", "powertrippin");
        User faculty = new User("Frank", "Faculty", "ffaculty", "TheMan");

        Section section = new Section("TestSection", 1);
        UserAccess sectionStudent = new UserAccess(student.id, section.id, AccessLevel.STUDENT);
        UserAccess sectionTa = new UserAccess(ta.id, section.id, AccessLevel.TA);
        UserAccess sectionFaculty = new UserAccess(faculty.id, section.id, AccessLevel.FACULTY);
        section.userAccesses.add(sectionStudent);
        section.userAccesses.add(sectionTa);
        section.userAccesses.add(sectionFaculty);
        section.userAccesses.forEach(accessService::saveUserAccess);

        List<Assignment> assignments = new ArrayList<>();
        Assignment a1 = new Assignment(60, "Stan's Essay",
                "Pretend this is an essay or something", section.id);
        Assignment a2 = new Assignment(100, "Stan's Lab",
                "Some lab that the TA has to grade", section.id);
        assignments.add(a1);
        assignments.add(a2);

        userService.saveUser(student);
        userService.saveUser(ta);
        userService.saveUser(faculty);
        sectionService.saveSection(section);
        assignments.forEach(postService::saveAssignment);
        postService.saveAssignmentsForUser(section.id, assignments);
        postService.saveAssignmentsForSection(section.id, assignments);

        postService.gradeAssignment(faculty.id, a1.id, 53);
        postService.gradeAssignment(ta.id, a2.id, 87);
    }

    /**
     * Create a Course
     * Associate it with a Department
     *     as an ADMINISTRATOR/FACULTY/TA/STUDENT?
     */
    private void courseTest() {

    }

    /**
     * Create a Department
     * Associate it with an Institution
     *     as an ADMINISTRATOR/FACULTY/TA/STUDENT?
     */
    private void departmentTest() {

    }

    /**
     * Create some EnrollmentActions
     * Associate them with a User
     * Associate them with a Section(or two)
     */
    private void entrollmentActionTest() {

    }

    /**
     * Create an Institution
     * Associate it with an LMS
     */
    private void institutionTest() {

    }

    /**
     * Create some Posts
     * Associate them with a Section
     * Associate them with a STUDENT, TA, FACULTY, and ADMINISTRATOR
     */
    private void postTest() {

    }

    /**
     * Create a Section
     * Associate it with a Course
     * Associate it with some MeetingTimes
     * Associate it with a Term
     */
    private void sectionTest() {

    }

    /**
     * Create a Term
     * Associate it with an Institution
     * Associate it with some Sections
     */
    private void termTest() {

    }

    /**
     * Create some Users
     * Associate them with some EnrollmentActions(student<->section)
     * Get a class(section) list
     */
    private void userTest() {
        UserService us = new UserService();
        PostService ps = new PostService();

        User testStudent = new User("Student", "Test", "Student", "TestPass");
        User testAdmin = new User("Admin", "Test", "Admin", "TestPass");
        User testFaculty = new User("Faculty", "Test", "Faculty", "TestPass");
        User testTa = new User("TA", "Test", "Ta", "TestPass");

        us.saveUser(testStudent);
        us.saveUser(testAdmin);
        us.saveUser(testFaculty);
        us.saveUser(testTa);

        // User Post Tests

        // test student post
        Post studentPost = new Post("Test student post", "Content of post", -1);
        ps.savePost(studentPost);

        // test admin post
        Post adminPost = new Post("Test admin post", "Content of post", -1);
        ps.savePost(adminPost);

        // test faculty post
        Post facultyPost = new Post("Test faculty post", "Content of post", -1);
        ps.savePost(facultyPost);

        // test ta post
        Post taPost = new Post("Test ta post", "Content of post", -1);
        ps.savePost(taPost);
    }
}

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
        FdfSettings.DB_ROOT_USER = "root";
        FdfSettings.DB_ROOT_PASSWORD = "262521";
        FdfSettings.DB_USER = "mitin";
        FdfSettings.DB_PASSWORD = "262521";
        FdfSettings.DB_NAME = "lms";

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
        courseTest();
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
        /*
         * Setup all required services
         */
        AccessService accessService = AccessService.getInstance();
        EnrollmentActionService enrollmentActionService = new EnrollmentActionService();
        PostService postService = new PostService();
        SectionService sectionService = new SectionService();
        UserService userService = new UserService();

        /*
         * Setup and save the required LMS Elements(just a section)
         */

        Section section = new Section("TestSection", 1);
        sectionService.saveSection(section);


        /*
         * Setup and save some users
         */
        User student = new User("Stan", "Student", "sstudent", "hashtagswag");
        userService.saveUser(student);
        User ta = new User("Tony", "TeeAy", "tteeay", "powertrippin");
        userService.saveUser(ta);
        User faculty = new User("Frank", "Faculty", "ffaculty", "TheMan");
        userService.saveUser(faculty);
        User administrator = new User("Adam", "Administrator", "aadmin", "TheMansBoss");
        userService.saveUser(administrator);

        /*
         * Setup and save the access levels
         */
        UserAccess sectionStudent = new UserAccess(student.id, section.id, AccessLevel.STUDENT);
        UserAccess sectionTa = new UserAccess(ta.id, section.id, AccessLevel.TA);
        UserAccess sectionFaculty = new UserAccess(faculty.id, section.id, AccessLevel.FACULTY);
        UserAccess sectionAdminstrator = new UserAccess(administrator.id, section.id, AccessLevel.ADMINISTRATOR);
        accessService.saveUserAccess(sectionStudent);
        accessService.saveUserAccess(sectionTa);
        accessService.saveUserAccess(sectionFaculty);
        accessService.saveUserAccess(sectionAdminstrator);

        /*
         * Test interacting with some assignments
         */
        Assignment sa1 = new Assignment(60, "Stan's Essay",
                "Pretend this is an essay or something", section.id);
        Assignment sa2 = new Assignment(100, "Stan's Lab",
                "Some lab that the TA has to grade", section.id);
        Assignment ta1 = new Assignment(100, "Tony's Assignment",
                "Silly TA... Assignments are for kids!", section.id);
        Assignment fa1 = new Assignment(100, "Frank's Assignment",
                "Silly faculty... Assignments are for kids!", section.id);
        Assignment aa1 = new Assignment(100, "Adam's Assignment",
                "Silly administrator... Assignments are for kids!", section.id);

        // TODO: Have assignment/post submission setup the Post[User/Section] relationships
        // These should get saved
        postService.submitAssignment(student.id, sa1);
        postService.submitAssignment(student.id, sa2);
        // These should not
        postService.submitAssignment(ta.id, fa1);
        postService.submitAssignment(faculty.id, fa1);
        postService.submitAssignment(administrator.id, fa1);

        // These shouldn't be applied
        postService.gradeAssignment(student.id, sa1.id, 60);
        postService.gradeAssignment(administrator.id, sa2.id, 0); // He's a jerk
        // These should
        postService.gradeAssignment(faculty.id, sa1.id, 53);
        postService.gradeAssignment(ta.id, sa2.id, 87);

        // TODO: Make some posts and save them.
        //    Have the submission create relationships.
        //    Make UserAssignment and SectionAssignment relationship objects and add them to the model.
        /*
         * Test interacting with enrollment
         *     This block should do the following:
         *     ADD -> DROP -> ADD -> DROP
         */
        // This should add then drop student
        enrollmentActionService.enrollSelf(student.id, section.id);
        enrollmentActionService.disenrollSelf(student.id, section.id);
        // This should add then drop student ONCE
        enrollmentActionService.enrollOther(administrator.id, student.id, section.id);
        enrollmentActionService.enrollOther(administrator.id, student.id, section.id);
        enrollmentActionService.disenrollOther(administrator.id, student.id, section.id);
        enrollmentActionService.disenrollOther(administrator.id, student.id, section.id);
        // These should have no effect
        enrollmentActionService.enrollOther(faculty.id, student.id, section.id);
        enrollmentActionService.disenrollOther(faculty.id, student.id, section.id);
        enrollmentActionService.enrollOther(ta.id, student.id, section.id);
        enrollmentActionService.disenrollOther(ta.id, student.id, section.id);
    }

    public void courseTest() {
        CourseService cs = new CourseService();
        SectionService ss = new SectionService();
        Course testCourse = new Course("Intro to Programming", 101);
        Section testSection1 = new Section("Intro section", 1);
        ss.saveSection(testSection1);
        Section testSection2 = new Section("Intro section", 2);
        ss.saveSection(testSection2);

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection1);
        sectionList.add(testSection2);

        cs.saveCourse(testCourse);
        cs.saveSectionsForCourse(testCourse.id, sectionList);
    }
}

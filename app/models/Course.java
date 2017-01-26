package models;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class Course extends LmsAccessElement {
    public String name;
    public int  code;

    public Course() { super(); }
    public Course(String courseName,int courseCode) {
        super();
        name = courseName;
        code = courseCode;
    }
}

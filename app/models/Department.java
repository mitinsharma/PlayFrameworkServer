package models;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class Department extends LmsAccessElement {
    public Department() {}
    public Department(String inName) {
        name = inName;
    }

    public String name;
}

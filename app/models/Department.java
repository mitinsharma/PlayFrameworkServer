package models;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class Department extends LmsAccessElement {
    public Department() { super(); }
    public Department(String inName) {
        super();
        name = inName;
    }

    public String name;
}

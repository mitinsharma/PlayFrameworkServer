package models;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class LMS extends LmsAccessElement {
    public LMS() { super(); }
    public LMS(String inName) {
        super();
        name = inName;
        institutionId = -1;
    }

    public String name;
    public long institutionId;
}

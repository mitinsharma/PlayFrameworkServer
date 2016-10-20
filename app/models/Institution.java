package models;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class Institution extends LmsAccessElement {
    public Institution() {}
    public Institution(String inName) {
        name = inName;
    }

    public String name;
}

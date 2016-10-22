package models;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class Institution extends LmsAccessElement {
    public Institution() { super(); }
    public Institution(String inName) {
        super();
        name = inName;
    }

    public String name;
}

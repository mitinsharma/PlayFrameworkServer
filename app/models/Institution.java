package models;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class Institution extends LmsAccessElement {
    public Institution() { super(); }
    public Institution(String inName) {
        super();
        name = inName;
        lmsId = -1;
    }

    public String name;
    public long lmsId;
}

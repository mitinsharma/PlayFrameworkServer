package models;

import java.time.LocalDate;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class Term extends LmsAccessElement {
    public Term() { super(); }
    public Term(String inName, LocalDate inStart, LocalDate inEnd) {
        super();
        name = inName;
        year = inStart.getYear();
        startDate = inStart;
        endDate = inEnd;
    }

    public String name;
    public int year; // NOTE: Is this redundant with start/end date?
    public LocalDate startDate;
    public LocalDate endDate;
}

package models;

/**
 * Created by tsmanner on 10/26/2016.
 */
public class InstitutionTerm extends LmsAccessElement {
    public InstitutionTerm() { super(); }

    public InstitutionTerm(long inInstitutionId, long inTermId) {
        super();
        institutionId = inInstitutionId;
        termId = inTermId;
    }

    public long institutionId;
    public long termId;
}

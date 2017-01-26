package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 11/1/2016.
 */
public class SectionTerm extends CommonState {
    public SectionTerm() { super(); }
    public SectionTerm(long inSectionId, long inTermId) {
        super();
        sectionId = inSectionId;
        termId = inTermId;
    }
    public long sectionId;
    public long termId;
}

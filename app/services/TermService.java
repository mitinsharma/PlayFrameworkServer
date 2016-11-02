package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.InstitutionTerm;
import models.Section;
import models.SectionTerm;
import models.Term;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tsmanner on 10/26/2016.
 */
public class TermService extends FdfCommonServices {
    public Term saveTerm(Term term) {
        // First thing first, if this guy's already in there, just do an update instead of a new add.
        List<FdfEntity<Term>> termList = getTermByName(term.name);
        if(!termList.isEmpty() &&
                termList.get(0).current != null &&
                termList.get(0).current.name.equals(term.name)) {
            term.id = termList.get(0).current.id;
        }
        save(Term.class, term);
        return term;
    }

    public List<FdfEntity<Term>> getTermByName(String name) {
        return getEntitiesByValueForPassedField(Term.class, "name", name);
    }
    //get current term
    public Term getAllTerms() { return this.getAllCurrent(Term.class).get(0); }

    public List<FdfEntity<Term>> getAllTermsWithHistory() { return this.getAll(Term.class); }

    public void deleteTerm(Long id) { setDeleteFlag(Term.class,id,-1,-1); }
    public void undeleteTerm(Long id) { removeDeleteFlag(Term.class,id,-1,-1); }

    /**
     *  InstitutionTerm services
     */
    public void saveTermsForInstitution(long institutionId, List<Term> terms) {
        for(Term term : terms) {
            InstitutionTerm institutionTerm = new InstitutionTerm(institutionId, term.id);
            List<FdfEntity<InstitutionTerm>> savedInstitutionTerms;
            savedInstitutionTerms = getInstitutionTermByIds(institutionId, term.id);
            if(!savedInstitutionTerms.isEmpty() && savedInstitutionTerms.get(0).current != null &&
                    savedInstitutionTerms.get(0).current.institutionId == institutionId &&
                    savedInstitutionTerms.get(0).current.termId == term.id) {
                institutionTerm.id = savedInstitutionTerms.get(0).current.id;
            }
            save(InstitutionTerm.class, institutionTerm);
        }
    }

    public List<FdfEntity<InstitutionTerm>> getInstitutionTermByIds(long institutionId, long termId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("institutionId", Long.toString(institutionId));
        fieldsAndValues.put("termId", Long.toString(termId));
        return getEntitiesByValuesForPassedFields(InstitutionTerm.class, fieldsAndValues);
    }

    public void deleteInstitutionTerm(InstitutionTerm institutionTerm) {
        setDeleteFlag(InstitutionTerm.class, institutionTerm.id, -1, -1);
    }

    public void undeleteInstitutionTerm(InstitutionTerm institutionTerm) {
        removeDeleteFlag(InstitutionTerm.class, institutionTerm.id, -1, -1);
    }

    /**
     *  SectionTerm services
     */
    public void saveTermsForSection(long sectionId, List<Term> terms) {
        for(Term term : terms) {
            SectionTerm sectionTerm = new SectionTerm(sectionId, term.id);
            List<FdfEntity<SectionTerm>> savedSectionTerms;
            savedSectionTerms = getSectionTermByIds(sectionId, term.id);
            if(!savedSectionTerms.isEmpty() && savedSectionTerms.get(0).current != null &&
                    savedSectionTerms.get(0).current.sectionId == sectionId &&
                    savedSectionTerms.get(0).current.termId == term.id) {
                sectionTerm.id = savedSectionTerms.get(0).current.id;
            }
            save(SectionTerm.class, sectionTerm);
        }
    }

    public List<FdfEntity<SectionTerm>> getSectionTermByIds(long sectionId, long termId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("sectionId", Long.toString(sectionId));
        fieldsAndValues.put("termId", Long.toString(termId));
        return getEntitiesByValuesForPassedFields(SectionTerm.class, fieldsAndValues);
    }

    public void deleteSectionTerm(SectionTerm sectionTerm) {
        setDeleteFlag(SectionTerm.class, sectionTerm.id, -1, -1);
    }

    public void undeleteSectionTerm(SectionTerm sectionTerm) {
        removeDeleteFlag(SectionTerm.class, sectionTerm.id, -1, -1);
    }
}

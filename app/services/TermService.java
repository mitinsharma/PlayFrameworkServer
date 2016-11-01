package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Term;

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
    //get term by name
    public List<FdfEntity<Term>> getTermByName(String name) {
        return getEntitiesByValueForPassedField(Term.class, "name", name);
    }
    //get current term
    public Term getAllSections(){
        return this.getAllCurrent(Term.class).get(0);

    }

    //get all terms with history
    public List<FdfEntity<Term>> getAllTermsWithHistory() {
        return this.getAll(Term.class);
    }




    //delete term
    public void deleteTerm(Long id){
        setDeleteFlag(Term.class,id,-1,-1);

    }
    //undelete section
    public void undeleteTerm(Long id){
        removeDeleteFlag(Term.class,id,-1,-1);
    }


}

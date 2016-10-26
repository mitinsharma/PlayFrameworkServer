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

    public List<FdfEntity<Term>> getTermByName(String name) {
        return getEntitiesByValueForPassedField(Term.class, "name", name);
    }
}

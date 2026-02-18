package com.uniovi.sdi.grademanager.validators;

import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.services.MarksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MarksValidator implements Validator {
    private final MarksService marksService;

    public MarksValidator(MarksService marksService) {
        this.marksService = marksService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark=(Mark) target;
        if(mark.getScore()<0 || mark.getScore()>10){
            errors.rejectValue("score", "Error.mark.score.invalid");
        }
        if(mark.getDescription().length()<20){
            errors.rejectValue("description", "Error.mark.description.length");
        }
    }
}

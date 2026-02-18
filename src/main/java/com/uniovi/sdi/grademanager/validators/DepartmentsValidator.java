package com.uniovi.sdi.grademanager.validators;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.services.DepartmentsServices;
import com.uniovi.sdi.grademanager.services.MarksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DepartmentsValidator implements Validator {
    private final DepartmentsServices departmentsServices;

    public DepartmentsValidator(DepartmentsServices departmentsServices) {
        this.departmentsServices = departmentsServices;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department=(Department) target;
        if (department.getCode().length() != 6) {
            errors.rejectValue("code", "Error.department.code.lenght");
        }
        if(departmentsServices.findByCode(department.getCode()) != null){
            errors.rejectValue("code", "Error.department.code.repited");
        }
        if(department.getName().trim().isEmpty()){
            errors.rejectValue("name", "Error.department.name.empty");
        }
        if(department.getFaculty().trim().isEmpty()){
            errors.rejectValue("faculty", "Error.department.faculty.empty");
        }
        if(department.getPhone().trim().isEmpty()){
            errors.rejectValue("phone", "Error.department.phone.empty");
        }
        if(department.getName().length() != department.getName().trim().length()){
            errors.rejectValue("name", "Error.department.name.blank");
        }
        if(department.getFaculty().length() != department.getFaculty().trim().length()){
            errors.rejectValue("faculty", "Error.department.faculty.blank");
        }
        if(department.getPhone().length() != department.getPhone().trim().length()){
            errors.rejectValue("phone", "Error.department.phone.blank");
        }
    }
}

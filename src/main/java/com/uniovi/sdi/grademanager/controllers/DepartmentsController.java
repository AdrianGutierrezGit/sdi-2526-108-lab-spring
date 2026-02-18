package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.services.DepartmentsServices;
import com.uniovi.sdi.grademanager.services.MarksService;
import com.uniovi.sdi.grademanager.validators.DepartmentsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentsController {

    private final DepartmentsServices departmentsServices;
    private final DepartmentsValidator departmentsValidator;

    public DepartmentsController(DepartmentsServices departmentsServices, DepartmentsValidator departmentsValidator){
        this.departmentsServices = departmentsServices;
        this.departmentsValidator = departmentsValidator;
    }

    @PostMapping("/departments/add")
    public String setDepartment(@Validated Department department, BindingResult result, Model model) {
        departmentsValidator.validate(department,result);
        if(result.hasErrors()){
            model.addAttribute("department", department);
            return "/department/add";
        }
        departmentsServices.addDepartment(department);
        return "redirect:/departments";

    }

    @GetMapping(value = "/departments")
    public String getList(Model model) {
        model.addAttribute("departmentList",departmentsServices.getDepartments());
        return "/department/list";
    }

    @GetMapping(value = "/departments/details/{id}")
    public String getDetails(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentsServices.getDepartment(id));
        return "department/details";
    }

    @GetMapping(value = "/departments/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        departmentsServices.deleteDepartment(id);
        return "redirect:/departments";
    }

    @GetMapping(value = "/departments/add")
    public String getDepartment(Model model) {
        model.addAttribute("department", new Department() );
        return "department/add";
    }

    @GetMapping(value = "/departments/edit/{id}")
    public String getEdit(@PathVariable Long id) {
        return departmentsServices.getDepartment(id).toString();
    }

    @PostMapping(value="/departments/edit")
    public String setEdit(@ModelAttribute Department department){
        departmentsServices.addDepartment(department);
        return "redirect:/departments/details/" + department.getId();
    }
}

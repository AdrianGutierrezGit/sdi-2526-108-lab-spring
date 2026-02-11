package com.uniovi.sdi.grademanager.controllers;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.services.DepartmentsServices;
import com.uniovi.sdi.grademanager.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentsController {

    private final DepartmentsServices departmentsServices;
    public DepartmentsController(DepartmentsServices departmentsServices){
        this.departmentsServices = departmentsServices;
    }

    @PostMapping("/departments/add")
    public String setDepartment(@ModelAttribute Department department) {
        departmentsServices.addDepartment(department);
        return "añadir departamento";
    }

    @GetMapping(value = "/departments")
    public String getList() {
        return departmentsServices.getDepartments().toString();
    }

    @GetMapping(value = "/departments/details")
    public String getDetails(@RequestParam Long id) {
        return departmentsServices.getDepartment(id).toString();
    }

    @GetMapping(value = "/departments/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        departmentsServices.deleteDepartment(id);
        return departmentsServices.getDepartments().toString();
    }

    @GetMapping(value = "/departments/add")
    public String getMark() {
        return "añadir departamento";
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

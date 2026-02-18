package com.uniovi.sdi.grademanager.services;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.entities.User;
import com.uniovi.sdi.grademanager.repositories.DepartmentsRepository;
import com.uniovi.sdi.grademanager.repositories.MarksRepository;
import com.uniovi.sdi.grademanager.repositories.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DepartmentsServices {

    private final DepartmentsRepository departmentsRepository;

    public DepartmentsServices(DepartmentsRepository departmentsRepository, UsersRepository usersRepository){
        this.departmentsRepository = departmentsRepository;
    }
    @PostConstruct
    public void init() {
    }

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentsRepository.findAll().forEach(departments::add);
        return departments;
    }
    public Department getDepartment(Long id) {
        return departmentsRepository.findById(id).orElse(null);
    }
    public void addDepartment(Department department) {
        departmentsRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentsRepository.deleteById(id);
    }

    public Department findByCode(String code){
        return departmentsRepository.findByCode(code);
    }

}

package com.uniovi.sdi.grademanager.services;

import com.uniovi.sdi.grademanager.entities.Department;
import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.repositories.MarksRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DepartmentsServices {

    private final List<Department> departmentsList = new LinkedList<>();
    @PostConstruct
    public void init() {
        departmentsList.add(new Department(1L,"INF-01","Departamento de Informática","EII","+34985999996",120));
        departmentsList.add(new Department(2L,"INF-01","Departamento de Informática","EII","+34985999996",120));

    }

    public List<Department> getDepartments() {
        return departmentsList;
    }
    public Department getDepartment(Long id) {
        return departmentsList.stream()
                .filter(department -> department.getId().equals(id)).findFirst().orElse(null);
    }
    public void addDepartment(Department department) {
        if (department.getId() == null) {
            department.setId(departmentsList.getLast().getId() + 1);
        }
        departmentsList.add(department);
    }

    public void deleteDepartment(Long id) {
        departmentsList.removeIf(department -> department.getId().equals(id));
    }
}

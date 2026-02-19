package com.uniovi.sdi.grademanager.services;

import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.repositories.MarksRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MarksService {


    private final HttpSession httpSession;
    private final MarksRepository marksRepository;

    public MarksService(HttpSession httpSession, MarksRepository marksRepository) {
        this.httpSession = httpSession;
        this.marksRepository = marksRepository;
    }


    public List<Mark> getMarks() {
        List<Mark> marks = new ArrayList<Mark>();
        marksRepository.findAll().forEach(marks::add);
        return marks;
    }

    public void addMark(Mark mark) {
        marksRepository.save(mark);
    }
    public void deleteMark(Long id) {
        marksRepository.deleteById(id);
    }

    public Mark getMark(Long id) {
        Mark mark = marksRepository.findById(id).isPresent() ? marksRepository.findById(id).get() : new Mark();
        return mark;
    }

    public void setMarkResend(boolean revised, Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName();
        Mark mark = marksRepository.findById(id).isPresent() ? marksRepository.findById(id).get() : new Mark();
        if(mark.getUser().getDni().equals(dni) ) {
            marksRepository.updateResend(revised, id);
        }
    }
}

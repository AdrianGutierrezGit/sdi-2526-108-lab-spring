package com.uniovi.sdi.grademanager.services;

import com.uniovi.sdi.grademanager.entities.Mark;
import com.uniovi.sdi.grademanager.entities.User;
import com.uniovi.sdi.grademanager.repositories.MarksRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarksService {


    private final HttpSession httpSession;
    private final MarksRepository marksRepository;

    public MarksService(HttpSession httpSession, MarksRepository marksRepository) {
        this.httpSession = httpSession;
        this.marksRepository = marksRepository;
    }


    public Page<Mark> getMarks(Pageable pageable) {
        return marksRepository.findAll(pageable);
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

    public Page<Mark> getMarksForUser(Pageable pageable, User user) {
        Page<Mark> marks = new PageImpl<>(new LinkedList<>());
        if (user.getRole().equals("ROLE_STUDENT")) {
            marks = marksRepository.findAllByUser(pageable,user);}
        if (user.getRole().equals("ROLE_PROFESSOR")) {
            marks = getMarks(pageable); }
        return marks;
    }

    public Page<Mark> searchMarksByDescriptionAndNameForUser(Pageable pageable, String searchText, User user) {
        Page<Mark> marks = new PageImpl<>(new LinkedList<>());
        searchText = "%"+searchText+"%";
        if (user.getRole().equals("ROLE_STUDENT")) {
            marks = marksRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
        }
        if (user.getRole().equals("ROLE_PROFESSOR")) {
            marks = marksRepository.searchByDescriptionAndName(pageable, searchText);
        }
        return marks;
    }
}

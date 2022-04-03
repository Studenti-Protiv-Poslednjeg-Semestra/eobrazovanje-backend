package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.service.*;
import com.ftn.studentservice.web.dto.ExamDTO;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping(value = "exams")
public class ExamController {

    private final IExamService examService;
    private final IStudentService studentService;
    private final IUserService userService;
    private final ISyllabusService syllabusService;

    public ExamController(IExamService examService, IStudentService studentService, IUserService userService,
                          ISyllabusService syllabusService) {
        this.examService = examService;
        this.studentService = studentService;
        this.userService = userService;
        this.syllabusService = syllabusService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping(value = "{id}")
    public ResponseEntity<ExamDTO> getExam(Principal principal, @PathVariable Long id) {
        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        ExamDTO examDTO = examService.findOne(id);
        if(examDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // STUDENT
        // validate if student requesting exam data is the same
        // student that that data belongs to
        if (role.equals("ROLE_STUDENT")) {
            if (!Objects.equals(examDTO.getStudentDTO().getId(), user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        // TEACHER
        // validate if teacher requesting exam data is actually
        // the teacher of the subject and student of the exam.
        if (role.equals("ROLE_TEACHER")) {
            if (!examService.teacherAuthorizedForStudent(examDTO.getStudentDTO().getId(), user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(examDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping(value = "student/{id}")
    public ResponseEntity<List<ExamDTO>> allExamsForStudent(Principal principal, @PathVariable Long id, @PathParam(value = "page") Integer page){
        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        Object studentObject = studentService.findOne(id);
        if(studentObject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // STUDENT
        // validate if student requesting exam data is the same
        // student that that data belongs to
        if (role.equals("ROLE_STUDENT")) {
            if (!Objects.equals(user.getId(), id)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        // TEACHER
        // validate if teacher requesting exam data is actually
        // the teacher of the student whose data he's requesting
        if (role.equals("ROLE_TEACHER")) {
            if (!examService.teacherAuthorizedForStudent(id, user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<ExamDTO> examsForStudentForTeacher = examService.findByStudentIdForTeacher(id, user.getId(),PageRequest.of(page, 2));

            return new ResponseEntity<>(examsForStudentForTeacher, HttpStatus.OK);
        }

        List<ExamDTO> examsForStudent = examService.findByStudentId(id, PageRequest.of(page, 2));
        examsForStudent = examsForStudent == null ? new ArrayList<>() : examsForStudent;

        return new ResponseEntity<>(examsForStudent, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping(value = "syllabus/{id}")
    public ResponseEntity<List<ExamDTO>> allExamsForSyllabus(Principal principal, @PathVariable Long id, @PathParam(value = "page") Integer page){
        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        Syllabus syllabus = syllabusService.findOne(id);
        if(syllabus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // TEACHER
        // validate if teacher requesting exam data is actually
        // on the syllabus whose data he's requesting
        if (role.equals("ROLE_TEACHER")) {
            if (!examService.teacherAuthorizedForSyllabus(id, user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<ExamDTO> examsForSyllabusForTeacher = examService.findBySyllabusIdForTeacher(id, user.getId(),PageRequest.of(page, 2));

            return new ResponseEntity<>(examsForSyllabusForTeacher, HttpStatus.OK);
        }

        // ADMIN
        List<ExamDTO> examsForSyllabus = examService.findBySyllabusId(id, PageRequest.of(page, 2));
        examsForSyllabus = examsForSyllabus == null ? new ArrayList<>() : examsForSyllabus;

        return new ResponseEntity<>(examsForSyllabus, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PutMapping(value = "{id}")
    public ResponseEntity<ExamDTO> updateExam(Principal principal, @PathVariable(value = "id") final Long id, @RequestBody ExamDTO examDTO){

        if (examDTO.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(id, examDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (examService.findOne(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        // TEACHER
        // validate if teacher requesting edit of exam data is actually
        // the teacher of the subject and student of the exam
        if (role.equals("ROLE_TEACHER")) {
            if (!examService.teacherAuthorizedForStudent(examDTO.getStudentDTO().getId(), user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        ExamDTO result = examService.update(examDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

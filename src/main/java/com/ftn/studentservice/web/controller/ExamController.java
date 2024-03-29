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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "exams")
public class ExamController {

    private final IExamService examService;
    private final IStudentService studentService;
    private final IUserService userService;
    private final ISyllabusService syllabusService;

    public ExamController(IExamService examService, IStudentService studentService, IUserService userService, ISyllabusService syllabusService) {
        this.examService = examService;
        this.studentService = studentService;
        this.userService = userService;
        this.syllabusService = syllabusService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping
    public ResponseEntity<Page<ExamDTO>> getAllExams(Principal principal,
                                                     @PathParam(value = "page") Integer page,
                                                     @PathParam(value = "itemsPerPage") Integer itemsPerPage,
                                                     @PathParam(value = "examType") String examType,
                                                     @RequestParam(value = "viewType",required = false) String viewType) {
        if (!examType.equals("unfinished") && !examType.equals("finished")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }

        if (role.equals("ROLE_TEACHER")) {
            Page<ExamDTO> allExamsForTeacher = examService.findAllForTeacher(user.getId(),examType,viewType,PageRequest.of(page, itemsPerPage));
            return new ResponseEntity<Page<ExamDTO>>(allExamsForTeacher, HttpStatus.OK);
        }

        Page<ExamDTO> allExams = examService.findAll(examType,viewType,PageRequest.of(page, itemsPerPage));
        return new ResponseEntity<Page<ExamDTO>>(allExams, HttpStatus.OK);
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
            if (!Objects.equals(examDTO.getStudentDTO().getUserDTO().getId(), user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        // TEACHER
        // validate if teacher requesting exam data is actually
        // the teacher of the subject and student of the exam.
        if (role.equals("ROLE_TEACHER")) {
            if (!examService.teacherAuthorizedForStudent(examDTO.getStudentDTO().getUserDTO().getId(), user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(examDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping(value = "student/{id}")
    public ResponseEntity<Page<ExamDTO>> allExamsForStudent(Principal principal, @PathVariable Long id,
                                                            @PathParam(value = "page") Integer page,
                                                            @PathParam(value = "itemsPerPage") Integer itemsPerPage,
                                                            @PathParam(value = "examType") String examType,
                                                            @RequestParam(value = "viewType",required = false) String viewType){
        if (!examType.equals("unfinished") && !examType.equals("finished")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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
            Page<ExamDTO> examsForStudentForTeacher = examService.findByStudentIdForTeacher(id, user.getId(), examType, viewType, PageRequest.of(page, itemsPerPage));

            return new ResponseEntity<Page<ExamDTO>>(examsForStudentForTeacher, HttpStatus.OK);
        }

        Page<ExamDTO> examsForStudent = examService.findByStudentId(id, examType, viewType, PageRequest.of(page, itemsPerPage));

        return new ResponseEntity<Page<ExamDTO>>(examsForStudent, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping(value = "syllabus/{id}")
    public ResponseEntity<Page<ExamDTO>> allExamsForSyllabus(Principal principal, @PathVariable Long id,
                                                             @PathParam(value = "page") Integer page,
                                                             @PathParam(value = "itemsPerPage") Integer itemsPerPage,
                                                             @PathParam(value = "examType") String examType,
                                                             @RequestParam(value = "viewType",required = false) String viewType){
        if (!examType.equals("unfinished") && !examType.equals("finished")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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
            Page<ExamDTO> examsForSyllabusForTeacher = examService.findBySyllabusIdForTeacher(id, user.getId(), examType, viewType, PageRequest.of(page, itemsPerPage));

            return new ResponseEntity<Page<ExamDTO>>(examsForSyllabusForTeacher, HttpStatus.OK);
        }

        // ADMIN
        Page<ExamDTO> examsForSyllabus = examService.findBySyllabusId(id, examType, viewType, PageRequest.of(page, itemsPerPage));

        return new ResponseEntity<Page<ExamDTO>>(examsForSyllabus, HttpStatus.OK);
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
            if (!examService.teacherAuthorizedForStudent(examDTO.getStudentDTO().getUserDTO().getId(), user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        ExamDTO result = examService.update(examDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @PostMapping(value = "application")
    public ResponseEntity<ExamDTO> createExamApplication(Principal principal, @RequestBody Map<String,String> params){
        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        Long studentId = Long.valueOf(params.get("studentId"));
        Long examScheduleId = Long.valueOf(params.get("examScheduleId"));

        // STUDENT
        // validate if student creating exam application is the same
        // for whom application is being created
        if (role.equals("ROLE_STUDENT")) {
            if (!Objects.equals(user.getId(), studentId)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        ExamDTO examDTO = examService.createExamApplication(examScheduleId, studentId);
        if (examDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(examDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> cancelExam(Principal principal, @PathVariable(value = "id") final Long id){
        ExamDTO examDTO = examService.findOne(id);
        if (examDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        // STUDENT
        if (role.equals("ROLE_STUDENT")) {
            if (!Objects.equals(examDTO.getStudentDTO().getUserDTO().getId(), user.getId())){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        examService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

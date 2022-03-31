package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.service.*;
import com.ftn.studentservice.web.dto.ExamFrontendDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "exams")
public class ExamController {

    private final IExamService examService;
    private final IStudentService studentService;
    private final IUserService userService;
    private final IEnrollmentService enrollmentService;
    private final ISyllabusService syllabusService;

    public ExamController(IExamService examService, IStudentService studentService,
                          IUserService userService, IEnrollmentService enrollmentService, ISyllabusService syllabusService) {
        this.examService = examService;
        this.studentService = studentService;
        this.userService = userService;
        this.enrollmentService = enrollmentService;
        this.syllabusService = syllabusService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping(value = "{id}")
    public ResponseEntity<ExamFrontendDTO> getExam(Principal principal, @PathVariable Long id) {
        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        Exam exam = examService.findOne(id);
        if(exam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // STUDENT
        // validate if student requesting exam data is the same
        // student that that data belongs to
        if (role.equals("ROLE_STUDENT")) {
            if (!Objects.equals(exam.getStudent().getId(), user.getId())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        // TEACHER
        // validate if teacher requesting exam data is actually
        // the teacher of the subject of the exam.
        // add findByStudentId in EnrollmentRep then for
        // every subject/enrolment go through every teacher that teaches
        // that subject and see if the one requesting the data
        // is one of them (through principal)
        if (role.equals("ROLE_TEACHER")) {
            List<Enrollment> enrollmentsForStudent = enrollmentService.findByStudentId(exam.getStudent().getId());
            enrollmentsForStudent = enrollmentsForStudent == null ?  new ArrayList<>(): enrollmentsForStudent;
            boolean authorized = false;
            for (Enrollment enrollment : enrollmentsForStudent) {
                for (Teacher teacher : enrollment.getSubject().getProfessors()) {
                    if (Objects.equals(teacher.getId(), user.getId()) && Objects.equals(exam.getExamSchedule().getSubject().getId(), enrollment.getSubject().getId())) {
                        authorized = true;
                        break;
                    }
                }
                if (authorized) {
                    break;
                }
            }
            if (!authorized) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        ExamFrontendDTO examFrontendDTO = new ExamFrontendDTO(exam);
        return ResponseEntity
                .ok()
                .body(examFrontendDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping(value = "student/{id}")
    public ResponseEntity<List<ExamFrontendDTO>> allExamsForStudent(Principal principal, @PathVariable Long id){
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
            List<Enrollment> enrollmentsForStudent = enrollmentService.findByStudentId(id);
            enrollmentsForStudent = enrollmentsForStudent == null ?  new ArrayList<>(): enrollmentsForStudent;
            boolean authorized = false;
            for (Enrollment enrollment : enrollmentsForStudent) {
                for (Teacher teacher : enrollment.getSubject().getProfessors()) {
                    if (Objects.equals(teacher.getId(), user.getId())) {
                        authorized = true;
                        break;
                    }
                }
                if (authorized) {
                    break;
                }
            }
            if (!authorized) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<Exam> examsForStudent = examService.findByStudentId(id);
            examsForStudent = examsForStudent == null ? new ArrayList<>() : examsForStudent;
            List<Exam> examForStudentFiltered =  new ArrayList<>();
            for (Exam exam : examsForStudent) {
                for (Teacher professor : exam.getExamSchedule().getSubject().getProfessors()) {
                    if (Objects.equals(professor.getId(), user.getId())) {
                        examForStudentFiltered.add(exam);
                        break;
                    }
                }
            }

            List<ExamFrontendDTO> examFrontendDTOs =  new ArrayList<>();
            for (Exam exam : examForStudentFiltered){
                ExamFrontendDTO examFrontendDTO = new ExamFrontendDTO(exam);
                examFrontendDTOs.add(examFrontendDTO);
            }

            return new ResponseEntity<>(examFrontendDTOs, HttpStatus.OK);
        }

        List<ExamFrontendDTO> examFrontendDTOs =  new ArrayList<>();
        List<Exam> examsForStudent = examService.findByStudentId(id);
        examsForStudent = examsForStudent == null ? new ArrayList<>() : examsForStudent;

        for (Exam exam : examsForStudent){
            ExamFrontendDTO examFrontendDTO = new ExamFrontendDTO(exam);
            examFrontendDTOs.add(examFrontendDTO);
        }

        return new ResponseEntity<>(examFrontendDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping(value = "syllabus/{id}")
    public ResponseEntity<List<ExamFrontendDTO>> allExamsForSyllabus(Principal principal, @PathVariable Long id){
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

        Set<Subject> subjectsForSyllabus = syllabus.getSubjects();

        // TEACHER
        // validate if teacher requesting exam data is actually
        // on the syllabus whose data he's requesting
        if (role.equals("ROLE_TEACHER")) {
            boolean authorized = false;

            for (Subject subject : subjectsForSyllabus) {
                for (Teacher teacher : subject.getProfessors()) {
                    if (Objects.equals(teacher.getId(), user.getId())) {
                        authorized = true;
                        break;
                    }
                }
                if (authorized) {
                    break;
                }
            }
            if (!authorized) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }


            List<Exam> examsForSyllabus = new ArrayList<>();
            List<Exam> allExams = examService.findAll();
            allExams = allExams == null ? new ArrayList<>() : allExams;
            for (Exam exam : allExams){
                for (Subject subject : subjectsForSyllabus){
                    boolean examFound = false;
                    for (Teacher professor : subject.getProfessors()) {
                        if (Objects.equals(subject.getId(), exam.getExamSchedule().getSubject().getId()) && Objects.equals(professor.getId(), user.getId())) {
                            examsForSyllabus.add(exam);
                            examFound = true;
                            break;
                        }
                    }
                    if (examFound) break;
                }
            }

            List<ExamFrontendDTO> examFrontendDTOs = new ArrayList<>();
            for (Exam exam : examsForSyllabus){
                ExamFrontendDTO examFrontendDTO = new ExamFrontendDTO(exam);
                examFrontendDTOs.add(examFrontendDTO);
            }

            return new ResponseEntity<>(examFrontendDTOs, HttpStatus.OK);

        }

        // ADMIN
        List<Exam> examsForSyllabus = new ArrayList<>();
        List<Exam> allExams = examService.findAll();
        allExams = allExams == null ? new ArrayList<>() : allExams;
        for (Exam exam : allExams){
            for (Subject subject : subjectsForSyllabus){
                if (Objects.equals(subject.getId(), exam.getExamSchedule().getSubject().getId())){
                    examsForSyllabus.add(exam);
                    break;
                }
            }
        }

        List<ExamFrontendDTO> examFrontendDTOs = new ArrayList<>();
        for (Exam exam : examsForSyllabus){
            ExamFrontendDTO examFrontendDTO = new ExamFrontendDTO(exam);
            examFrontendDTOs.add(examFrontendDTO);
        }

        return new ResponseEntity<>(examFrontendDTOs, HttpStatus.OK);
    }


}

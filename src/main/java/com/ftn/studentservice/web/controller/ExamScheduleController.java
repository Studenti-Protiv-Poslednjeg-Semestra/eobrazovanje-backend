package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.model.User;
import com.ftn.studentservice.service.*;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "exam_schedules")
public class ExamScheduleController {

    private final IExamScheduleService examScheduleService;
    private final IUserService userService;
    private final ISubjectService subjectService;

    public ExamScheduleController(IExamScheduleService examScheduleService, IUserService userService, ISubjectService subjectService) {
        this.examScheduleService = examScheduleService;
        this.userService = userService;
        this.subjectService = subjectService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ExamScheduleDTO> createExamSchedule(@RequestBody ExamScheduleDTO examScheduleDTO){
        ExamScheduleDTO result = examScheduleService.save(examScheduleDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping
    public ResponseEntity<List<ExamScheduleDTO>> getAllExamSchedules(Principal principal,
                                                                     @RequestParam(value="studentId", required = false) Long studentId) {
        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        // STUDENT
        if (role.equals("ROLE_STUDENT")) {
            // check if user is trying to access someone else's exam schedules
            if (studentId != null && !Objects.equals(studentId, user.getId())){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<ExamScheduleDTO> examSchedulesForStudent = examScheduleService.findAllForStudent(user.getId());
            return new ResponseEntity<List<ExamScheduleDTO>>(examSchedulesForStudent, HttpStatus.OK);
        }

        // admin has to provide studentId whose exam schedules he's requesting
        // unlike student who's by default requesting his own
        if (studentId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ExamScheduleDTO> allExamSchedules = examScheduleService.findAllForStudent(studentId);
        return new ResponseEntity<List<ExamScheduleDTO>>(allExamSchedules, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping(value = "subject/{subjectId}")
    public ResponseEntity<List<ExamScheduleDTO>> getAllExamSchedulesForSubject(Principal principal,
                                                                     @PathVariable(value="subjectId") Long subjectId) {
        User user = userService.findUserByEmail(principal.getName());
        String role = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }
        System.out.println("role:" + role);

        Subject subject = subjectService.findOne(subjectId);
        if(subject == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // TEACHER
        // check if teacher is authorized to access subject's exam schedules
        if (role.equals("ROLE_TEACHER") && !examScheduleService.teacherAuthorizedForSubject(user.getId(),subjectId)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<List<ExamScheduleDTO>>(examScheduleService.findBySubjectId(subjectId), HttpStatus.OK);
    }

}

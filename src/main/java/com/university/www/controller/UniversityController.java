package com.university.www.controller;

import com.university.www.dto.*;
import com.university.www.model.*;
import com.university.www.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "university")
public class UniversityController {

    private StudentRepository studentRepository;
    private EnrollRepository enrollRepository;
    private TeachRepository teachRepository;
    private ExamRepository examRepository;
    private ProfessorRepository professorRepository;
    private AdministratorRepository administratorRepository;
    private CourseRepository courseRepository;
    private SemesterRepository semesterRepository;

    @Autowired
    public UniversityController(StudentRepository studentRepository, EnrollRepository enrollRepository, TeachRepository teachRepository, ExamRepository examRepository, ProfessorRepository professorRepository, AdministratorRepository administratorRepository, CourseRepository courseRepository, SemesterRepository semesterRepository) {
        this.studentRepository = studentRepository;
        this.enrollRepository = enrollRepository;
        this.teachRepository = teachRepository;
        this.examRepository = examRepository;
        this.professorRepository = professorRepository;
        this.administratorRepository = administratorRepository;
        this.courseRepository = courseRepository;
        this.semesterRepository = semesterRepository;
    }

    @GetMapping(value = "/student/{username:.*}")
    public Student findStudentByUsername(@PathVariable String username){
        return studentRepository.findOne(username);
    }

    @GetMapping(value = "/professor/{username:.*}")
    public Professor findProfessorByUsername(@PathVariable String username){
        return professorRepository.findOne(username);
    }

    @GetMapping(value = "/administrator/{username:.*}")
    public Administrator findAdministratorByUsername(@PathVariable String username){
        return administratorRepository.findOne(username);
    }

    @GetMapping(value = "/student/courses/{username:.*}")
    public SortedSet<SemesterDTO> findStudentCourses(@PathVariable String username){
        List<Enroll> list = enrollRepository.findByStudent_Account_UsernameOrderBySemesterId(username);

        Map<Semester, List<Course>> map = list.stream()
                .collect(Collectors.groupingBy(
                        Enroll::getSemester,
                        Collectors.mapping(Enroll::getCourse, Collectors.toList())
                ));

        SortedSet<SemesterDTO> semestersSet =
                new TreeSet<>(Comparator.comparing(SemesterDTO::getSemester).reversed());

        map.forEach((semester, courses) -> semestersSet.add(
                new SemesterDTO(
                        semester,
                        courses.stream()
                                .map(course -> {
                                            Teach teach = teachRepository.findByCourseAndSemester(course, semester);
                                            if (teach != null) {
                                                return new CourseDTO(course, teach.getProfessor());
                                            } else {
                                                return new CourseDTO(course, new Professor());
                                            }
                                        }
                                ).collect(Collectors.toList())))
        );

        return semestersSet;
    }

    @GetMapping(value = "/student/exams/{username:.*}")
    public List<ExamDTO> findStudentExams(@PathVariable String username){
        List<Exam> exams = examRepository.findByEnroll_Student_Account_Username(username);

        List<ExamDTO> examsList = new ArrayList<>();

        exams.forEach(exam -> examsList.add(
                    new ExamDTO(
                            exam.getEnroll().getCourse().getCode(),
                            exam.getEnroll().getCourse().getName(),
                            exam.getSession(),
                            exam.getEnroll().getCourse().getSemesterNumber(),
                            exam.getEnroll().getCourse().getEktc(),
                            exam.getGrade()
                    )
                ));
        examsList.sort(Comparator.comparing(ExamDTO::getSemesterNumber));

        return examsList;
    }

    @GetMapping(value = "/professor/courses/{username:.*}")
    public List<ProfessorCourseDTO> findProfessorCourses(@PathVariable String username){
        List<Teach> teaches = teachRepository.findByProfessor_UsernameOrderByIdDesc(username);
        List<ProfessorCourseDTO> professorCourses = new ArrayList<>();

        for (Teach teach : teaches) {
            Course course = teach.getCourse();
            Semester semester = teach.getSemester();

            List<Enroll> enrolls = enrollRepository.findByCourseAndSemester(course, semester);
            List<StudentDTO> collect = new ArrayList<>();

            for (Enroll enroll : enrolls) {
                Long enrollID = enroll.getId();
                StudentDTO studentDTO = new StudentDTO(enroll.getStudent());
                studentDTO.setEnrollID(enrollID);

                collect.add(studentDTO);
            }

            professorCourses.add(new ProfessorCourseDTO(course,semester, collect));
        }

        return professorCourses;
    }

    @PostMapping(value = "/exam")
    public ResponseEntity postExam(@RequestBody Exam exam){
        Exam save = examRepository.save(exam);
        return save != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/professor")
    public ResponseEntity postProfessor(@RequestBody Professor professor){
        Professor save = professorRepository.save(professor);
        return save != null ? ResponseEntity.status(HttpStatus.CREATED).body(save) : ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/student")
    public ResponseEntity postStudent(@RequestBody Student student){
        Student save = studentRepository.save(student);
        return save != null ? ResponseEntity.status(HttpStatus.CREATED).body(save) : ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/administrator")
    public ResponseEntity postAdministrator(@RequestBody Administrator admin){
        Administrator save = administratorRepository.save(admin);
        return save != null ? ResponseEntity.status(HttpStatus.CREATED).body(save) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/course")
    public List<Course> getCourses(){
        return courseRepository.findAllByOrderBySemesterNumberDesc();
    }

    @PostMapping(value = "/course")
    public ResponseEntity postCourse(@RequestBody Course course){
        Course save = courseRepository.save(course);
        return save != null ? ResponseEntity.status(HttpStatus.CREATED).body(save) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/semester")
    public List<Semester> getSemesters(){
        return semesterRepository.findAllByOrderByIdDesc();
    }

    @PostMapping(value = "/semester")
    public ResponseEntity getSemesters(@RequestBody Semester semester){
        Semester save = semesterRepository.save(semester);
        return save != null ? ResponseEntity.status(HttpStatus.CREATED).body(save) : ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/admin/professor")
    public List<ProfessorDTO> getAdminProfessors(){
        List<Professor> all = (List<Professor>) professorRepository.findAll();

        return all.stream()
                .map(ProfessorDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/admin/student")
    public List<Student> getAdminStudents(){
        return (List<Student>) studentRepository.findAll();
    }

    @GetMapping(value = "/admin/semester/{id}")
    public List<TeachDTO> getProfessorsForSemester(@PathVariable Long id){
        List<Teach> teachList = teachRepository.findBySemesterId(id);
        return teachList.stream()
                .map(TeachDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/teach")
    public ResponseEntity postTeach(@RequestBody Teach teach){
        Teach save = null;
        if (teachRepository.findByCourseAndSemester(teach.getCourse(), teach.getSemester()) == null) {
            save = teachRepository.save(teach);
        }
        return save != null ? ResponseEntity.status(HttpStatus.CREATED).body(save) : ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/admin/enroll")
    public ResponseEntity postStudentEnrolls(@RequestBody EnrollDTO enroll){
        Semester semester = enroll.getSemester();
        Student student = enroll.getStudent();

        List<Enroll> enrolls = enrollRepository.findBySemesterAndStudent(semester, student);
        List<Long> collect = enrolls.stream()
                .map(Enroll::getCourse)
                .map(Course::getId)
                .collect(Collectors.toList());

        List<Course> courses = enroll.getCourses();
        courses.removeIf(course -> collect.contains(course.getId()));

        for (Course course : courses) {
            enrollRepository.save(new Enroll(course, semester, student));
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

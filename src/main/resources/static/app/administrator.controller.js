(function () {
    'use strict';

    angular
        .module('app')
        .controller('AdministratorController', AdministratorController);

    AdministratorController.$inject = ['$http'];

    function AdministratorController($http) {
        var vm = this;

        vm.admin = {};

        vm.course = {};
        vm.courses = [];

        vm.semester = {};
        vm.semesters = [];

        vm.semestersSummer = [];
        vm.semestersWinter = [];
        vm.professorSemesters = [];

        vm.professors = [];
        vm.teach = {
            professor : "",
            semester : ""
        };

        vm.coursesWinter = [];
        vm.coursesSummer = [];
        vm.studentCourses = [];

        vm.students = [];
        vm.enroll = {
            student : "",
            courses : []
        };

        vm.init = init;
        vm.formSubmit = formSubmit;

        vm.modalPredmetSubmit = modalPredmetSubmit;
        vm.modalPredmetReset = modalPredmetReset;

        vm.modalSemesterSubmit = modalSemesterSubmit;
        vm.modalSemesterReset = modalSemesterReset;

        vm.modalProfessorOpen = modalProfessorOpen;
        vm.modalProfessorSubmit = modalProfessorSubmit;
        vm.modalProfessorReset = modalProfessorReset;

        vm.modalStudentOpen = modalStudentOpen;
        vm.modalStudentSubmit = modalStudentSubmit;
        vm.modalStudentReset = modalStudentReset;

        vm.removeCourse = removeCourse;
        vm.addCourse = addCourse;

        function init(username) {
            var adminURL = "/university/administrator/" + username;
            var adminPromise = $http.get(adminURL);
            adminPromise.then(function (response) {
                vm.admin = response.data;
            });

            var courseURL = "/university/course";
            var coursePromise = $http.get(courseURL);
            coursePromise.then(function (response) {
                vm.courses = response.data;
                vm.courses.forEach(function (elem) {
                   if(elem.semesterNumber % 2 === 0){
                       vm.coursesSummer.push(elem);
                   } else {
                       vm.coursesWinter.push(elem);
                   }
                });
            });

            var semesterURL = "/university/semester";
            var semesterPromise = $http.get(semesterURL);
            semesterPromise.then(function (response) {
                vm.semesters = response.data;
                vm.semesters.forEach(function (elem) {
                    if(elem.summer){
                        vm.semestersSummer.push(elem);
                    }else{
                        vm.semestersWinter.push(elem);
                    }
                });
            });

            var adminProfessorURL = "/university/admin/professor";
            var adminProfessorPromise = $http.get(adminProfessorURL);
            adminProfessorPromise.then(function (response) {
                vm.professors = response.data;
            });

            var adminStudentURL = "/university/admin/student";
            var adminStudentPromise = $http.get(adminStudentURL);
            adminStudentPromise.then(function (response) {
                vm.students = response.data;
            });
        }

        function formSubmit() {
            var adminURL = "/university/administrator";
            var adminPromise = $http.post(adminURL, vm.admin);
            adminPromise.then(function (response) {
                vm.admin = response.data;
            });
        }

        function modalPredmetSubmit() {
            var course = vm.course;

            var courseURL = "/university/course";
            var coursePromise = $http.post(courseURL, course);
            coursePromise.then(function (response) {
                if (response.status === 201) {
                    course = response.data;
                    vm.courses.unshift(course);
                    if(course.semesterNumber % 2 === 0){
                        vm.coursesSummer.push(course);
                    }else {
                        vm.coursesWinter.push(course);
                    }
                }
            });

            $('#myModalPredmet').modal('hide');
            modalPredmetReset();
        }

        function modalPredmetReset() {
            vm.course = {};
        }

        function modalSemesterSubmit() {
            var semester = vm.semester;

            var semesterURL = "/university/semester";
            var semesterPromise = $http.post(semesterURL, semester);
            semesterPromise.then(function (response) {
                if (response.status === 201) {
                    semester = response.data;
                    vm.semesters.unshift(semester);
                    if(semester.summer){
                        vm.semestersSummer.push(semester);
                    }else {
                        vm.semestersWinter.push(semester);
                    }
                }
            });

            $('#myModalSemester').modal('hide');
            modalSemesterReset();
        }

        function modalSemesterReset() {
            vm.semester = {};
        }

        function modalProfessorSubmit() {
            vm.teach.professor = {username : vm.teach.professor};
            vm.teach.semester = {id : vm.teach.semester};

            var teach = vm.teach;

            var teachURL = "/university/teach";
            var teachPromise = $http.post(teachURL, teach);
            teachPromise.then(function (response) {
                if (response.status === 201) {
                    console.log("Teach Saved");
                    console.log(response.data);
                }
            });

            $('#myModalProfessor').modal('hide');
            modalPredmetReset();
        }

        function modalProfessorReset() {
            vm.teach = {
                professor : "",
                semester : ""
            };
        }

        function modalProfessorOpen(course) {
            var summer = course.semesterNumber % 2 === 0;
            vm.professorSemesters = summer ? vm.semestersSummer : vm.semestersWinter;
            vm.teach.course = { id: course.id }
        }


        function modalStudentOpen(semester) {
            vm.studentCourses = semester.summer ? vm.coursesSummer.slice() : vm.coursesWinter.slice();

            var teachURL = "/university/admin/semester/" + semester.id;
            var teachPromise = $http.get(teachURL);
            teachPromise.then(function (response) {
                var data = response.data;
                vm.studentCourses.forEach(function (elem) {
                    for(var i = 0; i < data.length; i++){
                        if(data[i].id = elem.id){
                            elem.professor = data[i].professor;
                        }
                    }
                    if(!elem.professor){
                        elem.professor = "";
                    }
                })
            });

            vm.enroll.semester = { id : semester.id }
        }

        function modalStudentReset() {
            vm.enroll = {
                student : "",
                courses : []
            };
        }

        function modalStudentSubmit() {
            vm.enroll.student = { username : vm.enroll.student };

            console.log(vm.enroll);

            var enrollURL = "/university/admin/enroll";
            var enrollPromise = $http.post(enrollURL, vm.enroll);
            enrollPromise.then(function (response) {
                console.log("Enrolls saved");
                console.log(response.data);
            });

            $('#myModalStudent').modal('hide');
            modalStudentReset();
        }

        function removeCourse(index) {
            var course = vm.enroll.courses[index];
            vm.enroll.courses.splice(index, 1);
            vm.studentCourses.push(course);
        }

        function addCourse(index) {
            var course = vm.studentCourses[index];
            vm.studentCourses.splice(index, 1);
            vm.enroll.courses.push(course);
        }
    }
})();

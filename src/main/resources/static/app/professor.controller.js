(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProfessorController', ProfessorController);

    ProfessorController.$inject = ['$http'];

    function ProfessorController($http) {
        var vm = this;

        vm.professor = {};
        vm.courses = {};
        vm.students = [];
        vm.sessions = [
            "(Зимска) Прва испитна сесија",
            "(Летна) Втора испитна сесија",
            "Август-Септемвриска испитна сесија"
        ];
        vm.grades = [6, 7, 8, 9, 10];

        vm.selectedEnroll = "";
        vm.selectedSession = "";
        vm.selectedGrade = "";

        vm.init = init;
        vm.modal = modal;
        vm.modalReset = modalReset;
        vm.modalSubmit = modalSubmit;
        vm.formSubmit = formSubmit;

        function init(username) {

            var professorURL = "/university/professor/" + username;
            var professorPromise = $http.get(professorURL);
            professorPromise.then(function (response) {
                vm.professor = response.data;
            });

            var coursesURL = "/university/professor/courses/" + username;
            var coursesPromise = $http.get(coursesURL);
            coursesPromise.then(function (response) {
                vm.courses = response.data;
            })
        }

        function modal(index) {
            vm.students = vm.courses[index].students;
        }

        function modalReset() {
            vm.selectedEnroll = "";
            vm.selectedSession = "";
            vm.selectedGrade = "";
        }

        function formSubmit() {
            var professorURL = "/university/professor";
            var professorPromise = $http.post(professorURL, vm.professor);
            professorPromise.then(function (response) {
                vm.professor = response.data;
            });
        }

        function modalSubmit() {
            var enroll = vm.selectedEnroll;
            var session = vm.selectedSession;
            var grade = vm.selectedGrade;

            var exam = {
                id : enroll,
                session : session,
                grade : grade,
                enroll : { id : enroll }
            };

            var examURL = "/university/exam";
            var examPromise = $http.post(examURL, exam);
            examPromise.then(function (response) {
                $('#myModal').modal('hide');
                modalReset();
            });
        }
    }
})();

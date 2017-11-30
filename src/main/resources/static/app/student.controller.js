(function () {
    'use strict';

    angular
        .module('app')
        .controller('StudentController', StudentController);

    StudentController.$inject = ['$http'];

    function StudentController($http) {
        var vm = this;

        vm.student = {};
        vm.courses = {};
        vm.exams = {};
        vm.init = init;
        vm.formSubmit = formSubmit;

        function init(username) {

            var studentURL = "/university/student/" + username;
            var studentPromise = $http.get(studentURL);
            studentPromise.then(function (response) {
                vm.student = response.data;
            });

            var coursesURL = "/university/student/courses/" + username;
            var coursesPromise = $http.get(coursesURL);
            coursesPromise.then(function (response) {
                vm.courses = response.data;
                if(vm.courses[0] !== undefined)
                    vm.selectedSemester = vm.courses[0].semester.id;
            });

            var examsURL = "/university/student/exams/" + username;
            var examsPromise = $http.get(examsURL);
            examsPromise.then(function (response) {
                vm.exams = response.data;
            });
        }

        function formSubmit() {
            var studentURL = "/university/student";
            var studentPromise = $http.post(studentURL, vm.student);
            studentPromise.then(function (response) {
                vm.student = response.data;
            });
        }
    }
})();

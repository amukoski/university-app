(function () {
    'use strict';

    angular
        .module('app')
        .controller('UniversityController', UniversityController);

    UniversityController.$inject = ['$http'];

    function UniversityController($http) {
        var vm = this;

        vm.cert = '/student';
    }
})();

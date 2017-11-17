angular.module('HeadModule')
.directive('userList', function() {
    return {
        restrict: 'E',
        templateUrl: 'templates/directives/user-list.html'
    }
})
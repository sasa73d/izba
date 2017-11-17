angular.module('HeadModule')
.directive('userData', function() {
    return {
        restrict: 'E',
        templateUrl: 'templates/directives/user-data.html'
    }
})
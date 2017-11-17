angular.module('HeadModule')
.directive('albumsList', function() {
    return {
        restrict: 'E',
        templateUrl: 'templates/directives/albums-list.html'
    }
})
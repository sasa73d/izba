angular.module('HeadModule')
.directive('photoList', function() {
    return {
        restrict: 'E',
        templateUrl: 'templates/directives/photos-list.html'
    }
})
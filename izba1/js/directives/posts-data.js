angular.module('HeadModule')
.directive('postsData', function() {
    return {
        restrict: 'E',
        templateUrl: 'templates/directives/posts-data.html'
    }
})
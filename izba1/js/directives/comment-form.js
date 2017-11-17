angular.module('HeadModule')
.directive('commentForm', function() {
    return {
        restrict: 'E',
        templateUrl: 'templates/directives/comment-form.html'
    }
})
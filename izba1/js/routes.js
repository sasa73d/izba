angular.module('HeadModule')
.config(['$routeProvider', function($routeProvider, passData) {
    $routeProvider
        .when('/', {
            redirectTo: '/posts'
        })
        .when('/posts', {
            templateUrl: 'templates/pages/posts/index.html'
        })
        .when('/albums', {
            templateUrl: 'templates/pages/albums/index.html'
        })
        .otherwise({redirectTo: '/'});
}]);
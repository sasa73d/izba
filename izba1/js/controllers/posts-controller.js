angular.module('HeadModule')
.controller('PostsCtrl', ['$scope','$http', 'passData', function($scope, $http, passData) {
    var urlConection = 'https://jsonplaceholder.typicode.com/posts';
    $http.get(urlConection).then(function(response) {
        if($scope.posts) {delete $scope.posts;}
        $scope.posts = response.data;
        $scope.users = passData.getUsers()
    });
    $scope.$watch(function() {return passData.getUserId()}, function(newValue, oldValue) {
        if (newValue !== oldValue) {
            passData.getUserId(newValue);
            $scope.userNameId = passData.getUserId();
        }
    });
}]);
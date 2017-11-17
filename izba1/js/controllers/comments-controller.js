angular.module('HeadModule')
.controller('CommentsCtrl',['$scope', '$http', 'passData', function($scope, $http, passData) {
    var urlConection = 'https://jsonplaceholder.typicode.com/comments';
    $http.get(urlConection).then(function (response) {
        if ($scope.comments) {
            delete  $scope.comments
        }
        $scope.comments = response.data;

    });
    $scope.addComment = function(postId) {
        var urlConect = "https://jsonplaceholder.typicode.com/comments";
        var comment = {
            postId: postId,
            name: $scope.name,
            email: $scope.email,
            body: $scope.comment
        };
        console.log(comment);

        $http.post(urlConect, comment)
            .then(function(response) {
                debugger
                $scope.comments.push(response.data);
                alert(response.statusText + ' a comment successfully');
                resetComment();
            }, function(err) {
                console.log(err);
            });
            return false;
    }
    function resetComment() {
        $scope.name = null;
        $scope.email = null;
        $scope.comment = null;
    }

}])
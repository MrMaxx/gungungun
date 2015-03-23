'use strict';

angular.module('gungungunApp')
    .controller('BehaviorDetailController', function ($scope, $stateParams, Behavior, Hero) {
        $scope.behavior = {};
        $scope.load = function (id) {
            Behavior.get({id: id}, function(result) {
              $scope.behavior = result;
            });
        };
        $scope.load($stateParams.id);
    });

'use strict';

angular.module('gungungunApp')
    .controller('ArenaDetailController', function ($scope, $stateParams, Arena, ArenaCoordinate, SpawnPoint) {
        $scope.arena = {};
        $scope.load = function (id) {
            Arena.get({id: id}, function(result) {
              $scope.arena = result;
            });
        };
        $scope.load($stateParams.id);
    });

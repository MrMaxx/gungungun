'use strict';

angular.module('gungungunApp')
    .controller('SpawnPointDetailController', function ($scope, $stateParams, SpawnPoint, Arena) {
        $scope.spawnPoint = {};
        $scope.load = function (id) {
            SpawnPoint.get({id: id}, function(result) {
              $scope.spawnPoint = result;
            });
        };
        $scope.load($stateParams.id);
    });

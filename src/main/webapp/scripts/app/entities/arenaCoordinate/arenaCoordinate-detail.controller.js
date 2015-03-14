'use strict';

angular.module('gungungunApp')
    .controller('ArenaCoordinateDetailController', function ($scope, $stateParams, ArenaCoordinate, Arena) {
        $scope.arenaCoordinate = {};
        $scope.load = function (id) {
            ArenaCoordinate.get({id: id}, function(result) {
              $scope.arenaCoordinate = result;
            });
        };
        $scope.load($stateParams.id);
    });

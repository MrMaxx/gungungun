'use strict';

angular.module('gungungunApp')
    .controller('ArenaCoordinateController', function ($scope, ArenaCoordinate, Arena) {
        $scope.arenaCoordinates = [];
        $scope.arenas = Arena.query();
        $scope.loadAll = function() {
            ArenaCoordinate.query(function(result) {
               $scope.arenaCoordinates = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            ArenaCoordinate.update($scope.arenaCoordinate,
                function () {
                    $scope.loadAll();
                    $('#saveArenaCoordinateModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            ArenaCoordinate.get({id: id}, function(result) {
                $scope.arenaCoordinate = result;
                $('#saveArenaCoordinateModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            ArenaCoordinate.get({id: id}, function(result) {
                $scope.arenaCoordinate = result;
                $('#deleteArenaCoordinateConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            ArenaCoordinate.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteArenaCoordinateConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.arenaCoordinate = {transparent: null, permeable: null, x: null, y: null, id: null};
        };
    });

'use strict';

angular.module('gungungunApp')
    .controller('ArenaController', function ($scope, Arena, ArenaCoordinate, SpawnPoint) {
        $scope.arenas = [];
        $scope.arenacoordinates = ArenaCoordinate.query();
        $scope.spawnpoints = SpawnPoint.query();
        $scope.loadAll = function() {
            Arena.query(function(result) {
               $scope.arenas = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Arena.update($scope.arena,
                function () {
                    $scope.loadAll();
                    $('#saveArenaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Arena.get({id: id}, function(result) {
                $scope.arena = result;
                $('#saveArenaModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Arena.get({id: id}, function(result) {
                $scope.arena = result;
                $('#deleteArenaConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Arena.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteArenaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.arena = {arenaKey: null, id: null};
        };
    });

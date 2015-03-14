'use strict';

angular.module('gungungunApp')
    .controller('SpawnPointController', function ($scope, SpawnPoint, Arena, ParseLinks) {
        $scope.spawnPoints = [];
        $scope.arenas = Arena.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            SpawnPoint.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.spawnPoints = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            SpawnPoint.update($scope.spawnPoint,
                function () {
                    $scope.loadAll();
                    $('#saveSpawnPointModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            SpawnPoint.get({id: id}, function(result) {
                $scope.spawnPoint = result;
                $('#saveSpawnPointModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            SpawnPoint.get({id: id}, function(result) {
                $scope.spawnPoint = result;
                $('#deleteSpawnPointConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            SpawnPoint.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSpawnPointConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.spawnPoint = {x: null, y: null, groupId: null, id: null};
        };
    });

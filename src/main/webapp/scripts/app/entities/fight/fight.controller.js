'use strict';

angular.module('gungungunApp')
    .controller('FightController', function ($scope, Fight, Party, Arena, ParseLinks) {
        $scope.fights = [];
        $scope.partys = Party.query();
        $scope.arenas = Arena.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Fight.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.fights = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Fight.update($scope.fight,
                function () {
                    $scope.loadAll();
                    $('#saveFightModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Fight.get({id: id}, function(result) {
                $scope.fight = result;
                $('#saveFightModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Fight.get({id: id}, function(result) {
                $scope.fight = result;
                $('#deleteFightConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Fight.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteFightConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.fight = {createdAt: null, processedAt: null, resultingEvents: null, id: null};
        };
    });

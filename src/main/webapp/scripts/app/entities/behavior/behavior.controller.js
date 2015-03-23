'use strict';

angular.module('gungungunApp')
    .controller('BehaviorController', function ($scope, Behavior, Hero) {
        $scope.behaviors = [];
        $scope.heros = Hero.query();
        $scope.loadAll = function() {
            Behavior.query(function(result) {
               $scope.behaviors = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Behavior.update($scope.behavior,
                function () {
                    $scope.loadAll();
                    $('#saveBehaviorModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Behavior.get({id: id}, function(result) {
                $scope.behavior = result;
                $('#saveBehaviorModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Behavior.get({id: id}, function(result) {
                $scope.behavior = result;
                $('#deleteBehaviorConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Behavior.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteBehaviorConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.behavior = {priority: null, heuristicName: null, id: null};
        };
    });

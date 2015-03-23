'use strict';

angular.module('gungungunApp')
    .controller('HeroController', function ($scope, Hero, Party, TokenBlueprint, Behavior) {
        $scope.heros = [];
        $scope.partys = Party.query();
        $scope.tokenblueprints = TokenBlueprint.query();
        $scope.behaviors = Behavior.query();
        $scope.loadAll = function() {
            Hero.query(function(result) {
               $scope.heros = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Hero.update($scope.hero,
                function () {
                    $scope.loadAll();
                    $('#saveHeroModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Hero.get({id: id}, function(result) {
                $scope.hero = result;
                $('#saveHeroModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Hero.get({id: id}, function(result) {
                $scope.hero = result;
                $('#deleteHeroConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Hero.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteHeroConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.hero = {name: null, id: null};
        };
    });

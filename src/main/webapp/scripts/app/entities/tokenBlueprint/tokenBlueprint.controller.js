'use strict';

angular.module('gungungunApp')
    .controller('TokenBlueprintController', function ($scope, TokenBlueprint) {
        $scope.tokenBlueprints = [];
        $scope.loadAll = function() {
            TokenBlueprint.query(function(result) {
               $scope.tokenBlueprints = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            TokenBlueprint.update($scope.tokenBlueprint,
                function () {
                    $scope.loadAll();
                    $('#saveTokenBlueprintModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            TokenBlueprint.get({id: id}, function(result) {
                $scope.tokenBlueprint = result;
                $('#saveTokenBlueprintModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            TokenBlueprint.get({id: id}, function(result) {
                $scope.tokenBlueprint = result;
                $('#deleteTokenBlueprintConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            TokenBlueprint.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteTokenBlueprintConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.tokenBlueprint = {tokenKey: null, health: null, actionsPerTurn: null, longRangeAttackRange: null, longRangeWeaponDamageMinimum: null, longRangeWeaponDamageMaximum: null, shortRangeAttackRange: null, shortRangeWeaponDamageMinimum: null, shortRangeWeaponDamageMaximum: null, moveRange: null, id: null};
        };
    });

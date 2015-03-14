'use strict';

angular.module('gungungunApp')
    .controller('FightDetailController', function ($scope, $stateParams, Fight, Party, Arena) {
        $scope.fight = {};
        $scope.load = function (id) {
            Fight.get({id: id}, function(result) {
              $scope.fight = result;
            });
        };
        $scope.load($stateParams.id);
    });

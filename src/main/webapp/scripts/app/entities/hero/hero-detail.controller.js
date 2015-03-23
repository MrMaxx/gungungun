'use strict';

angular.module('gungungunApp')
    .controller('HeroDetailController', function ($scope, $stateParams, Hero, Party, TokenBlueprint, Behavior) {
        $scope.hero = {};
        $scope.load = function (id) {
            Hero.get({id: id}, function(result) {
              $scope.hero = result;
            });
        };
        $scope.load($stateParams.id);
    });

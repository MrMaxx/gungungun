'use strict';

angular.module('gungungunApp')
    .controller('GameController', function ($scope, $http, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        $scope.width = 1000;
        $scope.height = 680;

        $scope.$on('KONVA:READY', function kineticReady (event, stage) {

            $http.get('scripts/app/game/engine/example.events.json').
                success(function(data) {
                    var loader = new Konva.Loader(config.assets);
                    loader.onComplete(function(){
                        var game = new GameEngine(data, stage);
                        game.run();
                    });
                    loader.load();
                });

        });

    });
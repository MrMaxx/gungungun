'use strict';

angular.module('gungungunApp')
    .controller('GameController', function ($scope, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        $scope.width = 1000;
        $scope.height = 680;

        $scope.$on('KONVA:READY', function kineticReady (event, stage) {

            var backgroundLayer = new Konva.Layer({id: 'backgrounds'});

            var bgImage = new Image();
            bgImage.onload = function() {
                var background = new Konva.Image({
                    image: bgImage,
                    width: 1000,
                    height: 680
                });
                backgroundLayer.add(background);
                stage.draw();
            };
            bgImage.src = 'http://localhost:8080/assets/images/game/arena/STAND_YOUR_GROUND.png';
            stage.add(backgroundLayer);
            /*var circle = new Konva.Circle({
                x: stage.getWidth() / 2,
                y: stage.getHeight() / 2,
                radius: 70,
                fill: 'red',
                stroke: 'black',
                strokeWidth: 4
            });
            backgroundLayer.add(circle);
             */


        });

    });

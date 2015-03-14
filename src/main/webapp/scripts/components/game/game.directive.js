'use strict';

/**
 * taken and modified from https://github.com/jonashartmann/stage-directive to make it work with konva
 */
angular.module('gungungunApp')
    .directive('stage', ['$rootScope', function  ($rootScope) {
        return {
            restrict: 'EA',
            scope: {
                stageWidth: '=',
                stageHeight: '='
            },
            link: function (scope, elem, attrs) {
                var id = attrs["id"];
                 if (!id) {
                 id = Math.random().toString(36).substring(8);
                 elem.attr('id', id);
                 }
                 var stageWidth = scope.stageWidth || 1000;
                 var stageHeight = scope.stageHeight || 680;
                 var konva = {
                 stage: new Konva.Stage({
                 container: id,
                 width: stageWidth,
                 height: stageHeight
                 })
                 };
                 scope.konva = konva;
                 // console.log('New Stage Created', kinetic.stage);
                 $rootScope.$broadcast('KONVA:READY', konva.stage);
            }
        };
    }]);
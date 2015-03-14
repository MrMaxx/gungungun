'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('arenaCoordinate', {
                parent: 'entity',
                url: '/arenaCoordinate',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.arenaCoordinate.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/arenaCoordinate/arenaCoordinates.html',
                        controller: 'ArenaCoordinateController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('arenaCoordinate');
                        return $translate.refresh();
                    }]
                }
            })
            .state('arenaCoordinateDetail', {
                parent: 'entity',
                url: '/arenaCoordinate/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.arenaCoordinate.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/arenaCoordinate/arenaCoordinate-detail.html',
                        controller: 'ArenaCoordinateDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('arenaCoordinate');
                        return $translate.refresh();
                    }]
                }
            });
    });

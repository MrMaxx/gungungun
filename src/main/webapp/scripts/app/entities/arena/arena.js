'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('arena', {
                parent: 'entity',
                url: '/arena',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.arena.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/arena/arenas.html',
                        controller: 'ArenaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('arena');
                        return $translate.refresh();
                    }]
                }
            })
            .state('arenaDetail', {
                parent: 'entity',
                url: '/arena/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.arena.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/arena/arena-detail.html',
                        controller: 'ArenaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('arena');
                        return $translate.refresh();
                    }]
                }
            });
    });

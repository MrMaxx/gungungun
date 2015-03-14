'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('spawnPoint', {
                parent: 'entity',
                url: '/spawnPoint',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.spawnPoint.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/spawnPoint/spawnPoints.html',
                        controller: 'SpawnPointController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('spawnPoint');
                        return $translate.refresh();
                    }]
                }
            })
            .state('spawnPointDetail', {
                parent: 'entity',
                url: '/spawnPoint/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.spawnPoint.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/spawnPoint/spawnPoint-detail.html',
                        controller: 'SpawnPointDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('spawnPoint');
                        return $translate.refresh();
                    }]
                }
            });
    });

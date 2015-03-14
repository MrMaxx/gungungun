'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('tokenBlueprint', {
                parent: 'entity',
                url: '/tokenBlueprint',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.tokenBlueprint.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tokenBlueprint/tokenBlueprints.html',
                        controller: 'TokenBlueprintController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('tokenBlueprint');
                        return $translate.refresh();
                    }]
                }
            })
            .state('tokenBlueprintDetail', {
                parent: 'entity',
                url: '/tokenBlueprint/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.tokenBlueprint.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/tokenBlueprint/tokenBlueprint-detail.html',
                        controller: 'TokenBlueprintDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('tokenBlueprint');
                        return $translate.refresh();
                    }]
                }
            });
    });

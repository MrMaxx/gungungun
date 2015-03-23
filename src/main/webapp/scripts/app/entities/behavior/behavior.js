'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('behavior', {
                parent: 'entity',
                url: '/behavior',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.behavior.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/behavior/behaviors.html',
                        controller: 'BehaviorController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('behavior');
                        return $translate.refresh();
                    }]
                }
            })
            .state('behaviorDetail', {
                parent: 'entity',
                url: '/behavior/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.behavior.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/behavior/behavior-detail.html',
                        controller: 'BehaviorDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('behavior');
                        return $translate.refresh();
                    }]
                }
            });
    });

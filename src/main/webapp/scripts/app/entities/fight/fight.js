'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('fight', {
                parent: 'entity',
                url: '/fight',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.fight.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fight/fights.html',
                        controller: 'FightController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('fight');
                        return $translate.refresh();
                    }]
                }
            })
            .state('fightDetail', {
                parent: 'entity',
                url: '/fight/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.fight.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fight/fight-detail.html',
                        controller: 'FightDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('fight');
                        return $translate.refresh();
                    }]
                }
            });
    });

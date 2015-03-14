'use strict';

angular.module('gungungunApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('hero', {
                parent: 'entity',
                url: '/hero',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.hero.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/hero/heros.html',
                        controller: 'HeroController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('hero');
                        return $translate.refresh();
                    }]
                }
            })
            .state('heroDetail', {
                parent: 'entity',
                url: '/hero/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'gungungunApp.hero.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/hero/hero-detail.html',
                        controller: 'HeroDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('hero');
                        return $translate.refresh();
                    }]
                }
            });
    });

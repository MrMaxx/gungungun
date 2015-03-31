'use strict';

angular.module('gungungunApp')
    .controller('UserListController', function (
        $scope, Principal, UserRankingService, UserFightService) {

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.getUserPositionedPage($scope.pagingOptions.pageSize);
        });

        $scope.filterOptions = {
            filterText: "",
            useExternalFilter: true
        };
        $scope.totalServerItems = 0;
        $scope.pagingOptions = {
            pageSizes: [15, 30, 50],
            pageSize: 15,
            currentPage: 1
        };

        $scope.getPagedDataAsync = function (pageSize, page) {
            UserRankingService.getUserPage(page,pageSize).then(function(userPage){
                $scope.totalServerItems = userPage.totalSize;
                $scope.usersInRange = $scope.prepareData(userPage.users);
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            });
        };

        $scope.prepareData = function(userList){
            angular.forEach(userList, function(data, index){
                data['isOther'] = (data.id != $scope.account.id);
            });
            return userList;
        }

        $scope.getUserPositionedPage = function (pageSize) {
            UserRankingService.getUserRankedPage($scope.account.id, pageSize).then(function(userPage){

                $scope.usersInRange = $scope.prepareData(userPage.users);
                $scope.totalServerItems = userPage.totalSize;
                $scope.pagingOptions.currentPage = userPage.page;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            });
        };

        $scope.attack = function(row){
            Logger.info("tried to create a fight for row = "+row);

             UserFightService.createFight($scope.account.id, row.entity.id).then(function(fight){
                $rootScope.$broadcast('Fight:created');
            });
        };

        $scope.$watch('pagingOptions', function (newVal, oldVal) {
            if (newVal.currentPage !== oldVal.currentPage || newVal.pageSize !== oldVal.pageSize) {
                $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
            }
        }, true);

        $scope.userInRangeOptions = {
            data: 'usersInRange',
            columnDefs: [
                {field:'rank', displayName:'Rank', width:50},
                {
                    field:'username',
                    displayName:'Name',
                    cellTemplate: '' +
                        '<div class="ngCellText" ng-class="col.colIndex()">' +
                        '  <span class="{{!row.getProperty(\'isOther\')?\'label label-success\':\'nonononono\'}}"  ng-cell-text>{{row.getProperty(col.field)}}</span>' +
                        '</div>'
                },
                {field:'score', displayName:'Score', width:80},
                {
                    displayName:'Action',
                    width:80,
                    cellTemplate:'' +
                        '<div style="display:{{row.getProperty(\'isOther\')?\'block\':\'none\'}};padding:5px;line-hight:1.0;" class="buttons">' +
                        '  <button style="line-height:9px;" ng-click="attack(row)" class="btn btn-sm btn-primary">Attack</button>' +
                        '</div>'}
            ],

            enablePaging: true,
            enableSorting: false,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions,
            enableCellSelection: false,
            beforeSelectionChange: function() {
                return false;
            }
        };
    });

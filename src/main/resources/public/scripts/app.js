/**
 * Created by shekhargulati on 10/06/14.
 */

var app = angular.module('oponygdanksapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/customers').success(function (data) {
        $scope.customers = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

/*    $scope.cusomerStatusChanged = function (customer) {
        console.log(customer);
        $http.put('/api/v1/customers/' + customer.id, customer).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }*/
});

app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.createCustomer = function () {
        console.log($scope.customer);
        $http.post('/api/v1/customers', $scope.customer).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});
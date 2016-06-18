var app = angular.module('oponygdanksapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/customer/list.html',
        resolve: {
            customers: function(Customer) {
                return Customer.query().$promise.then(function(data){
                    return data;
                });
            }
        },
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/customer/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', ['$scope', 'Customer', 'customers', function ($scope, Customer, customers) {

    $scope.customers = customers;
    $scope.selectedIndex = -1;

    $scope.select= function(i) {
        $scope.selectedIndex = i;
    };

    $scope.isCustomerSelected = function() {
        return $scope.selectedIndex < 0;
    };

    $('#listTable').on('click', '.clickable-row', function() {
        $(this).addClass('active').siblings().removeClass('active');
    });

    $scope.removeCustomer = function(idx) {
        var customer = $scope.customers[idx];
        Customer.remove({'id': customer.id}, function() {
            $scope.customers.splice(idx, 1);
            $scope.selectedIndex = -1;
        })
    }

}]);

app.controller('CreateCtrl', ['$scope', '$location', 'Customer', function ($scope, $location, Customer) {
    
    $scope.customer = new Customer();
    $scope.customer.sex = 'male';

    $scope.createCustomer = function() {
        Customer.save($scope.customer, function() {
            $location.path('/');
        });
    }
}]);

app.factory('Customer', ["$resource", function($resource) {
    return $resource('/api/v1/customers/:id', {id: '@id'}, {});
}]);

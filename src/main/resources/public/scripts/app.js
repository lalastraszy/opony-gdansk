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
        controller: 'CustomerListCtrl'
    }).when('/createCustomer', {
        templateUrl: 'views/customer/create.html',
        controller: 'CustomerCreateCtrl'
    }).when('/createForm/:customerId', {
        templateUrl: 'views/form/create.html',
        controller: 'FormCreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});


/* Customer */
app.controller('CustomerListCtrl', ['$scope', 'Customer', 'customers', 'Form', function ($scope, Customer, customers, Form) {

    $scope.customers = customers;
    $scope.selectedIndex = -1;

    $scope.select= function(i) {
        $scope.selectedIndex = i;
        $scope.getCustomersForms($scope.selectedIndex)

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
    };

    $scope.getCustomersForms = function(idx) {
        var customer = $scope.customers[idx];
        Form.get({'customerId': customer.id}, function() {

        })
    }

}]);

app.controller('CustomerCreateCtrl', ['$scope', '$location', 'Customer', function ($scope, $location, Customer) {
    
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

/* Form */
app.controller('FormCreateCtrl', ['$scope', '$routeParams', '$location', 'Form', function ($scope, $routeParams, $location, Form) {

    $scope.form = new Form();
    $scope.form.balancing = false;
    $scope.form.customerId = $routeParams.customerId;

    $scope.createForm = function() {
        Form.save($scope.form, function() {
            $location.path('/');
        });
    }
}]);

app.factory('Form', ["$resource", function($resource) {
    return $resource('/api/v1/forms/:id', {id: '@id'}, {});
}]);

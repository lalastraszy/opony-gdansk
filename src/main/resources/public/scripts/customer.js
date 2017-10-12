/* Customer */
app.controller('CustomerListCtrl', ['$scope', 'Customer', 'customers', 'Form', function ($scope, Customer, customers, Form) {

    $scope.forms = null;
    $scope.customers = customers;

    $scope.selectedIndex = -1;

    $scope.selectAndGetCustomersCars = function (selectedIndex) {
        $scope.selectedIndex = selectedIndex;
        $scope.getCustomersForms($scope.selectedIndex);
    };

    $scope.isCustomerSelected = function () {
        return $scope.selectedIndex < 0;
    };

    // TODO: Change it to angular if you can
    $('#listTable').on('click', '.clickable-row', function () {
        $(this).addClass('active').siblings().removeClass('active');
    });

    $scope.removeCustomer = function (idx) {
        var customer = $scope.customers[idx];
        Customer.remove({'id': customer.id}, function () {
            $scope.customers.splice(idx, 1);
            $scope.selectedIndex = -1;
        })
    };

    $scope.getCustomersForms = function (idx) {
        var customer = $scope.customers[idx];
        Form.query({'customerId': customer.id}).$promise.then(function (data) {
            $scope.forms = data;
        })
    };

}]);

app.controller('CustomerCreateCtrl', ['$scope', '$location', 'Customer', 'CustomerSvc', function ($scope, $location, Customer, CustomerSvc) {

    $scope.customer = new Customer();
    $scope.customer.sex = 'male';

    $scope.createCustomer = function () {
        Customer.save($scope.customer, function (data) {
            CustomerSvc.setCustomer(data);
            window.history.back();
        });
    }
}]);

app.factory('Customer', ["$resource", function ($resource) {
    return $resource('/api/v1/customers/:id', {id: '@id'}, {});
}]);
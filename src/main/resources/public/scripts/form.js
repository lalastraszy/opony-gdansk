/* Form */
app.controller('FormListCtrl', ['$scope', '$routeParams', '$location', 'Form', 'CustomerSvc', 'CarSvc', 'forms', function ($scope, $routeParams, $location, Form, CustomerSvc, CarSvc, forms) {

    $scope.forms = forms;
    $scope.searchQuery = "";

    $scope.editForm = function (id) {
        $location.path('#/createForm/'+form.id);
    };

    function find_form(id) {
        for(var i = 0; i < forms.length; i++){
            if (forms[i].id == id) {
                return forms[i];
            }
        }
        return null;
    }

}]);

app.controller('FormCreateCtrl', ['$scope', '$routeParams', '$location', 'Form', 'Car', 'CustomerSvc', 'CarSvc', 'WheelSvc', 'customers', 'cars', 'tyreBrands', 'tyreSizes', 'customer', 'car', function ($scope, $routeParams, $location, Form, Car, CustomerSvc, CarSvc, WheelSvc, customers, cars, tyreBrands, tyreSizes, customer, car) {

    $scope.form = new Form();
    $scope.form.customerId = null;
    $scope.customers = customers;
    $scope.customer = customer;
    $scope.cars = cars;
    $scope.car = car;
    $scope.showCustomersData = false;
    $scope.showCarData = false;
    $scope.showWheelTable = true;
    $scope.tyreBrands = tyreBrands;
    $scope.tyreSizes = tyreSizes;
    $scope.rimTypes = WheelSvc.getRimTypes();
    $scope.seasons = WheelSvc.getSeasons();

    $scope.addWheel = function () {
        $scope.showWheelTable = true;
        if (!$scope.car || !$scope.car.wheels) {
            return;
        }
        var wheelTableLength = $scope.car.wheels.length;
        if (wheelTableLength == 0 || !angular.equals($scope.car.wheels[wheelTableLength - 1], WheelSvc.getWheelSchema())) {
            var wheel = WheelSvc.getWheelSchema();
            $scope.car.wheels.push(wheel);
        }
    };

    $scope.$watch('customer', function (customer) {
        if (customer && customer.id) {
            $scope.form.customerId = customer.id;
            $scope.showCustomersData = true;
            CustomerSvc.setCustomer(customer);
        }
    });

    $scope.$watch('car', function (car) {
        if (car && car.id) {
            $scope.form.carId = car.id;
            $scope.showCarData = true;
            CarSvc.setCar(car);
        }
    });

    $scope.createForm = function () {
        Form.save($scope.form, function () {
            $scope.form = null;
            CustomerSvc.setCustomer(null);
            CarSvc.setCar(null);
            window.history.back();
        });
    };

    $scope.formatCarLabel = function (carId, options) {
        for (var i = 0; i < options.length; i++) {
            if (carId === options[i].id) {
                return options[i].registrationNumber;
            }
        }
    };

    $scope.formatLabel = function (model, options) {
        for (var i = 0; i < options.length; i++) {
            if (model === options[i].id) {
                return options[i].name;
            }
        }
    };

    $scope.setCustomer = function (customerId) {
        for (var i = 0; i < customers.length; i++) {
            if (customerId === customers[i].id) {
                $scope.customer = customers[i];
            }
        }
    }
}]);

app.factory('Form', ["$resource", function ($resource) {
    return $resource('/api/v1/forms', {}, {});
}]);
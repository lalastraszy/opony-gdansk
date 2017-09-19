var app = angular.module('oponygdanksapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ngAnimate',
    'ngTouch',
    'ui.bootstrap'
]);

app.config(function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider.when('/', {
        templateUrl: 'views/customer/list.html',
        resolve: {
            customers: function (Customer) {
                return Customer.query().$promise.then(function (data) {
                    return data;
                });
            }
        },
        controller: 'CustomerListCtrl'
    }).when('/customers', {
        templateUrl: 'views/customer/list.html',
        resolve: {
            customers: function (Customer) {
                return Customer.query().$promise.then(function (data) {
                    return data;
                });
            }
        },
        controller: 'CustomerListCtrl'
    }).when('/createCustomer', {
        templateUrl: 'views/customer/create.html',
        controller: 'CustomerCreateCtrl'
    }).when('/forms', {
        templateUrl: 'views/form/list.html',
        controller: 'FormListCtrl'
    }).when('/createForm', {
        templateUrl: 'views/form/create.html',
        resolve: {
            customers: function (Customer) {
                return Customer.query().$promise.then(function (data) {
                    return data;
                });
            },
            cars: function (Car) {
                return Car.query().$promise.then(function (data) {
                    return data
                });
            },
            tyreBrands: function (TyreBrand) {
                return TyreBrand.query().$promise.then(function (data) {
                    return data;
                });
            },
            tyreSizes: function (TyreSize) {
                return TyreSize.query().$promise.then(function (data) {
                    return data;
                });
            }
        },
        controller: 'FormCreateCtrl'
    }).when('/createCar/:customerId', {
        templateUrl: 'views/car/create.html',
        controller: 'CarCreateCtrl',
        resolve: {
            carBrands: function (CarBrand) {
                return CarBrand.query().$promise.then(function (data) {
                    return data;
                });
            },
            carModels: function (CarModel) {
                return CarModel.query().$promise.then(function (data) {
                    return data;
                });
            },
            tyreBrands: function (TyreBrand) {
                return TyreBrand.query().$promise.then(function (data) {
                    return data;
                });
            },
            tyreSizes: function (TyreSize) {
                return TyreSize.query().$promise.then(function (data) {
                    return data;
                });
            }
        }
    }).otherwise({
        redirectTo: '/'
    })
});


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

app.controller('CustomerCreateCtrl', ['$scope', '$location', 'Customer', function ($scope, $location, Customer) {

    $scope.customer = new Customer();
    $scope.customer.sex = 'male';

    $scope.createCustomer = function () {
        Customer.save($scope.customer, function () {
            window.history.back();
        });
    }
}]);

app.factory('Customer', ["$resource", function ($resource) {
    return $resource('/api/v1/customers/:id', {id: '@id'}, {});
}]);

/* Car */
app.controller('CarCreateCtrl', ['$scope', '$routeParams', '$location', 'Car', 'carBrands', 'carModels', 'tyreBrands', 'tyreSizes', function ($scope, $routeParams, $location, Car, carBrands, carModels, tyreBrands, tyreSizes) {

    var emptyWheelSchema = {
        'carId': null,
        'brandId': null,
        'sizeId': null,
        'quantity': null,
        'rim': null,
        'season': null,
        'comments': null,
        'isInUse': true
    };

    $scope.car = new Car();
    $scope.car.wheels = [];
    $scope.car.isInUse = true;
    $scope.car.customerId = $routeParams.customerId;
    $scope.showWheelTable = false;
    $scope.tyreBrands = tyreBrands;
    $scope.tyreSizes = tyreSizes;
    $scope.carBrands = carBrands;
    $scope.carModels = carModels;
    $scope.rimTypes = ['brak', 'aluminiowa', 'stalowa'];
    $scope.seasons = ['zima', 'lato'];

    $scope.formatLabel = function (model, options) {
        for (var i = 0; i < options.length; i++) {
            if (model === options[i].id) {
                return options[i].name;
            }
        }
    };

    $scope.createCar = function () {
        Car.save($scope.car, function () {
            $location.path('/');
        });
    };

    $scope.addWheel = function () {
        $scope.showWheelTable = true;
        var wheelTableLength = $scope.car.wheels.length;
        if (wheelTableLength == 0 || !angular.equals($scope.car.wheels[wheelTableLength - 1], emptyWheelSchema)) {
            var wheel = angular.copy(emptyWheelSchema);
            wheel.carId = angular.isUndefined($scope.car.id) ? null : $scope.car.id;
            $scope.car.wheels.push(wheel);
        }
    };
}]);

app.factory('Car', ["$resource", function ($resource) {
    return $resource('/api/v1/cars', {}, {});
}]);

/* CarBrand */
app.factory('CarBrand', ["$resource", function ($resource) {
    return $resource('/api/v1/carBrands', {}, {});
}]);

/* CarModel */
app.factory('CarModel', ["$resource", function ($resource) {
    return $resource('/api/v1/carModels/:id', {id: '@id'}, {});
}]);

/* TyreBrand */
app.factory('TyreBrand', ["$resource", function ($resource) {
    return $resource('/api/v1/tyreBrands', {}, {});
}]);

/* TyreSize */
app.factory('TyreSize', ["$resource", function ($resource) {
    return $resource('/api/v1/tyreSizes', {}, {});
}]);

/* Form */
app.controller('FormListCtrl', ['$scope', '$routeParams', '$location', 'Form', function ($scope, $routeParams, $location, Form) {

}]);

app.controller('FormCreateCtrl', ['$scope', '$routeParams', '$location', 'Form', 'Car', 'customers', 'cars', 'tyreBrands', 'tyreSizes', function ($scope, $routeParams, $location, Form, Car, customers, cars, tyreBrands, tyreSizes) {

    $scope.form = new Form();
    $scope.form.balancing = false;
    $scope.form.customerId = null;
    $scope.customers = customers;
    $scope.customer = null;
    $scope.cars = cars;
    $scope.car = null;
    $scope.showCustomersData = false;
    $scope.showCarData = false;
    $scope.showWheelTable = true;
    $scope.tyreBrands = tyreBrands;
    $scope.tyreSizes = tyreSizes;

    $scope.addWheel = function () {
        $scope.showWheelTable = true;
        var wheelTableLength = $scope.car.wheels.length;
        if (wheelTableLength == 0 || !angular.equals($scope.car.wheels[wheelTableLength - 1], emptyWheelSchema)) {
            var wheel = angular.copy(emptyWheelSchema);
            wheel.carId = angular.isUndefined($scope.car.id) ? null : $scope.car.id;
            $scope.car.wheels.push(wheel);
        }
    };

    $scope.$watch('customer', function (customer) {
        if (customer && customer.id) {
            $scope.form.customerId = customer.id;
            $scope.showCustomersData = true;
        }
    });

    $scope.$watch('car', function (car) {
        if (car && car.id) {
            $scope.form.carId = car.id;
            $scope.showCarData = true;
        }
    });

    $scope.createForm = function () {
        Form.save($scope.form, function () {
            $location.path('/');
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

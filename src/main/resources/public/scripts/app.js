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
            },
            customer: function (CustomerSvc) {
                return CustomerSvc.getCustomer();
            },
            car: function (CarSvc) {
                return CarSvc.getCar();
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

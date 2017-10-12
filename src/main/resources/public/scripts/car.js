/* Car */
app.controller('CarCreateCtrl', ['$scope', '$routeParams', '$location', 'Car', 'CarSvc', 'WheelSvc', 'carBrands', 'carModels', 'tyreBrands', 'tyreSizes', function ($scope, $routeParams, $location, Car, CarSvc, WheelSvc, carBrands, carModels, tyreBrands, tyreSizes) {



    $scope.car = new Car();
    $scope.car.wheels = [];
    $scope.car.isInUse = true;
    $scope.car.customerId = $routeParams.customerId;
    $scope.showWheelTable = false;
    $scope.tyreBrands = tyreBrands;
    $scope.tyreSizes = tyreSizes;
    $scope.carBrands = carBrands;
    $scope.carModels = carModels;
    $scope.rimTypes = WheelSvc.getRimTypes();
    $scope.seasons = WheelSvc.getSeasons();

    $scope.formatLabel = function (model, options) {
        for (var i = 0; i < options.length; i++) {
            if (model === options[i].id) {
                return options[i].name;
            }
        }
    };

    $scope.createCar = function () {
        Car.save($scope.car, function (data) {
            CarSvc.setCar(data);
            window.history.back();
        });
    };

    $scope.addWheel = function () {
        $scope.showWheelTable = true;
        var wheelTableLength = $scope.car.wheels.length;
        if (wheelTableLength == 0 || !angular.equals($scope.car.wheels[wheelTableLength - 1], WheelSvc.getWheelSchema())) {
            var wheel = WheelSvc.getWheelSchema();
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
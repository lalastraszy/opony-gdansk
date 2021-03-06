app.service("CustomerSvc", ['Customer', function ( Customer) {

    var customer = new Customer();

    return {
        getCustomer: function () {
            return customer;
        },
        setCustomer: function (value) {
            customer = value;
        }
    };

}]);

app.service("CarSvc", ['Car', function (Car) {

    var car = null;

    return {
        getCar: function () {
            return car;
        },
        setCar: function (value) {
            car = value;
        }
    };

}]);

app.service("WheelSvc", function () {
    var wheelSchema = {
        'carId': null,
        'brandId': null,
        'sizeId': null,
        'quantity': null,
        'rim': null,
        'season': null,
        'comments': null,
        'isInUse': true
    };

    var rimTypes = ['brak', 'aluminiowa', 'stalowa'];
    var seasons = ['zima', 'lato'];

    return {
        getWheelSchema: function () {
            return wheelSchema;
        },
        getRimTypes: function () {
            return rimTypes;
        },
        getSeasons: function () {
            return seasons;
        }
    }

});
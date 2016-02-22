(function (angular, $, _) {
    var app = angular.module("app",[]);

    app.config(["$interpolateProvider", function ($interpolateProvider) {
        $interpolateProvider.startSymbol('//');
        $interpolateProvider.endSymbol('//');
    }]);

}(window.angular, window.jQuery, window._));

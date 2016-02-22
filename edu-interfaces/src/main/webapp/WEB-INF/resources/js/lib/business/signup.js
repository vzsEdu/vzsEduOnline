(function (angular, $, _) {
    var app = angular.module("signupApp", ["app","ngMessages"]);

    app.constant("url", "/verifyCode/random-image");

    app.directive("compareTo", function() {
        return {
            require: "ngModel",
            scope: {
                otherModelValue: "=compareTo"
            },
            link: function(scope, element, attributes, ngModel) {

                ngModel.$validators.compareTo = function(modelValue) {
                    return modelValue == scope.otherModelValue;
                };

                scope.$watch("otherModelValue", function() {
                    ngModel.$validate();
                });
            }
        };
    });


    app.controller("signupCtrl", ["$scope", "$location", "url", function ($scope, $location, url) {

        $scope.company = false;

        $scope.verifyImgSrc = url + "?timestamp=" + (new Date()).valueOf();
        $scope.changeVersifyCode = function () {
            $scope.verifyImgSrc = url + "?timestamp=" + (new Date()).valueOf();
        };

        $('#rootwizard').bootstrapWizard({'tabClass': 'nav nav-tabs nav-justified'});

        $scope.changeTab = function (tabType) {
            $('.tab-pane').removeClass("active");
            if (tabType) {
                $('#tab2').addClass("active");
            } else {
                $('#tab1').addClass("active");
            }

            $scope.alertFlag = 'N';
            $(".alert").alert('close');
            $scope.verifyImgSrc = url + "?timestamp=" + (new Date()).valueOf();
        }
    }]);
}(window.angular, window.jQuery, window._));




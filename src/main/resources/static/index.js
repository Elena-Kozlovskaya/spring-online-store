angular.module('homework_app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/homework_app/api/v1';

    // console.log(123);

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.ProductsPage = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/homework_app/api/v1/carts/add/' + productId)
            .then(function(response) {
                $scope.loadCart();
            });
    }

    $scope.clearCart = function () {
        $http.get('http://localhost:8189/homework_app/api/v1/carts/clear')
            .then(function(response) {
                $scope.loadCart();
            });
    }

    $scope.loadCart = function () {
        $http.get(contextPath + '/carts')
            .then(function (response) {
                console.log(response.data)
                $scope.Cart = response.data;
            });
    };


    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/homework_app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    /*$scope.tryToSignUp = function () {
        $http.post('http://localhost:8189/homework_app/registration', $scope.newUser)
            .then(function (response) {
                console.log(response);
                if ($scope.newUser.username) {
                   $scope.newUser.username = null;
                }
                if ($scope.newUser.password) {
                    $scope.newUser.password = null;
                }
                if ($scope.newUser.email) {
                    $scope.newUser.email = null;
                }
            }, function errorCallback(response) {
                alert('User already exists');
            });
    };*/

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get('http://localhost:8189/homework_app/api/v1/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    }

    $scope.createOrder = function () {
        $http.get('http://localhost:8189/homework_app/api/v1/orders')
            .then(function (response) {
                alert('Order created');
            });
    }

    /*$scope.loadCart = function () {
        $http.get(contextPath + '/carts')
            .then(function (response) {
                console.log(response.data)
                $scope.CartsList = response.data;
            });
    };

    $scope.addProductToCart = function (productId) {
        console.log('Click! ' + productId);
        $http.get(contextPath + '/carts/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/carts/' + productId)
            .then(function (response) {
                alert('DELETED')
                $scope.loadCart();
            });
    };
*/
    $scope.loadProducts();
    $scope.loadCart();

});

That was working !!!!!

This is old Auth service

(function () {
'use strict';

angular.module('MenuApp')

.config(['$httpProvider', function($httpProvider) {
  $httpProvider.interceptors.push('AuthInterceptor');
}])

.factory('AuthInterceptor', function () {return {
    // Send the Authorization header with each request
        'request': function(config) {
            config.headers = config.headers || {};
            var encodedString = btoa("alex:123456");
            config.headers.Authorization = 'Basic '+encodedString;
            //console.log(config);
           return config;
       },

       'response': function(result) {
            //console.log(result);
           return result;
       },

       'responseError': function(rejection) {
           // console.log(rejection);
           return rejection;
       }


     }})
;


})();


'use strict'

var module = angular.module('logincontrollers', []);
module.controller("UserController", [ "$scope", "LoginService",
		function($scope, UserService) {
			$scope.loginDTO = {
				userName : null,
				password : null
			};
			$scope.login = function() {				
				LoginService.login($scope.loginDTO).then(function() {
					console.log("works");
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		} ]);
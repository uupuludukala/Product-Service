'use strict'

angular.module('loginservices', []).factory('LoginService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.login = function(loginDTO) {
				return $http.post(CONSTANTS.login,loginDTO);
			}
			return service;
		} ]);
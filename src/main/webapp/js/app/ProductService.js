'use strict'
angular.module('productServices', []).factory('ProductService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.getAllProducts = function() {
				return $http.get(CONSTANTS.getAllProduct);
			}
			return service;
		} ]);
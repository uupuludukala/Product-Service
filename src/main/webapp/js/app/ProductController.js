'use strict'

var module = angular.module('productControllers', []);
module.controller("ProductController", [ "$scope", "ProductService",
		function($scope, ProductService) {
			$scope.productDTO = {
				id : null,
				productCode : null,
				productName : null
			};
			$scope.getAllProducts = function() {				
				ProductService.getAllProducts().then(function(value) {
					console.log("works");
					$scope.allProducts= value.data;
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		} ]);
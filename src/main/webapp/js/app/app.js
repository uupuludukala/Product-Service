'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'productControllers',
		'productServices' ]);
demoApp.constant("CONSTANTS", {
	login : "/login",getAllProduct : "/getAllProduct"
});
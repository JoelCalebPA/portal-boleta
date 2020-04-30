'use strict';
 
var App = angular.module('myApp',['ui.router']);
 
App.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
     
    $urlRouterProvider.otherwise("/documents")
     
    $stateProvider
	    .state('documents', {
	        url: "/documents",
	        templateUrl: 'documents',
	        controller : "AlfrescoController as alfController",
	        resolve: {
	            async: ['AlfrescoService', function(AlfrescoService) {
	                return AlfrescoService.fetchDocumentsList();
	            }]
	        }
	    })
 
}]);
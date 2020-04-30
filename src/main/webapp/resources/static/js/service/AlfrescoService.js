'use strict';
 
App.factory('AlfrescoService', ['$http', '$q', function($http, $q){
	
	return {
		fetchDocumentsList: function() {//Fetches category list from server.
			return $http.get('http://localhost:8080/api/alfresco')
				.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while fetching Documents');
						return $q.reject(errResponse);
					}
			);
		},
	};

}]);
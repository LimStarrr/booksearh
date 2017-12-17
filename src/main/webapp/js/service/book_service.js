'use strict';

angular.module('BookApp').factory('BookService', ['$http', '$q', function($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8080';

    var factory = {
        createBookMark: createBookMark,
        searchBooks: searchBooks,
        searchHistoryList: searchHistoryList,
        searchBookMarkList: searchBookMarkList,
        deleteBookMark: deleteBookMark,
        login: login,
    };

    return factory;

    /* Book Mark 등록 */
    function createBookMark(bookMark) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + '/bookmarks', bookMark)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while creating book mark');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    /* Book Mark 삭제 */
    function deleteBookMark(bookMark) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + '/bookmarks', bookMark)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while delete book mark');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    /* book search */
    function searchBooks(title) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + '/books/searches/' + title)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while search books');
                    deferred.reject(errResponse);
                }
            );
        // $http.get(REST_SERVICE_URI + '/books/searches/')
        //     .then(
        //         function(response) {
        //             deferred.resolve(response.data);
        //         },
        //         function(errResponse) {
        //             console.error('Error while search books');
        //             deferred.reject(errResponse);
        //         }
        //     );
        return deferred.promise;
    }

    /* 검색 히스토리 리스트 */
    function searchHistoryList() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + '/history')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while search hitory list');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    /* Book mark list */
    function searchBookMarkList(sortType) {
        var deferred = $q.defer();
        var data = {
            lastId:1,
            size:10,
            sortType:sortType
        };

        var config = {
            params: data
        }
        $http.get(REST_SERVICE_URI + '/bookmarks', config)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while search hitory');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    /* Login */
    function login(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + '/signin', user)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while search hitory');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
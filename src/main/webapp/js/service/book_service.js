'use strict';

angular.module('BookSearch').factory('BookService', ['$http', '$q', function($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8080';

    var factory = {
        postBookMark: postBookMark,
        searchKakaoBooks: searchKakaoBooks,
        getHistory: getHistory,
        getBookMarks: getBookMarks,
        deleteBookMark: deleteBookMark,
        signin: signin,
    };

    return factory;

    /* Login */
    function signin(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + '/signin', user)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    // console.error('Error while search hitory');
                    // console.error(errResponse);
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    /* Book Mark 등록 */
    function postBookMark(bookMark) {
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

    function getBookMarks(sortType, page) {
        var deferred = $q.defer();

        var data = {
            lastId:page,
            size:10,
            sortType:sortType
        };

        var config = {
            params: data
        };
        // $http.get(REST_SERVICE_URI + '/bookmarks', config)
        // $http.get(REST_SERVICE_URI + '/bookmarks/sort', config)

        console.log('Config Data : ' + config);

        // $http.get(REST_SERVICE_URI + '/bookmarks', config)
        $http.get(REST_SERVICE_URI + '/bookmarks', {params: data})
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

    function deleteBookMark(bookMark) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + '/bookmarks' + '/' + bookMark)
        // $http.delete(REST_SERVICE_URI + '/bookmarks', bookMark)
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
    function searchKakaoBooks(title, page) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + '/search/books/' + title + '/' + page)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while search books');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getHistory() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + '/history')
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    console.error('Error while search hitory list');
                    alert('검색어가 없습니다');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);
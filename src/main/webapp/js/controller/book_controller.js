'use strict';

angular.module('BookApp').controller('BookController', ['$scope', '$location', '$window', 'BookService', function($scope, $location, $window, BookService) {
    var self = this;
    self.books = [];
    self.title = "";
    self.searchBooks = searchBooks;
    self.createBookMark = createBookMark;
    self.searchHistory = searchHistory;
    self.searchBookMarkList = searchBookMarkList;
    self.deleteBookMark = deleteBookMark;
    self.submitForm = submitForm;
    self.login = login;

    self.isSearchView = true;

    self.sorts = [
        {type : "Title", index : 0},
        {type : "Date", index : 1}
    ]

    function createBookMark(book) {
        console.log(book);
        var bookData = JSON.stringify(book);
        BookService.createBookMark(bookData)
            .then(
                function(d) {
                    console.log(d);
                },
                function(errResponse) {
                    console.error('Error while createBookMark');
                }
            );
    }

    function searchBooks(title) {
        // if (title == undefined || title == '') {
        //     alert('검색어를 입력하세요.');
        //     return;
        // }
        BookService.searchBooks(title)
            .then(
                function(d) {
                    self.isSearchView = true;
                    self.books = d.documents;
                    console.log(self.books);
                },
                function(errResponse) {
                    console.error('Error while search books');
                }
            );
    }
    
    function searchHistory() {
        BookService.searchHistoryList()
            .then(
                function (res) {
                    alert(res.historys);
                },
                function(errResponse) {
                    console.error('Error while search history');
                }
            );
    }

    function login(user) {
        var userData = JSON.stringify(user);

        BookService.login(userData)
            .then(
                function (res) {
                    $window.location.href = '/search';

                },
                function(errResponse) {
                    console.error(errResponse);
                    // console.error('Error while search history');
                }
            );
    }

    function searchBookMarkList(sortType) {
        BookService.searchBookMarkList(sortType)
            .then(
                function (res) {
                    self.isSearchView = false;
                    self.books = res.bookmarks;
                },
                function(errResponse) {
                    console.error('Error while search bookmark list');
                }
            );
    }

    function deleteBookMark() {
        BookService.deleteBookMark()
            .then(
                function (res) {
                    searchBookMarkList();
                },
                function(errResponse) {
                    console.error('Error while search bookmark list');
                }
            );
    }

    function submitForm() {
        alert();
    }

}]);
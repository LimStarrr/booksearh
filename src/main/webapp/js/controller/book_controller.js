'use strict';

angular.module('BookApp').controller('BookController', ['$scope', '$location', '$window', 'BookService', function($scope, $location, $window, BookService) {
    var self = this;
    self.books = [];
    self.searchbooks = [];
    self.historyList = [];
    self.title = "";
    self.searchBooks = searchBooks;
    self.createBookMark = createBookMark;
    self.searchHistory = searchHistory;
    self.searchBookMarkList = searchBookMarkList;
    self.deleteBookMark = deleteBookMark;
    self.submitForm = submitForm;
    self.login = login;

    self.isSearchView = true;

    self.isHistoryView = false;
    self.page = 0;

    self.sorts = [
        {type : "Title", index : "0"},
        {type : "Date", index : "1"}
    ];
    self.selectedSort = "0";

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
                    self.isHistoryView = false;
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
                    self.isHistoryView = true;
                    self.historyList = res.historys;
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

    function searchBookMarkList(sortType, page) {
        if( page === 0)
            self.page = 0;

        if(self.page === -1)
            return;

        BookService.searchBookMarkList(sortType, self.page)
            .then(
                function (res) {
                    self.isSearchView = false;
                    self.isHistoryView = false;
                    for(var i = 0 ; i < res.bookmarks.content.length; i++ ) {
                        self.searchbooks.push(res.bookmarks.content[i]);
                    }

                    if(res.bookmarks.totalPages === self.page + 1)
                        self.page = -1;
                    else
                        self.page++;
                },
                function(errResponse) {
                    console.error('Error while search bookmark list');
                }
            );
    }

    function deleteBookMark(id) {
        BookService.deleteBookMark(id)
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

    // angular.module('scroll', []).directive('whenScrolled', function() {
    //     return function(scope, elm, attr) {
    //         var raw = elm[0];
    //
    //         elm.bind('scroll', function() {
    //             if (raw.scrollTop + raw.offsetHeight >= raw.scrollHeight) {
    //                 scope.$apply(attr.whenScrolled);
    //             }
    //         });
    //     };
    // });

}]);
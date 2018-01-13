'use strict';

angular.module('BookApp').controller('BookController', ['$scope', '$location', '$window', 'BookService', function($scope, $location, $window, BookService) {
    var self = this;
    self.books = [];
    self.searchbooks = [];
    self.historyList = [];
    self.title = "";
    self.searchKakaoBooks = searchKakaoBooks;
    self.postBookMark = postBookMark;
    self.getHistory = getHistory;
    self.getBookMarks = getBookMarks;
    self.deleteBookMark = deleteBookMark;
    self.submitForm = submitForm;
    self.signin = signin;

    self.isSearchView = true;

    self.isHistoryView = false;
    self.page = 0;

    self.barcode = -1;

    self.sorts = [
        {type : "Title", index : "0"},
        {type : "Date", index : "1"},
        {type : "Price", index : "2"}

    ];
    self.selectedSort = "0";

    function signin(user) {
        var userData = JSON.stringify(user);

        BookService.signin(userData)
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

    function postBookMark(book) {
        console.log(book);
        var bookData = JSON.stringify(book);
        BookService.postBookMark(bookData)
            .then(
                function(d) {
                    console.log(d);
                },
                function(errResponse) {
                    console.error('Error while createBookMark');
                }
            );
    }

    function getBookMarks(sortType, page) {
        self.isHistoryView = false;
        self.isSearchView = false;

        if( page === 0) {
            self.page = 0;
            self.searchbooks = [];
        }
        if(self.page === -1)
            return;

        if( page === -1)
            return;

        BookService.getBookMarks(sortType, self.page)
            .then(
                function (res) {
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

    function deleteBookMark(barcode) {
        self.barcode = barcode;
        BookService.deleteBookMark(barcode)
            .then(
                function (res) {
                    self.searchbooks = self.searchbooks.filter(function (book) {
                        return book.barcode !== self.barcode;
                    });
                },
                function(errResponse) {
                    console.error('Error while search bookmark list');
                }
            );
    }

    function searchKakaoBooks(title) {
        // if (title == undefined || title == '') {
        //     alert('검색어를 입력하세요.');
        //     return;
        // }
        BookService.searchKakaoBooks(title)
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
    
    function getHistory() {
        self.historyList = [];
        BookService.getHistory()
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
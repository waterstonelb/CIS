$(document).ready(function () {
    var movieList;
    getMovies();
    getTopBox();
    getVIPinfo();
    getMostFavourite();

    function getVIPinfo() {
        getRequest(
            '/vip/' + sessionStorage.getItem('id') + '/get',
            function (res) {
                if (res.success) {
                    // 是会员
                    sessionStorage.setItem('isVIP', '1');
                    sessionStorage.setItem('cardId', res.content.cardId);
                    sessionStorage.setItem('vipId', res.content.id);
                    sessionStorage.setItem('joinDate',res.content.joinDate);
                    sessionStorage.setItem('balance',res.content.balance);
                    getUserVIPCard(res.content.cardId);
                } else {
                    // 非会员
                    sessionStorage.setItem('isVIP', '0');
                    sessionStorage.setItem('discount','1');
                }
            },
            function (error) {
                alert(error);
            });

        function getUserVIPCard(cardId) {
            getRequest(
                '/vipactivity/getbyid?cardId=' + cardId,
                function (res) {
                    if (res.success) {
                        sessionStorage.setItem('targetAmount', res.content.targetAmount);
                        sessionStorage.setItem('discountAmount',res.content.discountAmount);
                        sessionStorage.setItem('discount',res.content.discount);
                        sessionStorage.setItem('cardName',res.content.cardName);
                    }
                }
            )
        }
    }

    function getMovies() {
        getRequest(
            "/movie/all/exclude/off",
            function (res) {
                movieList = res.content;
                renderMovie(movieList)
            },
            function () {

            }
        )
    }

    function getTopBox() {
        getRequest(
            "/statistics/boxOffice/total",
            function (res) {
                topList = res.content;
                renderTopBox(topList)
            }
        )
    }

    function getMostFavourite() {
        getRequest(
            "/statistics/userlike",
            function (res) {
                favouriteList = res.content;
                renderMostFavourite(favouriteList)
            }
        )
    }

    function renderMovie(movieList) {
        $("#movie-show").empty();
        $("#movie-choose").empty();
        var movieDomStr='';
        var movieChoose='';
        var count=0;
        movieList.forEach(function (movie) {
            movieDomStr+="<div class='item "+(count==0?"active":"")+"' >" +
                "                        <img src='"+movie.posterUrl+"' alt='成功' class='img-show'>" +
                "                        <div class='carousel-caption'>" +movie.name+"</div>" +
                "                    </div>";
            movieChoose+="<li data-target='#carousel-example-generic' data-slide-to='"+count+"'></li>"
            count=count+1;
        });
        $("#movie-show").append(movieDomStr);
        $("#movie-choose").append(movieChoose);
        /**$('.movie-on-list').empty();
        var movieDomStr = '';
        movieList.forEach(function (movie) {
            movie.description = movie.description || '';
            movieDomStr +=
                "<li class='movie-item card'>" +
                "<img class='movie-img' src='" + (movie.posterUrl || "../images/defaultAvatar.jpg") + "' id='" + movie.id + "'/>" +
                "<div>" + movie.name + "</div>" +
                "</li>";
        });
        $('.movie-on-list').append(movieDomStr);*/
    }

    function renderTopBox(topList) {
        $('.top-sell-list').empty();
        var movieDomStr = '';
        for (i = 0; i < topList.length && 10; i++) {
            movieDomStr +=
                "<div class='statistic-item' id='" + topList[i].movieId + "'>" +
                "<span>" + topList[i].name + "</span>" +
                "<span>" + (topList[i].boxOffice || 0) + "</span>" +
                "</div>";
        }
        $('.top-sell-list').append(movieDomStr);
    }

    function renderMostFavourite(favouriteList) {
        $('.top-expectation-list').empty();
        var movieDomStr = '';
        for (i = 0; i < favouriteList.length && 10; i++) {
            movieDomStr +=
                "<div class='statistic-item' id='" + favouriteList[i].movieid + "'>" +
                "<span>" + favouriteList[i].moviename + "</span>" +
                "<span>" + (favouriteList[i].likeNum || 0) + "</span>" +
                "</div>";
        }
        $('.top-expectation-list').append(movieDomStr);
    }

    $(document).on('click', '.movie-img', function () {
        $(location).attr('href', '/user/movieDetail?id=' + $(this).attr("id"));
    });
    $(document).on('click', '.statistic-item', function () {
        $(location).attr('href', '/user/movieDetail?id=' + $(this).attr("id"));
    });

});
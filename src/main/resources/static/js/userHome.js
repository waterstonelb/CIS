$(document).ready(function() {
    var movieList;
    getMovies();
    getTopBox();
    getMostFavourite();
    function getMovies() {
        getRequest(
            "/movie/all/exclude/off",
            function (res) {
                movieList=res.content;
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
                topList=res.content;
                renderTopBox(topList)
            }
        )
    }
    function getMostFavourite(){
        getRequest(
            "/statistics/userlike",
            function (res) {
                favouriteList=res.content;
                renderMostFavourite(favouriteList)
            }
        )
    }
    function renderMovie(movieList) {
        $('.movie-on-list').empty();
        var movieDomStr = '';
        movieList.forEach(function (movie) {
            movie.description = movie.description || '';
            movieDomStr +=
                "<li class='movie-item card'>" +
                "<img class='movie-img' src='" + (movie.posterUrl || "../images/defaultAvatar.jpg") +"' id='"+movie.id + "'/>" +
                "<div>"+movie.name+"</div>"+
                "</li>";
        });
        $('.movie-on-list').append(movieDomStr);
    }
    function renderTopBox(topList) {
        $('.top-sell-list').empty();
        var movieDomStr = '';
        for(i=0;i<topList.length&&10;i++){
            movieDomStr +=
                "<div class='statistic-item' id='"+topList[i].movieId+"'>" +
                "<span>"+topList[i].name+"</span>"+
                "<span>"+(topList[i].boxOffice || 0)+"</span>"+
                "</div>";
        }
        $('.top-sell-list').append(movieDomStr);
    }
    function renderMostFavourite(favouriteList) {
        $('.top-expectation-list').empty();
        var movieDomStr = '';
        for(i=0;i<favouriteList.length&&10;i++){
            movieDomStr +=
            "<div class='statistic-item' id='"+favouriteList[i].movieid+"'>" +
                "<span>"+favouriteList[i].moviename+"</span>"+
                "<span>"+(favouriteList[i].likeNum || 0)+"</span>"+
                "</div>";
        }
        $('.top-expectation-list').append(movieDomStr);
    }

    $(document).on('click','.movie-img',function(){
        $(location).attr('href', '/user/movieDetail?id='+$(this).attr("id"));
    });
    $(document).on('click','.statistic-item',function(){
        $(location).attr('href', '/user/movieDetail?id='+$(this).attr("id"));
    });
    
});
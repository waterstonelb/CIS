package com.example.cinema.blImpl.statistics;

import java.util.ArrayList;
import java.util.List;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.blImpl.management.schedule.MovieServiceForBl;
import com.example.cinema.blImpl.user.AccountServiceForBl;
import com.example.cinema.data.statistics.MovieLikeMapper;
import com.example.cinema.po.DateLike;
import com.example.cinema.po.MovieLikePO;
import com.example.cinema.vo.DateLikeVO;
import com.example.cinema.vo.MovieLikeVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yzh
 * @date 2019/6/18 23:39 PM
 * 实现MovieLikeService接口
 */
@Service
public class MovieLikeServiceImpl implements MovieLikeService {
    private static final String ALREADY_LIKE_ERROR_MESSAGE = "用户已标记该电影为想看";
    private static final String UNLIKE_ERROR_MESSAGE = "用户未标记该电影为想看";
    private static final String MOVIE_NOT_EXIST_ERROR_MESSAGE = "电影不存在";

    @Autowired
    private MovieLikeMapper movieLikeMapper;
    @Autowired
    private MovieServiceForBl movieServiceForBl;
    @Autowired
    private AccountServiceForBl accountServiceForBl;

    @Override
    public ResponseVO likeMovie(int userId, int movieId) {

        //todo: user 判空
        if (userLikeTheMovie(userId, movieId)) {
            return ResponseVO.buildFailure(ALREADY_LIKE_ERROR_MESSAGE);
        } else if (movieServiceForBl.getMovieById(movieId) == null) {
            return ResponseVO.buildFailure(MOVIE_NOT_EXIST_ERROR_MESSAGE);
        } else if ( accountServiceForBl.getAccountById(userId)== null) {
            return ResponseVO.buildFailure(MOVIE_NOT_EXIST_ERROR_MESSAGE);
        }
        try {
            return ResponseVO.buildSuccess(movieLikeMapper.insertOneLike(movieId, userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO unLikeMovie(int userId, int movieId) {
        if (!userLikeTheMovie(userId, movieId)) {
            return ResponseVO.buildFailure(UNLIKE_ERROR_MESSAGE);
        } else if (movieServiceForBl.getMovieById(movieId) == null) {
            return ResponseVO.buildFailure(MOVIE_NOT_EXIST_ERROR_MESSAGE);
        }
        try {
            return ResponseVO.buildSuccess(movieLikeMapper.deleteOneLike(movieId, userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO getCountOfLikes(int movieId) {
        try {
            return ResponseVO.buildSuccess(movieLikeMapper.selectLikeNums(movieId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getLikeNumsGroupByDate(int movieId) {
        try {
            return ResponseVO.buildSuccess(dateLikeList2DateLikeVOList(movieLikeMapper.getDateLikeNum(movieId)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private boolean userLikeTheMovie(int userId, int movieId) {
        return movieLikeMapper.selectLikeMovie(movieId, userId) != 0;
    }


    private List<DateLikeVO> dateLikeList2DateLikeVOList(List<DateLike> dateLikeList) {
        List<DateLikeVO> dateLikeVOList = new ArrayList<>();
        for (DateLike dateLike : dateLikeList) {
            dateLikeVOList.add(new DateLikeVO(dateLike));
        }
        return dateLikeVOList;
    }

	@Override
	public ResponseVO getlikemovielist() {
        try {
            return ResponseVO.buildSuccess(MovieLikeList2DateLikeVOList(movieLikeMapper.getMovieLikeNum()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
	}
	/*
	 * 这是一个获取movielistpo的桩
	@Override
	public ResponseVO getlikemovielist() {
        	List<MovieLikePO> movielikepolist = new ArrayList<>();
        	MovieLikePO movielikepo1 = new MovieLikePO();
        	movielikepo1.setLikeNum(10);
        	movielikepo1.setmovieid(10);
        	movielikepo1.setmoviename("black");
        	movielikepolist.add(movielikepo1);
            return ResponseVO.buildSuccess(MovieLikeList2DateLikeVOList(movielikepolist));
        }
	*/
	
    private List<MovieLikeVO> MovieLikeList2DateLikeVOList(List<MovieLikePO> MovieLike) {
        List<MovieLikeVO> MovieLikeVOList = new ArrayList<>();
        for (MovieLikePO movielike  : MovieLike) {
            MovieLikeVOList.add(new MovieLikeVO(movielike));
        }
        return MovieLikeVOList;
    }
}

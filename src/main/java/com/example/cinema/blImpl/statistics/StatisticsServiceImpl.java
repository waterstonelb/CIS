package com.example.cinema.blImpl.statistics;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.data.statistics.StatisticsMapper;
import com.example.cinema.po.AudiencePrice;
import com.example.cinema.po.MovieScheduleTime;
import com.example.cinema.po.MovieTotalBoxOffice;
import com.example.cinema.po.PlacingRate;
import com.example.cinema.po.PopularMovies;
import com.example.cinema.po.UserBuyRecord;
import com.example.cinema.po.UserChargeRecord;
import com.example.cinema.vo.AudiencePriceVO;
import com.example.cinema.vo.MovieScheduleTimeVO;
import com.example.cinema.vo.MovieTotalBoxOfficeVO;
import com.example.cinema.vo.PlacingRateVO;
import com.example.cinema.vo.PopularMoviesVO;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserBuyRecordVO;
import com.example.cinema.vo.UserChargeRecordVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Override
    public ResponseVO getScheduleRateByDate(Date date) {
        try{
            Date requireDate = date;
            if(requireDate == null){
                requireDate = new Date();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));

            Date nextDate = getNumDayAfterDate(requireDate, 1);
            return ResponseVO.buildSuccess(movieScheduleTimeList2MovieScheduleTimeVOList(statisticsMapper.selectMovieScheduleTimes(requireDate, nextDate)));
            //selectmoviescheduletime 返回所有电影票房
            //然后那个长不辣叽得转vo
            //最后返回vo 得到id 时间 片名
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTotalBoxOffice() {
        try {
            return ResponseVO.buildSuccess(movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(statisticsMapper.selectMovieTotalBoxOffice()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAudiencePriceSevenDays() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date startDate = getNumDayAfterDate(today, -6);
            List<AudiencePriceVO> audiencePriceVOList = new ArrayList<>();
            for(int i = 0; i < 7; i++){
                AudiencePriceVO audiencePriceVO = new AudiencePriceVO();
                Date date = getNumDayAfterDate(startDate, i);
                audiencePriceVO.setDate(date);
                List<AudiencePrice> audiencePriceList = statisticsMapper.selectAudiencePrice(date, getNumDayAfterDate(date, 1));
                double totalPrice = audiencePriceList.stream().mapToDouble(item -> item.getTotalPrice()).sum();
                audiencePriceVO.setPrice(Double.parseDouble(String.format("%.2f", audiencePriceList.size() == 0 ? 0 : totalPrice / audiencePriceList.size())));
                audiencePriceVOList.add(audiencePriceVO);
            }
            return ResponseVO.buildSuccess(audiencePriceVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getMoviePlacingRateByDate(String date) {
        try {
            Date requireDate = null;
            if(date == ""){
                requireDate = new Date();
            }
            else
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                requireDate = simpleDateFormat.parse(date);
            }
            Date nextDate = getNumDayAfterDate(requireDate, 1);
            return ResponseVO.buildSuccess(PlacingRateList2MovieTotalBoxOfficeVOList(statisticsMapper.selectPlacingRate(requireDate,nextDate)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getPopularMovies(int days, int movieNum) {
    	try {
            Date requireDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));
            Date nextDate = getNumDayAfterDate(requireDate, -1*days);
            List<PopularMovies> datas = statisticsMapper.selectPopularMovies(requireDate,nextDate,movieNum);
    		return ResponseVO.buildSuccess(PopularMoviesList2MovieTotalBoxOfficeVOList(datas));
    	}catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
        //要求见接口说明
    }


    /**
     * 获得num天后的日期
     * @param oldDate
     * @param num
     * @return
     */
    Date getNumDayAfterDate(Date oldDate, int num){
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }



    private List<MovieScheduleTimeVO> movieScheduleTimeList2MovieScheduleTimeVOList(List<MovieScheduleTime> movieScheduleTimeList){
        List<MovieScheduleTimeVO> movieScheduleTimeVOList = new ArrayList<>();
        for(MovieScheduleTime movieScheduleTime : movieScheduleTimeList){
            movieScheduleTimeVOList.add(new MovieScheduleTimeVO(movieScheduleTime));
        }
        return movieScheduleTimeVOList;
    }


    private List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(List<MovieTotalBoxOffice> movieTotalBoxOfficeList){
        List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeVOList = new ArrayList<>();
        for(MovieTotalBoxOffice movieTotalBoxOffice : movieTotalBoxOfficeList){
            movieTotalBoxOfficeVOList.add(new MovieTotalBoxOfficeVO(movieTotalBoxOffice));
        }
        return movieTotalBoxOfficeVOList;
    }
    
    private List<PopularMoviesVO> PopularMoviesList2MovieTotalBoxOfficeVOList(List<PopularMovies> datas){
        List<PopularMoviesVO> PopularMoviesVOList = new ArrayList<>();
        for(PopularMovies popularMovies : datas){
        	PopularMoviesVOList.add(new PopularMoviesVO(popularMovies));
        }
        return PopularMoviesVOList;
    }
    
    private List<PlacingRateVO> PlacingRateList2MovieTotalBoxOfficeVOList(List<PlacingRate> datas){
        List<PlacingRateVO> PlacingRateVOList = new ArrayList<>();
        for(PlacingRate placingrate : datas){
        	PlacingRateVOList.add(new PlacingRateVO(placingrate));
        }
        return PlacingRateVOList;
    }
    
    
    
	public ResponseVO getUserBuyRecord() {
        try {
            return ResponseVO.buildSuccess(buyRecordList2buyRecordVOList(statisticsMapper.SelectBuyRecord()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
	}

	private List<UserBuyRecordVO> buyRecordList2buyRecordVOList(List<UserBuyRecord> userBuyRecord) {
        List<UserBuyRecordVO> UserBuyRecordVOList = new ArrayList<>();
        for(UserBuyRecord userbuyRecord : userBuyRecord){
        	UserBuyRecordVOList.add(new UserBuyRecordVO(userbuyRecord));
        }
        return UserBuyRecordVOList;
	}

	@Override
	public ResponseVO getUserChargeRecord() {
        try {
            return ResponseVO.buildSuccess(chargeRecordList2buyRecordVOList(statisticsMapper.SelectChargeRecord()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
	}

	private List<UserChargeRecordVO> chargeRecordList2buyRecordVOList(List<UserChargeRecord> userChargeRecord) {
        List<UserChargeRecordVO> UserChargeRecordVOList = new ArrayList<>();
        for(UserChargeRecord userchargeRecord : userChargeRecord){
        	UserChargeRecordVOList.add(new UserChargeRecordVO(userchargeRecord));
        }
        return UserChargeRecordVOList;
	}
}

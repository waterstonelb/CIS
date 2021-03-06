package com.example.cinema.blImpl.statistics;

import com.example.cinema.blImpl.promotion.vipservice.VIPServiceForBl;
import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.data.statistics.StatisticsMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.AudiencePriceVO;
import com.example.cinema.vo.MovieScheduleTimeVO;
import com.example.cinema.vo.MovieTotalBoxOfficeVO;
import com.example.cinema.vo.PlacingRateVO;
import com.example.cinema.vo.PopularMoviesVO;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.amountVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yzh
 * @date 2019/6/18 23:46 PM
 * 实现StatisticService接口
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Autowired
    VIPServiceForBl vipServiceForBl;

    @Override
    public ResponseVO getScheduleRateByDate(Date date) {
        try {
            Date requireDate = date;
            if (requireDate == null) {
                requireDate = new Date();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));

            Date nextDate = getNumDayAfterDate(requireDate, 1);
            return ResponseVO.buildSuccess(movieScheduleTimeList2MovieScheduleTimeVOList(statisticsMapper.selectMovieScheduleTimes(requireDate, nextDate)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    
    
    @Override
    public ResponseVO getTotalBoxOffice() {
        try {
            return ResponseVO.buildSuccess(movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(statisticsMapper.selectMovieTotalBoxOffice()));
        } catch (Exception e) {
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
            for (int i = 0; i < 7; i++) {
                AudiencePriceVO audiencePriceVO = new AudiencePriceVO();
                Date date = getNumDayAfterDate(startDate, i);
                audiencePriceVO.setDate(date);
                List<AudiencePrice> audiencePriceList = statisticsMapper.selectAudiencePrice(date, getNumDayAfterDate(date, 1));
                double totalPrice = audiencePriceList.stream().mapToDouble(item -> item.getTotalPrice()).sum();
                audiencePriceVO.setPrice(Double.parseDouble(String.format("%.2f", audiencePriceList.size() == 0 ? 0 : totalPrice / audiencePriceList.size())));
                audiencePriceVOList.add(audiencePriceVO);
            }
            return ResponseVO.buildSuccess(audiencePriceVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

	@Override
    public ResponseVO getMoviePlacingRateByDate(String date) {
        try {
            Date requireDate = null;
            if (date == "") {
                Date requireDate1 = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = simpleDateFormat.format(requireDate1);
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                requireDate = simpleDateFormat2.parse(date1);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                requireDate = simpleDateFormat.parse(date);
            }
            Date nextDate = getNumDayAfterDate(requireDate, 1);
            return ResponseVO.buildSuccess(PlacingRateList2MovieTotalBoxOfficeVOList(statisticsMapper.selectPlacingRate(requireDate, nextDate)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAmountHistory(String startDate, String endDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startdata = simpleDateFormat.parse(startDate);
            Date enddate = simpleDateFormat.parse(endDate);
            return ResponseVO.buildSuccess(amountPO2VO(statisticsMapper.AdminSelectBuyRecord(startdata, enddate)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("查询历史消费总额失败排名");
        }
    }
    
    /*
     * 桩
     * 这是获取adminuserrecordlist的桩
    @Override
    public ResponseVO getAmountHistory(String startDate, String endDate) {
        	List<AdminUserBuyRecord> adminuserbuyrecord = new ArrayList<>();
            AdminUserBuyRecord adminuserbuyrecord1 = new AdminUserBuyRecord();
            adminuserbuyrecord1.setRealPrice(100);
            adminuserbuyrecord1.setUserId(10);
            adminuserbuyrecord.add(adminuserbuyrecord1);
            return ResponseVO.buildSuccess(amountPO2VO(adminuserbuyrecord));

    }
    */
    

    private List<amountVO> amountPO2VO(List<AdminUserBuyRecord> list) {
        try {
            List<amountVO> amountVOS = new ArrayList<>();
            for (AdminUserBuyRecord adminUserBuyRecord : list) {
                amountVO amountvo = new amountVO();
                amountvo.setUserId(adminUserBuyRecord.getUserId());
                amountvo.setRealPrice(adminUserBuyRecord.getRealPrice());
                amountvo.setCardId(vipServiceForBl.getCardId(adminUserBuyRecord.getUserId()));
                amountVOS.add(amountvo);
            }
            return amountVOS;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseVO getPopularMovies(int days, int movieNum) {
        try {
            Date requireDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));
            Date nextDate = getNumDayAfterDate(requireDate, -1 * days);
            List<PopularMovies> datas = statisticsMapper.selectPopularMovies(requireDate, nextDate, movieNum);
            return ResponseVO.buildSuccess(PopularMoviesList2MovieTotalBoxOfficeVOList(datas));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
        //要求见接口说明
    }


    /**
     * 获得num天后的日期
     *
     * @param oldDate
     * @param num
     * @return
     */
    Date getNumDayAfterDate(Date oldDate, int num) {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }


    private List<MovieScheduleTimeVO> movieScheduleTimeList2MovieScheduleTimeVOList(List<MovieScheduleTime> movieScheduleTimeList) {
        List<MovieScheduleTimeVO> movieScheduleTimeVOList = new ArrayList<>();
        for (MovieScheduleTime movieScheduleTime : movieScheduleTimeList) {
            movieScheduleTimeVOList.add(new MovieScheduleTimeVO(movieScheduleTime));
        }
        return movieScheduleTimeVOList;
    }


    private List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(List<MovieTotalBoxOffice> movieTotalBoxOfficeList) {
        List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeVOList = new ArrayList<>();
        for (MovieTotalBoxOffice movieTotalBoxOffice : movieTotalBoxOfficeList) {
            movieTotalBoxOfficeVOList.add(new MovieTotalBoxOfficeVO(movieTotalBoxOffice));
        }
        return movieTotalBoxOfficeVOList;
    }

    private List<PopularMoviesVO> PopularMoviesList2MovieTotalBoxOfficeVOList(List<PopularMovies> datas) {
        List<PopularMoviesVO> PopularMoviesVOList = new ArrayList<>();
        for (PopularMovies popularMovies : datas) {
            PopularMoviesVOList.add(new PopularMoviesVO(popularMovies));
        }
        return PopularMoviesVOList;
    }

    private List<PlacingRateVO> PlacingRateList2MovieTotalBoxOfficeVOList(List<PlacingRate> datas) {
        List<PlacingRateVO> PlacingRateVOList = new ArrayList<>();
        for (PlacingRate placingrate : datas) {
            PlacingRateVOList.add(new PlacingRateVO(placingrate));
        }
        return PlacingRateVOList;
    }
}

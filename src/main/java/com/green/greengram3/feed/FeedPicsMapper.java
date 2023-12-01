package com.green.greengram3.feed;

import com.green.greengram3.feed.model.FeedInsProcDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    int insFeedPics(FeedInsProcDto pDto);

    List<String> selFeedPicsAll(int ifeed);  //컬럼이 2개이상 부터는 list말고 객체사용

}

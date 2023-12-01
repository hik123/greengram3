package com.green.greengram3.feed;


import com.green.greengram3.common.Const;
import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.FeedFavDto;
import com.green.greengram3.feed.model.FeedInsDto;
import com.green.greengram3.feed.model.FeedSelDto;
import com.green.greengram3.feed.model.FeedSelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
@Tag(name = "피드 API", description = "피드 관련 처리") // description 에서 <br> >> 개행
public class FeedController {
    private final FeedService service;

    @Operation(summary = "피드 등록", description = "피드 등록 처리")
    @PostMapping
    public ResVo postFeed(@RequestBody FeedInsDto dto) {
        return service.postFeed(dto);
    }

    @GetMapping
    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
        /*FeedSelDto dto = FeedSelDto.builder()    // builder패턴 쓰면 객체화 불가능
                .rowCount(Const.FEED_COUNT_PER_PAGE)
                .startIdx((page-1) * Const.FEED_COUNT_PER_PAGE)
                .build();
        return service.getFeedAll(dto); */

        return service.getFeedAll(dto);
    }

    @GetMapping("/fav")
    public ResVo toggleFeedFav(FeedFavDto dto) {
        return service.toggleFeedFav(dto);
    }
}

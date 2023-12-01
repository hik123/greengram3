package com.green.greengram3.feed;


import com.green.greengram3.common.Const;
import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;
    private final FeedFavMapper favMapper;
    private final FeedCommentMapper commentMapper;

    public ResVo postFeed(FeedInsDto dto) {
        FeedInsProcDto pDto = FeedInsProcDto.builder()
                .iuser(dto.getIuser())
                .contents(dto.getContents())
                .location(dto.getLocation())
                .pics(dto.getPics())
                .build();

        int feedAffectedRow = mapper.insFeed(pDto);
        if(feedAffectedRow == 0 || pDto.getIfeed() < 1) {
            return new ResVo(0);  //피드 생성실패 result:0
        }
        int picsAffectedRow = picsMapper.insFeedPics(pDto);
        if(picsAffectedRow != pDto.getPics().size()) {
            return new ResVo(3); //사진 추가실패 result:3
        }
        return new ResVo(pDto.getIfeed());
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {                     // n+1문제 / for문에서 feed가 4개일때 for문 5번반복?
        List<FeedSelVo> list = mapper.selFeedAll(dto); // list에 feed 싹다 담음

        FeedCommentSelDto fcDto = new FeedCommentSelDto();
        fcDto.setStartIdx(0);
        fcDto.setRowCount(Const.FEED_COMMENT_FIRST_CNT);

        for(FeedSelVo vo : list) {      // 추가로 사진도 싹다 담음
            List<String> pics = picsMapper.selFeedPicsAll(vo.getIfeed());
            vo.setPics(pics);           // setter메소드로 FeedSelVo의 pics에 담아줌

            fcDto.setIfeed(vo.getIfeed());              // FeedCommentSelDto에 ifeed값 담고
            List<FeedCommentSelVo> comments = commentMapper.selFeedCommentAll(fcDto);  //comment 전부 comments에 담고

            if(comments.size() == Const.FEED_COMMENT_FIRST_CNT) {      // 댓글4일때 3개까지보이게
                vo.setIsMoreComment(1);             //IsMoreComment 0: 댓글이 더 없음, 1: 댓글이 더있음
                comments.remove(comments.size() - 1);
            }
            vo.setComments(comments);
        }
        return list;
    }

    // ----------- t_feed_fav
    public ResVo toggleFeedFav(FeedFavDto dto) {
        //ResVo - result값은 삭제했을 시(좋아요 취소) 0리턴,
        // 등록했을 시 1리턴.
        int delAffectedRow = favMapper.delFeedFav(dto);
        if(delAffectedRow == 1) {
            return new ResVo(Const.FEED_FAV_DEL);
        }
        int insAffectedRow = favMapper.insFeedFav(dto);
        return new ResVo(Const.FEED_FAV_ADD);
    };



}

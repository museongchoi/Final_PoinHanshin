package com.project.poinhanshin.domain.etc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword = "";
    private String option = "";
    // 동물필터 0 - 강아지 , 1 - 고양이, 2 - 모두
    private Integer ani_category = 2;
    // 사용자 타입 0 - 신고자 , 1 - 발견자 , 2 - 모두
    private Integer writerType = 2;
    private String missingAddress ="";
    private String kind="";
    // 지역
    // private String region = "";

    public SearchCondition(){}

    public SearchCondition(Integer page, Integer pageSize, String keyword, String option, Integer ani_category, Integer writerType, String missingAddress, String kind) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
        this.ani_category = ani_category;
        this.writerType = writerType;
        this.missingAddress = missingAddress;
        this.kind = kind;
    }

    public String getQueryString(){
        return getQueryString(page);
    }
    public String getQueryString(Integer page){
        return UriComponentsBuilder.newInstance()
                .queryParam("page" , page)
                .queryParam("pageSize" , pageSize)
                .queryParam("keyword" , keyword)
                .queryParam("option" , option)
                .queryParam("ani_category" , ani_category)
                .queryParam("writerType" , writerType)
                .queryParam("missingAddress" , missingAddress)
                .queryParam("kind" , kind)
                .build().toString();
    }
    public int getOffset() {
        return (page - 1) * pageSize;
    }

   /* public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                ", ani_category=" + ani_category +
                '}';
    }*/
}

package com.project.poinhanshin.domain.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Setter
@Getter
@ToString
public class Abandoned_animal {

    //유기번호
    private String desertionNo;
    // 썸네일 이미지
    private String filename;
    // 접수일자
    private String happenDt;
    // 발견장소
    private String happenPlace;
    // 품종
    private String kindCd;
    // 색상
    private String colorCd;
    // 나이
    private String age;
    // 무게
    private String weight;
    //공고번호
    private String noticeNo;
    // 공고 시작일
    private String noticeSdt;
    // 공고 종료일
    private String noticeEdt;
    // 이미지
    private String popfile;
    // 상태
    private String processState;
    // 성별
    private String sexCd;
    // 중성화 여부
    private String neuterYn;
    // 특징
    private String specialMark;
    // 보호소이름
    private String careNm;
    // 보호소 번호
    private String careTel;
    // 보호소 주소
    private String careAddr;
    // 관할기관
    private String orgNm;
    // 담당자 이름
    private String chargeNm;
    // 담당자 연락처
    private String officetel;
    private String numOfRows;
    private String pageNo;
    private String totalCount;
    public String getQueryString(){
        return UriComponentsBuilder.newInstance()
                .queryParam("desertionNo" , desertionNo)
                .queryParam("filename" , filename)
                .queryParam("happenDt" , happenDt)
                .queryParam("happenPlace",happenPlace)
                .queryParam("kindCd" , kindCd)
                .queryParam("colorCd" , colorCd)
                .queryParam("age" , age)
                .queryParam("weight" , weight)
                .queryParam("noticeNo" , noticeNo)
                .queryParam("noticeSdt" , noticeSdt)
                .queryParam("noticeEdt" , noticeEdt)
                .queryParam("popfile" , popfile)
                .queryParam("processState" , processState)
                .queryParam("sexCd" , sexCd)
                .queryParam("neuterYn" , neuterYn)
                .queryParam("specialMark" , specialMark)
                .queryParam("careNm" , careNm)
                .queryParam("careTel" , careTel)
                .queryParam("careAddr" , careAddr)
                .queryParam("orgNm" , orgNm)
                .queryParam("chargeNm" , chargeNm)
                .queryParam("officetel" , officetel)
                .queryParam("numOfRows" , numOfRows)
                .queryParam("pageNo" , pageNo)
                .queryParam("totalCount" , totalCount)
                .build().encode().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abandoned_animal that = (Abandoned_animal) o;
        return Objects.equals(desertionNo, that.desertionNo) && Objects.equals(filename, that.filename) && Objects.equals(happenDt, that.happenDt) && Objects.equals(happenPlace, that.happenPlace) && Objects.equals(kindCd, that.kindCd) && Objects.equals(colorCd, that.colorCd) && Objects.equals(age, that.age) && Objects.equals(weight, that.weight) && Objects.equals(noticeNo, that.noticeNo) && Objects.equals(noticeSdt, that.noticeSdt) && Objects.equals(noticeEdt, that.noticeEdt) && Objects.equals(popfile, that.popfile) && Objects.equals(processState, that.processState) && Objects.equals(sexCd, that.sexCd) && Objects.equals(neuterYn, that.neuterYn) && Objects.equals(specialMark, that.specialMark) && Objects.equals(careNm, that.careNm) && Objects.equals(careTel, that.careTel) && Objects.equals(careAddr, that.careAddr) && Objects.equals(orgNm, that.orgNm) && Objects.equals(chargeNm, that.chargeNm) && Objects.equals(officetel, that.officetel) && Objects.equals(numOfRows, that.numOfRows) && Objects.equals(pageNo, that.pageNo) && Objects.equals(totalCount, that.totalCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desertionNo, filename, happenDt, happenPlace, kindCd, colorCd, age, weight, noticeNo, noticeSdt, noticeEdt, popfile, processState, sexCd, neuterYn, specialMark, careNm, careTel, careAddr, orgNm, chargeNm, officetel, numOfRows, pageNo, totalCount);
    }
}

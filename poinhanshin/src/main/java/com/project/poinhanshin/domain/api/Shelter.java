package com.project.poinhanshin.domain.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Getter
@Setter
@ToString
public class Shelter {

    /*
        // 요청번호
        private String reqNo;
        // 결과코드
        private String resultCode;
        // 결과 메시지
        private String resultMsg;*/
    // 동물보호센터명
    private String careNm;
    // 관리기관명
    private String orgNm;
    //동물보호센터 유형
    private String divisionNm;
    // 구조대상동물
    private String saveTrgtAnimal;
    //소재지도로명주소
    private String careAddr;

    //소재지지번주소
    private String jibunAddr;
    // 위도
    private String lat;
    // 경도
    private String lng;
    //동물보호센터지정일자
    private String dsignationDate;
    //평일운영시작시각
    private String weekOprStime;
    //평일운영종료시각
    private String weekOprEtime;
    //평일분양시작시각
    private String weekCellStime;
    //평일분양종료시각
    private String weekCellEtime;
    //주말운영시작시각
    private String weekendOprStime;
    //주말운영종료시각
    private String weekendOprEtime;
    //주말분양시작시각
    private String weekendCellStime;
    //주말분양종료시각
    private String weekendCellEtime;
    //휴무일
    private String closeDay;
    //수의사 인원 수
    private String vetPersonCnt;
    //사양 관리사 인원 수
    private String specsPersonCnt;
    //진료실 수
    private String medicalCnt;
    //사육실 수
    private String breedCnt;
    // 격리실 수
    private String quarabtineCnt;
    // 사료보관실 수
    private String feedCnt;
    //구조운반용차량보유대수
    private String transCarCnt;
    // 전화번호
    private String careTel;
    // 데이터 기준일자
    private String dataStdDt;
    // 한 페이지 결과 수
    private String numOfRows;
    // 페이지 번호
    private String pageNo;
    // 전체 결과 수
    private String totalCount;

    public String getQueryString(){
        return UriComponentsBuilder.newInstance()
                .queryParam("careNm" , careNm)
                .queryParam("orgNm" , orgNm)
                .queryParam("divisionNm" , divisionNm)
                .queryParam("saveTrgtAnimal",saveTrgtAnimal)
                .queryParam("careAddr" , careAddr)
                .queryParam("jibunAddr" , jibunAddr)
                .queryParam("lat" , lat)
                .queryParam("lng" , lng)
                .queryParam("dsignationDate" , dsignationDate)
                .queryParam("weekOprStime" , weekOprStime)
                .queryParam("weekOprEtime" , weekOprEtime)
                .queryParam("weekCellStime" , weekCellStime)
                .queryParam("weekCellEtime" , weekCellEtime)
                .queryParam("weekendOprStime" , weekendOprStime)
                .queryParam("weekendOprEtime" , weekendOprEtime)
                .queryParam("weekendCellStime" , weekendCellStime)
                .queryParam("weekendCellEtime" , weekendCellEtime)
                .queryParam("closeDay" , closeDay)
                .queryParam("vetPersonCnt" , vetPersonCnt)
                .queryParam("specsPersonCnt" , specsPersonCnt)
                .queryParam("medicalCnt" , medicalCnt)
                .queryParam("medicalCnt" , medicalCnt)
                .queryParam("breedCnt" , breedCnt)
                .queryParam("quarabtineCnt" , quarabtineCnt)
                .queryParam("feedCnt" , feedCnt)
                .queryParam("transCarCnt" , transCarCnt)
                .queryParam("careTel" , careTel)
                .queryParam("dataStdDt" , dataStdDt)
                .queryParam("numOfRows" , numOfRows)
                .queryParam("pageNo" , pageNo)
                .queryParam("totalCount" , totalCount)
                .build().encode().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(careNm, shelter.careNm) && Objects.equals(orgNm, shelter.orgNm) && Objects.equals(divisionNm, shelter.divisionNm) && Objects.equals(saveTrgtAnimal, shelter.saveTrgtAnimal) && Objects.equals(careAddr, shelter.careAddr) && Objects.equals(jibunAddr, shelter.jibunAddr) && Objects.equals(lat, shelter.lat) && Objects.equals(lng, shelter.lng) && Objects.equals(dsignationDate, shelter.dsignationDate) && Objects.equals(weekOprStime, shelter.weekOprStime) && Objects.equals(weekOprEtime, shelter.weekOprEtime) && Objects.equals(weekCellStime, shelter.weekCellStime) && Objects.equals(weekCellEtime, shelter.weekCellEtime) && Objects.equals(weekendOprStime, shelter.weekendOprStime) && Objects.equals(weekendOprEtime, shelter.weekendOprEtime) && Objects.equals(weekendCellStime, shelter.weekendCellStime) && Objects.equals(weekendCellEtime, shelter.weekendCellEtime) && Objects.equals(closeDay, shelter.closeDay) && Objects.equals(vetPersonCnt, shelter.vetPersonCnt) && Objects.equals(specsPersonCnt, shelter.specsPersonCnt) && Objects.equals(medicalCnt, shelter.medicalCnt) && Objects.equals(breedCnt, shelter.breedCnt) && Objects.equals(quarabtineCnt, shelter.quarabtineCnt) && Objects.equals(feedCnt, shelter.feedCnt) && Objects.equals(transCarCnt, shelter.transCarCnt) && Objects.equals(careTel, shelter.careTel) && Objects.equals(dataStdDt, shelter.dataStdDt) && Objects.equals(numOfRows, shelter.numOfRows) && Objects.equals(pageNo, shelter.pageNo) && Objects.equals(totalCount, shelter.totalCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(careNm, orgNm, divisionNm, saveTrgtAnimal, careAddr, jibunAddr, lat, lng, dsignationDate, weekOprStime, weekOprEtime, weekCellStime, weekCellEtime, weekendOprStime, weekendOprEtime, weekendCellStime, weekendCellEtime, closeDay, vetPersonCnt, specsPersonCnt, medicalCnt, breedCnt, quarabtineCnt, feedCnt, transCarCnt, careTel, dataStdDt, numOfRows, pageNo, totalCount);
    }

}

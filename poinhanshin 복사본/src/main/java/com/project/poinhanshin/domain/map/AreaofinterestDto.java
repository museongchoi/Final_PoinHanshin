package com.project.poinhanshin.domain.map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AreaofinterestDto {
    private Integer areaofinterset_userno;  // FK, 회원번호
    private Integer location;     // FK, 지역번호
    private Integer locationarr;    // PK

    /* AreaofinterestDto table
        1 / 유저 1번 / 1(영등포)
        2 / 유저 1번 / 2(영포)
        3 / 유저 2번 / 1(영등포)
        4 / 유저 3번 / 1(권선)
    */
    public AreaofinterestDto(){}

    public AreaofinterestDto(Integer areaofinterset_userno, Integer location, Integer locationarr) {
        this.areaofinterset_userno = areaofinterset_userno;
        this.location = location;
        this.locationarr = locationarr;
    }
}

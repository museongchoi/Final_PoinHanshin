package com.project.poinhanshin.domain.mbti;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//MBTI
public class MBTIDto {
    private Integer MBTI;           // PK, mbit 번호
    private String MBTIcategory;    // mbti 이름

    public MBTIDto(Integer MBTI, String MBTIcategory) {
        this.MBTI = MBTI;
        this.MBTIcategory = MBTIcategory;
    }
}

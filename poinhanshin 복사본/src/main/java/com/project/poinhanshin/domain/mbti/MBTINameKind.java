package com.project.poinhanshin.domain.mbti;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MBTINameKind {
    private String kindCd = ""; // 품종번호
    private String animalname = ""; // 품종 이름
    private String MBTIcategory = ""; // MBTI 유형

    public MBTINameKind(String kindCd, String animalname, String MBTIcategory) {
        this.kindCd = kindCd;
        this.animalname = animalname;
        this.MBTIcategory = MBTIcategory;
    }
}

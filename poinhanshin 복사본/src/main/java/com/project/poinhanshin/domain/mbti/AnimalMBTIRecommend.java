package com.project.poinhanshin.domain.mbti;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// MBTI 별 동물

public class AnimalMBTIRecommend {
    private Integer AnimalMBTIrecommend_MBTI;   // FK, mbti번호
    private Integer animalno;           // PK, 동물번호
    private String animalname;          // sqltyoe : varchar, 강아지/고양이 종 이름


    public AnimalMBTIRecommend(){}

    public AnimalMBTIRecommend(Integer MBTI, Integer animalno, String animalname) {
        this.AnimalMBTIrecommend_MBTI = MBTI;
        this.animalno = animalno;
        this.animalname = animalname;
    }
}

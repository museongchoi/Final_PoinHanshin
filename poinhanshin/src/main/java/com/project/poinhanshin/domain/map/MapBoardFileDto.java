package com.project.poinhanshin.domain.map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MapBoardFileDto {

    // 지도 게시물 번호
    private Integer mapboardno;

    // 기본기
    private Integer mapboardfileno;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reg_date;
    // 원본이름
    private String original_file_name;
    // 서버 저장 이름
    private String stored_file_name;
    private Long fileSize;

    public MapBoardFileDto(Integer mapboardno, Integer mapboardfileno, Date reg_date, String original_file_name, String stored_file_name, Long fileSize) {
        this.mapboardno = mapboardno;
        this.mapboardfileno = mapboardfileno;
        this.reg_date = reg_date;
        this.original_file_name = original_file_name;
        this.stored_file_name = stored_file_name;
        this.fileSize = fileSize;
    }
}

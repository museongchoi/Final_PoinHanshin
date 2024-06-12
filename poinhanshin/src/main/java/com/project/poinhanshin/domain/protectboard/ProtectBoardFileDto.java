package com.project.poinhanshin.domain.protectboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProtectBoardFileDto {

    // 기본키
    private Integer protectboardfileno;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reg_date;
    // 원본 이름
    private String original_file_name;
    // 서버 저장 이름
    private String stored_file_name;
    // 임보자 게시물 번호
    private Integer protectboardno;

    private Long fileSize;

    public ProtectBoardFileDto(Integer protectboardfileno, Date reg_date, String original_file_name, String stored_file_name, Integer protectboardno, Long fileSize) {
        this.protectboardfileno = protectboardfileno;
        this.reg_date = reg_date;
        this.original_file_name = original_file_name;
        this.stored_file_name = stored_file_name;
        this.protectboardno = protectboardno;
        this.fileSize = fileSize;
    }
}

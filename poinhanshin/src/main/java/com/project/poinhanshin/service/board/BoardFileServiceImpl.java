package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.board.BoardFileDto;
import com.project.poinhanshin.mapper.board.BoardFileMapper;
import com.project.poinhanshin.mapper.board.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BoardFileServiceImpl implements BoardFileService {

    BoardFileMapper boardFileMapper;
    BoardMapper boardMapper;

    @Autowired
    public BoardFileServiceImpl(BoardFileMapper boardFileMapper, BoardMapper boardMapper) {
        this.boardFileMapper = boardFileMapper;
        this.boardMapper = boardMapper;
    }

    // 이미지 첨부 파일 불러오기
    @Override
    public List<BoardFileDto> boardSelectFile(Integer boardno) {
        return boardFileMapper.boardSelectFile(boardno);
    }

    //
    @Override
    public int boardDeleteFile(String stored_file_name) {

        // 이미지 파일 제거
        int result = boardFileMapper.boardDeleteFile(stored_file_name);

        // 이미지 파일 제거 성공시
        if(result == 1){
            String path = "/Users/yuyeong-u/fileStorage/board/";
            File file = new File(path + stored_file_name);
            if (file.exists()) {
                file.delete();
            }
        }

        return result;
    }


    @Override
    public int boardSelectCnt(Integer boardFileno) {
        return boardFileMapper.boardSelectCnt(boardFileno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int boardInsertFile(MultipartFile[] multipartFile, Integer board_userno) throws IOException {
        if(multipartFile == null)
        return 0;

        int boardno = boardMapper.boardSelectRecentBoardNo(board_userno);

        for(int i = 0 ; i < multipartFile.length ; i++){
            String originalFileName = multipartFile[i].getOriginalFilename();

            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

            String savePath = "/Users/yuyeong-u/fileStorage/board/" + storedFileName;

            multipartFile[i].transferTo(new File(savePath));

            BoardFileDto boardFileDto = new BoardFileDto(boardno , null , null , originalFileName , storedFileName,multipartFile[i].getSize());
            boardFileMapper.boardInsertFile(boardFileDto);
        }
        return 1;
    }
}

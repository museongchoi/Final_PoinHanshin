package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.board.BoardFileDto;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.mapper.board.BoardFileMapper;
import com.project.poinhanshin.mapper.board.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    BoardMapper boardMapper;
    BoardFileMapper boardFileMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, BoardFileMapper boardFileMapper) {
        this.boardMapper = boardMapper;
        this.boardFileMapper = boardFileMapper;
    }

    // 검색된 게시물 리스트 개수를 가져온다.
    @Override
    public int bringBoarndListCnt(SearchCondition sc) {
        return boardMapper.boardSearchResultCnt(sc);
    }

    // 검색된 게시물 리스트를 가져온다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap bringBoardList(SearchCondition sc) {
        // 리스트 목록을 가져온다.
        List<BoardDto> boardDtoList = boardMapper.boardSearchResultList(sc);
        //List<BoardDto> topBoardDtoList = boardMapper.selectViewCntTop();
        List<BoardDto> topBoardDtoList = boardMapper.selectLikeCntTop();

        // 각 게시물의 이미지들을 가져와 boardDto에 저장
        for(BoardDto boardDto : boardDtoList ){
            List<String> storedFileName = boardFileMapper.boardSelectFileName(boardDto.getBoardno());
            boardDto.setStoredFileName(storedFileName);
        }
        for(BoardDto boardDto : topBoardDtoList){
            List<String> storedFileName = boardFileMapper.boardSelectFileName(boardDto.getBoardno());
            boardDto.setStoredFileName(storedFileName);
        }
        /*for(BoardDto boardDto : topBoardDtoList){
            List<String> storedFileName = boardFileMapper.boardSelectFileName(boardDto.getBoardno());
            boardDto.setStoredFileName(storedFileName);
        }*/

        HashMap hashMap = new HashMap();
        hashMap.put("boardDtoList" , boardDtoList);
        hashMap.put("topBoardDtoList", topBoardDtoList);

        return hashMap;
    }

    // 특정 게시물 하나 가져오기
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BoardDto bringBoardOne(Integer boardno) {

        BoardDto boardDto = boardMapper.boardSelectContentOne(boardno);
        boardMapper.updateViewCnt(boardno);
        if(boardDto.getFileAttached() == 0){
            // 파일 없음
            return boardDto;
        }
        else {
            // 파일 있음
            List<BoardFileDto> boardFileDtoList = boardFileMapper.boardSelectFile(boardno);

            // 원본 파일 이름 저장하기 위한 리스트
            List<String> originalFileNameList = new ArrayList<>();

            // 서버 저장 이름 저장하기 위한 리스트
            List<String>  storedFileNameList = new ArrayList<>();

            // DB에서 게시물 이미지 파일들을 읽어 와서 각각 원본 파일 이름과 서버 저장 파일을 리스트에 저장
            for( BoardFileDto boardFileDto : boardFileDtoList){
                originalFileNameList.add(boardFileDto.getOriginal_file_name());
                storedFileNameList.add(boardFileDto.getStored_file_name());
            }

            // 게시물 객체에 원본 파일 이름 , 서버 저장 파일 이름 리스트를 저장
            boardDto.setOriginalFileName(originalFileNameList);
            boardDto.setStoredFileName(storedFileNameList);


            return boardDto;
        }
    }

    // 게시물을 등록한다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int writeContent(BoardDto boardDto) throws IOException {

        // 게시물 등록
        boardMapper.boardInsertContent(boardDto);
        System.out.println("BoardServiceImpl writeContent boardDto : "+boardDto);

        Integer currentBoardno = boardMapper.boardSelectRecentBoardNo(boardDto.getBoard_userno());
        System.out.println("BoardServiceImpl writeContent currentBoardno : "+currentBoardno);

        // 게시물 객체에 등록된 게시물 번호 설정
        boardDto.setBoardno(currentBoardno);
        // 첨부 파일 있음
        if(boardDto.getFileAttached() != 0){
            addImgFiles(boardDto);
        }
        return currentBoardno;
    }

    // 특정 게시물을 수정한다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyContent(BoardDto boardDto, Integer loginUser) throws IOException {

        int imgCnt = boardFileMapper.boardSelectCnt(boardDto.getBoardno());

        System.out.println("BoardService boardDto : " + boardDto);
        System.out.println("BoardService loginUser : "+ loginUser);
        System.out.println("BoardService imgCnt : "+ imgCnt);

        if(boardDto.getBoardFile() != null){
            // 추가된 이미지가 있는 경우
            boardDto.setFileAttached(1);
            addImgFiles(boardDto);
        } else {
            // 추가된 이미지가 없는 경우
            if(imgCnt== 0){
                // 기존 이미지가 없는 경우 ( 바로바로 삭제되기 떄문에 모두 삭제했다면 기존 이미지는 없을 것이다. )
                boardDto.setFileAttached(0);
            } else {
                // 기존 이미지가 있는 경우
                boardDto.setFileAttached(1);
            }
        }

        // 게시물 내용 업데이트
        boardMapper.boardUpdateContent(boardDto , loginUser);

        return boardDto.getBoardno();
    }

    // 특정 게시물을 삭제한다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeContent(Integer boardno, Integer loginUser) throws IOException {
        // 저장된 이미지 이름 목록 가져오기
        List<String> fileNameList = boardFileMapper.boardSelectFileName(boardno);

        // 게시물 삭제( 게시물 먼저 삭제하면 DB 데이터 사라짐 )
        int result = boardMapper.boardDeleteContent(boardno,loginUser);

        // 로컬 저장소에서 이미지 파일 삭제
        if( result == 1){
            String path = "/Users/yuyeong-u/fileStorage/board/";
            for(String fileName : fileNameList){
                File file = new File(path+fileName);
                if(file.exists()){
                    file.delete();
                }
            }
        }

        return result;
    }

    // 최근 등록 게시물 번호를 가져온다.
    @Override
    public int bringRecentRegContentNo(Integer board_userno) {
        return boardMapper.boardSelectRecentBoardNo(board_userno);
    }

    @Override
    public int updateFileAttached(Integer boardno, Integer fileAttached) {
        return boardMapper.boardUpdateFileAttached(boardno , fileAttached);
    }


    // 이미지 등록을 위한 메서드
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addImgFiles(BoardDto boardDto) throws IOException {
        System.out.println("BoardService addImgFiles boardDto : " + boardDto);
        for (MultipartFile boardFile : boardDto.getBoardFile()) {

            // 파일 이름 저장 (서버 이름 X)
            String originalFileName = boardFile.getOriginalFilename();
            // 서버 저장용 이름 // System.currentTimeMillis - 현재 몇 밀리초가 지났는지 - 겹치면 안 되기 때문
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            // 서버 컴퓨터 파일 저장 위치
            String savePath = "/Users/yuyeong-u/fileStorage/board/" + storedFileName;
            // java.io.File; // 지정된 경로로 파일을 넘긴다.
            boardFile.transferTo(new File(savePath));

            // 파일 테이블에 데이터 저장 ( 원본 파일 , 서버에 저장할 이름 , 부모 게시물 번호 )
            BoardFileDto boardFileDto = new BoardFileDto(boardDto.getBoardno() , null , null , originalFileName , storedFileName , boardFile.getSize());
            boardFileMapper.boardInsertFile(boardFileDto);
        }
    }

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    @Override
    public List<BoardDto> SelectMyBoardService(Integer userno) {
        List<BoardDto> SelectMyBoardService = boardMapper.SelectMyBoard(userno);
        return SelectMyBoardService;
    }

    // 마이페이지 - 자신이 쓴 커뮤니티 게시글 불러오기
    @Override
    public List<BoardDto> WriteMyBoardService(Integer userno) {
        List<BoardDto> WriteMyBoardService = boardMapper.WriteMyBoard(userno);
        return WriteMyBoardService;
    }
}

package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardFileMapper;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProtectBoardServiceImpl implements ProtectBoardService{

    ProtectBoardMapper protectBoardMapper;
    ProtectBoardFileMapper protectBoardFileMapper;

    // 의존성 주입
    @Autowired
    public ProtectBoardServiceImpl(ProtectBoardMapper protectBoardMapper, ProtectBoardFileMapper protectBoardFileMapper) {
        this.protectBoardMapper = protectBoardMapper;
        this.protectBoardFileMapper = protectBoardFileMapper;
    }


    // 검색된 게시물의 개수를 반환
    @Override
    public int searchResultCnt(SearchCondition sc) {
        return protectBoardMapper.searchResultCnt(sc);
    }

    // 검색된 게시물 리스트를 반환
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProtectBoardDto> searchResultList(SearchCondition sc) {
        // 리스트 목록을 가져온다.
        List<ProtectBoardDto> protectBoardDtoList = protectBoardMapper.searchResultList(sc);

        // 각 게시물의 이미지의 StoredFileName 가져와서 각 ProtectBoardDto에 저장
        for(ProtectBoardDto protectBoardDto : protectBoardDtoList){
            List<String> storedFileName = protectBoardFileMapper.selectFilesName(protectBoardDto.getProtectboardno());
            protectBoardDto.setStoredFileName(storedFileName);
        }
        return protectBoardDtoList;
    }

    // 특정 임보자 게시물 하나 가져오기
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProtectBoardDto bringBoardOne(Integer protectboardno) {
            ProtectBoardDto protectBoard =  protectBoardMapper.selectContentOne(protectboardno);
            if(protectBoard.getFileAttached() == 0){
                // 파일 없음
                return protectBoard;
            }
            else {
                // 파일 있음
                List<ProtectBoardFileDto> protectBoardFileDtoList = protectBoardFileMapper.selectFiles(protectboardno);

                // 원본 파일 이름 저장하기 위한 리스트
                List<String> originalFileNameList = new ArrayList<>();

                // 서버 저장 이름 저장하기 위한 리스트
                List<String>  storedFileNameList = new ArrayList<>();

                // DB에서 게시물 이미지 파일들을 읽어 와서 각각 원본 파일 이름과 서버 저장 파일을 리스트에 저장
                for(ProtectBoardFileDto protectBoardFileDto : protectBoardFileDtoList){
                    originalFileNameList.add(protectBoardFileDto.getOriginal_file_name());
                    storedFileNameList.add(protectBoardFileDto.getStored_file_name());
                }

                // 게시물 객체에 원본 파일 이름 , 서버 저장 파일 이름 리스트를 저장
                protectBoard.setOriginalFileName(originalFileNameList);
                protectBoard.setStoredFileName(storedFileNameList);

                return protectBoard;
            }
    }

    // 임보자 게시물을 등록
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertProductBoard(ProtectBoardDto protectBoardDto) throws IOException {
        // 게시물 등록
        protectBoardMapper.insertContent(protectBoardDto);

        // 등록한 게시물 번호
        Integer currentProtectboardno = protectBoardMapper.selectRecentBoardno(protectBoardDto.getProtectboard_userno());
        protectBoardDto.setProtectboardno(currentProtectboardno);
        // 첨부 파일 있음
        if (protectBoardDto.getFileAttached() != 0) {
            addImgFiles(protectBoardDto);
        }
        return currentProtectboardno;
    }

    // 임보자 게시물을 수정한다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProductBoard(ProtectBoardDto protectBoardDto , Integer loginUser) throws IOException {

         // 기존 이미지 수 가져오기
        int imgCnt = protectBoardFileMapper.selectCnt(protectBoardDto.getProtectboardno());

        if(protectBoardDto.getProtectboardFile() != null){
            // 추가된 이미지가 있는 경우
            protectBoardDto.setFileAttached(1);
            addImgFiles(protectBoardDto);
        } else {
            // 추가된 이미지가 없는 경우
            if(imgCnt== 0){
                // 기존 이미지가 없는 경우 ( 바로바로 삭제되기 떄문에 모두 삭제했다면 기존 이미지는 없을 것이다. )
                protectBoardDto.setFileAttached(0);
            } else {
                // 기존 이미지가 있는 경우
                protectBoardDto.setFileAttached(1);
            }
        }

        // 게시물 내용 업데이트
        int result = protectBoardMapper.updateContent(protectBoardDto , loginUser);
        System.out.println("실행 됨 "+ result);
        return protectBoardDto.getProtectboardno();
    }


    // 임보자 게시물을 삭제
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProductBoard(Integer protectboardno, Integer loginUser) {

        // 저장된 이미지 이름 목록 가져오기
        List<String> filesName = protectBoardFileMapper.selectFilesName(protectboardno);

        // 게시물 삭제 ( 게시물 먼저 삭제하면 DB 데이터 사라짐 )
        int result = protectBoardMapper.deleteContent(protectboardno ,loginUser);

        // 로컬 저장소에서 이미지 파일 삭제
        if( result == 1){
            String path = "/Users/yuyeong-u/fileStorage/protectboard/";
            for(String fileName : filesName){
                File file = new File(path + fileName);
                if (file.exists()) {
                    file.delete();
                }
            }
        }

        return result;
    }



    // 최근 등록한 게시물의 번호를 반환
    @Override
    public int readWritedBoardno(Integer protectboard_userno) {
        return protectBoardMapper.selectRecentBoardno(protectboard_userno);
    }

    // 파일 존재 여부 값 수정
    @Override
    public int updateFileAttached(Integer protectboardno, Integer fileAttached) {
        return protectBoardMapper.updateFileAttached(protectboardno,fileAttached);
    }

    // 이미지 등록을 위한 메서드
    @Override
    public void addImgFiles(ProtectBoardDto protectBoardDto) throws IOException {
        for (MultipartFile protectboardFile : protectBoardDto.getProtectboardFile()) {

            // 파일 이름 저장 (서버 이름 X)
            String originalFileName = protectboardFile.getOriginalFilename();
            // 서버 저장용 이름 // System.currentTimeMillis - 현재 몇 밀리초가 지났는지 - 겹치면 안 되기 때문
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            // 서버 컴퓨터 파일 저장 위치
            String savePath = "/Users/yuyeong-u/fileStorage/protectboard/" + storedFileName;
            // java.io.File; // 지정된 경로로 파일을 넘긴다.
            protectboardFile.transferTo(new File(savePath));

            // 파일 테이블에 데이터 저장 ( 원본 파일 , 서버에 저장할 이름 , 부모 게시물 번호 )
            ProtectBoardFileDto protectBoardFileDto = new ProtectBoardFileDto(null, null, originalFileName, storedFileName, protectBoardDto.getProtectboardno(), protectboardFile.getSize());
            protectBoardFileMapper.insertFiles(protectBoardFileDto);
        }
    }

    @Override
    public List<ProtectBoardDto> selectMyprotectboardService(Integer userno) {
            List<ProtectBoardDto> selectMyprotectboardService = protectBoardMapper.selectMyprotectboard(userno);
        return selectMyprotectboardService;
    }

    @Override
    public List<ProtectBoardDto> writeMyprotectboardService(Integer userno) {
        List<ProtectBoardDto> writeMyprotectboardService = protectBoardMapper.writeMyprotectboard(userno);
        return writeMyprotectboardService;
    }
}
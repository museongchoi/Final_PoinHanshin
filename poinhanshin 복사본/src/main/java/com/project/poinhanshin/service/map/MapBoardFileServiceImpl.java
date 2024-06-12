package com.project.poinhanshin.service.map;

import com.project.poinhanshin.domain.map.MapBoardFileDto;
import com.project.poinhanshin.mapper.map.MapFileMapper;
import com.project.poinhanshin.mapper.map.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class MapBoardFileServiceImpl implements MapBoardFileService{

    MapFileMapper mapFileMapper;
    MapMapper mapMapper;

    @Autowired
    public MapBoardFileServiceImpl(MapFileMapper mapFileMapper, MapMapper mapMapper) {
        this.mapFileMapper = mapFileMapper;
        this.mapMapper = mapMapper;
    }

    // 이미지 파일 불러오기
    @Override
    public List<MapBoardFileDto> MapBoardSelectFileService(Integer mapboardno) {
        return mapFileMapper.MapBoardSelectFile(mapboardno);
    }

    // 이미지 파일 저장
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int MapBoardInsertFileService(MultipartFile[] multipartFile, Integer mapboard_userno) throws IOException {

        if(multipartFile == null)
            return 0;

        int mapBoardno = mapMapper.selectRecentMapBoardNo(mapboard_userno);

        for(int i = 0 ; i < multipartFile.length ; i++){
            String originalFileName = multipartFile[i].getOriginalFilename();

            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

            String savePath = "/Users/yuyeong-u/fileStorage/mapBoard/" + storedFileName;

            multipartFile[i].transferTo(new File(savePath));

            MapBoardFileDto mapBoardFileDto = new MapBoardFileDto(mapBoardno , null , null , originalFileName,storedFileName , multipartFile[i].getSize());
            mapFileMapper.MapBoardInsertFile(mapBoardFileDto);
        }
        return 1;
    }

    // 이미지 파일 삭제
    @Override
    public int MapBoardDeleteFileService(String stored_file_name) {

        // 이미지 파일 제거
        int result = mapFileMapper.MapBoardDeleteFile(stored_file_name);

        if(result == 1){
            String path = "/Users/yuyeong-u/fileStorage/mapBoard/";
            File file = new File(path+stored_file_name);
            if(file.exists()){
                file.delete();
            }
        }

        return result;
    }

    // 이미지 개수 반환
    @Override
    public int MapBoardSelectCnt(Integer mapboardfileno) {
        return mapFileMapper.MapBoardSelectCnt(mapboardfileno);
    }


}

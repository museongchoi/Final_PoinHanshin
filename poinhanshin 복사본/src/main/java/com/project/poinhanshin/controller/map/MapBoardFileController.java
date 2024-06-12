package com.project.poinhanshin.controller.map;

import com.project.poinhanshin.domain.map.MapBoardFileDto;
import com.project.poinhanshin.service.map.MapBoardFileService;
import com.project.poinhanshin.service.map.MapBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MapBoardFileController {

    MapBoardService mapBoardService;

    MapBoardFileService mapBoardFileService;

    @Autowired
    public MapBoardFileController(MapBoardService mapBoardService, MapBoardFileService mapBoardFileService) {
        this.mapBoardService = mapBoardService;
        this.mapBoardFileService = mapBoardFileService;
    }

    // 게시물 첨부 파일 읽어오기
    @GetMapping("/mapBoard/file")
    @ResponseBody
    public ResponseEntity<List<MapBoardFileDto>>  readMapBoardFile(Integer mapboardno){

        // 게시물의 이미지들을 읽어온다.
        List<MapBoardFileDto> mapBoardFileDtoList = mapBoardFileService.MapBoardSelectFileService(mapboardno);

        System.out.println("mapBoard - file : mapBoardFileDtoList" + mapBoardFileDtoList);

        return new ResponseEntity<List<MapBoardFileDto>>(mapBoardFileDtoList , HttpStatus.OK);
    }

    // 게시물 첨부 파일 삭제
    @DeleteMapping("/mapBoard/remove")
    @ResponseBody
    public ResponseEntity<String> removeMapBoardFile(@RequestBody String dataURL){

        System.out.println(dataURL);

        if(dataURL.length() < 100){
            String stored_file_name = dataURL.substring(dataURL.indexOf("/mapboard/")+10 , dataURL.lastIndexOf('"'));
            System.out.println(stored_file_name);
            mapBoardFileService.MapBoardDeleteFileService(stored_file_name);
            return new ResponseEntity<String>("Remove_existing_file" , HttpStatus.OK);
        }
        return new ResponseEntity<String>("Remove_new_file" , HttpStatus.OK);
    }
}

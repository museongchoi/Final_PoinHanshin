package com.project.poinhanshin.etc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.poinhanshin.domain.api.Abandoned_animal;
import com.project.poinhanshin.domain.api.Shelter;
import com.project.poinhanshin.domain.api.KindDto;
import org.apache.groovy.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;

@Component
public class ApiExplorer {

    // API 서버에 URL을 보내고 API 서버에서 JSON 파일을 받아 이를 String으로 변환하는 메서드
    private static String getJSONToString(StringBuilder urlBuilder) throws IOException {
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // OpenAPI 요청 주소 제작 및 요청 후 데이터 JSON으로 받는 부분 ##끝

        return sb.toString();
    }

    public Shelter[] SearchShelterList(
            String care_reg_no , String care_nm , String numOfRows , String pageNo
    ) throws IOException, ParseException {

        // OpenAPI 요청 주소 제작 및 요청 후 데이터 JSON으로 받는 부분 ##시작
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=cRuWOhJ88ti%2BpxoS0eWhj8wzd9nGK8dQdRxM%2F%2B6Z1iYtVGdJ2d27uc4xMwZfCKiBGG4TYsbW%2Fdf5sOex8slQYA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode(care_reg_no, "UTF-8")); /*보호센터등록번호*/
        urlBuilder.append("&" + URLEncoder.encode("care_nm","UTF-8") + "=" + URLEncoder.encode(care_nm, "UTF-8")); /*동물보호센터명*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수 (1,000 이하)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*xml(기본값) 또는 json*/

        String result = getJSONToString(urlBuilder);

        JSONParser parser = new JSONParser();

        // JSON 문자열을 JAVA 객체로 변환 시작
        JSONObject obj = (JSONObject) parser.parse(result);

        JSONObject responseResult = (JSONObject)obj.get("response");
        JSONObject bodyResult = (JSONObject)responseResult.get("body");
        // 검색 보호소 수
        String totalCount = bodyResult.get("totalCount").toString();

        JSONObject itemsResult = (JSONObject)bodyResult.get("items");

        JSONArray itemResult = (JSONArray) itemsResult.get("item");

        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject objArr [] = new JSONObject[itemResult.size()];
        for(int i = 0 ; i < itemResult.size(); i++){
            objArr[i] = (JSONObject) itemResult.get(i);
        }

        Shelter shelterArr[] = new Shelter[itemResult.size()];
        for(int i = 0 ; i < itemResult.size(); i++){
            shelterArr[i] = objectMapper.readValue(objArr[i].toJSONString() , Shelter.class);
            //System.out.println(shelterArr[i]);
            if(i == 0)
                shelterArr[0].setTotalCount(totalCount);
        }
        // JSON 문자열을 JAVA 객체로 변환 끝

        // 변환된 객체 배열을 반환
        return shelterArr;
    }





    public Abandoned_animal[] SearchAnimalList(
            String bgnde , String endde , String upkind , String kind , String upr_cd , String org_cd , String care_reg_no,
            String state , String neuter_yn , String pageNo , String numOfRows
    ) throws IOException, ParseException {

        // 유기동물 조회 시작
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=cRuWOhJ88ti%2BpxoS0eWhj8wzd9nGK8dQdRxM%2F%2B6Z1iYtVGdJ2d27uc4xMwZfCKiBGG4TYsbW%2Fdf5sOex8slQYA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("bgnde", "UTF-8") + "=" + URLEncoder.encode(bgnde, "UTF-8")); /*유기날짜*/
        urlBuilder.append("&" + URLEncoder.encode("endde", "UTF-8") + "=" + URLEncoder.encode(endde, "UTF-8")); /*유기날짜*/
        urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "=" + URLEncoder.encode(upkind, "UTF-8")); /*축종코드*/
        urlBuilder.append("&" + URLEncoder.encode("kind", "UTF-8") + "=" + URLEncoder.encode(kind, "UTF-8")); /*품종코드*/
        urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "=" + URLEncoder.encode(upr_cd, "UTF-8")); /*시도코드*/
        urlBuilder.append("&" + URLEncoder.encode("org_cd", "UTF-8") + "=" + URLEncoder.encode(org_cd, "UTF-8")); /*시군구코드*/
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no", "UTF-8") + "=" + URLEncoder.encode(care_reg_no, "UTF-8")); /*보호소번호*/
        urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8")); /*상태*/
        urlBuilder.append("&" + URLEncoder.encode("neuter_yn", "UTF-8") + "=" + URLEncoder.encode(neuter_yn, "UTF-8")); /*상태*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*페이지당 보여줄 개수*/
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("JSON" , "UTF-8")); /*xml(기본값) 또는 json*/

        // 유기동물 조회 끝
        String result = getJSONToString(urlBuilder);

        try{
            JSONParser parser = new JSONParser();

            JSONObject obj = (JSONObject) parser.parse(result);

            JSONObject responseResult = (JSONObject)obj.get("response");
            JSONObject bodyResult = (JSONObject)responseResult.get("body");
            // 검색 유기동물 수
            String totalCount = bodyResult.get("totalCount").toString();

            JSONObject itemsResult = (JSONObject)bodyResult.get("items");
            //JSONObject itemResult = (JSONObject) itemsResult.get("item");
            // 각 게시물 목록
            JSONArray itemResult = (JSONArray) itemsResult.get("item");

            ObjectMapper objectMapper = new ObjectMapper();



            JSONObject objArr [] = new JSONObject[itemResult.size()];
            for(int i = 0 ; i < itemResult.size(); i++){
                objArr[i] = (JSONObject) itemResult.get(i);
            }

            Abandoned_animal AAArr[] = new Abandoned_animal[itemResult.size()];

            // JSON 객체를 자바 객체로 변환한 후 배열에 저장
            for(int i = 0 ; i < itemResult.size(); i++){
                AAArr[i] = objectMapper.readValue(objArr[i].toJSONString() , Abandoned_animal.class);
                if(i == 0)
                    AAArr[0].setTotalCount(totalCount);
            }
            return AAArr;
        }catch(ParseException e) {
            // 에러시 null 반환
            return null;
        }
    }

    public KindDto[] getKind() throws IOException, ParseException {

        String up_kind_cd_dog = "417000";
        String up_kind_cd_cat = "422400";

        KindDto[] KindDtoList1 = getKindDto(up_kind_cd_dog);
        KindDto[] KindDtoList2 = getKindDto(up_kind_cd_cat);

        for(KindDto kindDto : KindDtoList1 ){
            System.out.println(kindDto);
        }
        for(KindDto kindDto : KindDtoList2 ){
            System.out.println(kindDto);
        }
        return Arrays.concat(KindDtoList1, KindDtoList2);
    }

    private static KindDto[] getKindDto(String up_kind_cd) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/kind"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=cRuWOhJ88ti%2BpxoS0eWhj8wzd9nGK8dQdRxM%2F%2B6Z1iYtVGdJ2d27uc4xMwZfCKiBGG4TYsbW%2Fdf5sOex8slQYA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("up_kind_cd", "UTF-8") + "=" + URLEncoder.encode(up_kind_cd, "UTF-8")); /*축종코드*/
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("JSON" , "UTF-8")); /*xml(기본값) 또는 json*/

        String result = getJSONToString(urlBuilder);

        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(result);

        JSONObject responseResult = (JSONObject)obj.get("response");
        JSONObject bodyResult = (JSONObject)responseResult.get("body");
        JSONObject itemsResult = (JSONObject)bodyResult.get("items");
        JSONArray itemResult = (JSONArray) itemsResult.get("item");

        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject objArr [] = new JSONObject[itemResult.size()];
        for(int i = 0 ; i < itemResult.size(); i++){
            objArr[i] = (JSONObject) itemResult.get(i);
        }

        KindDto[] KindDtoList = new KindDto[itemResult.size()];

        for(int i = 0 ; i < itemResult.size(); i++){
            KindDtoList[i] = objectMapper.readValue(objArr[i].toJSONString() , KindDto.class);
        }
        return KindDtoList;
    }


}
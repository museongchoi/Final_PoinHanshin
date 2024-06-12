/*
package com.project.poinhanshin.mapper.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LikeMapperImpl implements LikeMapper {

    SqlSession sqlSession;

    @Autowired
    public LikeMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public String namespace = "com.project.poinhanshin.mapper.board.LikeMapper.";

    @Override
    public int likeCheck(Integer LoginId, Integer bno) {
        Map hashMap = new HashMap();
        hashMap.put("like_userno", LoginId);
        hashMap.put("like_boardno", bno);
        return sqlSession.selectOne(namespace + "likeCheck", hashMap);

    }

    @Override
    public int addLike(Integer LoginId, Integer bno) {
        Map hashMap = new HashMap();
        hashMap.put("like_userno", LoginId);
        hashMap.put("like_boardno", bno);
        return sqlSession.insert(namespace + "addLike", hashMap);
    }

    @Override
    public int deleteLike(Integer LoginId, Integer bno) {
        Map hashMap = new HashMap();
        hashMap.put("like_userno", LoginId);
        hashMap.put("like_boardno", bno);
        return sqlSession.delete(namespace + "deleteLike", hashMap);
    }


    @Override
    public int updateLikeCnt(Integer bno, Integer num) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("boardno", bno);
        hashMap.put("num", num);
        return sqlSession.update(namespace + "updateLikeCnt", hashMap);
    }

}
*/

package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	private int count = 0;
	@Autowired
	private SqlSession sqlSession;

	public List<PersonVo> SelectAll() {
		List<PersonVo> pList = sqlSession.selectList("phonebook.SelectAll");
		System.out.println(pList);
		return pList;
	}

	public PersonVo Select(int pId) {
		PersonVo pVo = sqlSession.selectOne("phonebook.Select", pId);
		return pVo;
	}

	public List<PersonVo> Search(PersonVo pVo) {
		List<PersonVo> sList = sqlSession.selectList("phonebook.Search", pVo);
		return sList;
	}

	public int Insert(PersonVo pVo) {
		count += sqlSession.insert("phonebook.Insert", pVo);
		return count;
	}

	public int Update(PersonVo pVo) {
		count += sqlSession.update("phonebook.Update", pVo);
		return count;
	}

	public int Delete(int pId) {
		count += sqlSession.delete("phonebook.Delete", pId);
		return count;
	}
}

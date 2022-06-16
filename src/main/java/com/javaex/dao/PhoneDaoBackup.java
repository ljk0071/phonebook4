package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDaoBackup {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String driver = "oracle.jdbc.driver.OracleDriver";
//	private String id = "phonedb";
//	private String pw = "phonedb";
	private int count = 0;

	private void getConnection() {

		try {
			conn = dataSource.getConnection();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void Close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public String Create() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += "create table person( " + "    person_id number(5) " + "    ,name varchar2(30) not null "
					+ "    ,hp varchar2(20) " + "    ,company varchar2(20) " + "    ,primary key(person_id) )";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query); // 문자열을 쿼리로 만들기

			// 실행
			pstmt.executeUpdate();
			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		Close();
		return "Person테이블 생성 완료";
	}

	public String Drop() {
		try {
			getConnection();

			// SQL문 준비
			String query = "";
			query += "drop table person ";

			// 바인딩
			pstmt = conn.prepareStatement(query); // 문자열을 쿼리로 만들기

			// 실행
			pstmt.executeUpdate();
			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		Close();
		return "Person테이블 삭제 완료";
	}

	public String CreateSeq() {
		try {
			getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += ("create sequence seq_person_id " + "increment by 1 " + "start with 1 " + "nocache");

			// 바인딩
			pstmt = conn.prepareStatement(query); // 문자열을 쿼리로 만들기

			// 실행
			pstmt.executeUpdate();
			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		Close();
		return "Person_id시퀀스 생성 완료";
	}

	public String DropSeq() {
		try {
			getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += "drop sequence seq_person_id ";

			// 바인딩
			pstmt = conn.prepareStatement(query); // 문자열을 쿼리로 만들기

			// 실행
			pstmt.executeUpdate();
			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		Close();
		return "Person_id시퀀스 삭제 완료";
	}

	public int Insert(PersonVo pVo) {
		count += sqlSession.insert("phonebook.Insert", pVo);
		return count;
	}

//	public int Insert(PersonVo personVo) {
//		try {
//			getConnection();
//			// 3. SQL문 준비 / 바인딩 / 실행
//
//			// SQL문 준비
//			String query = "";
//			query += "insert into person ";
//			query += "values(seq_person_id.nextval, ?, ?, ?) ";
//
//			// 바인딩
//			pstmt = conn.prepareStatement(query); // 문자열을 쿼리로 만들기
//			pstmt.setString(1, personVo.getName());
//			pstmt.setString(2, personVo.getHp());
//			pstmt.setString(3, personVo.getCompany());
//
//			// 실행
//			count = pstmt.executeUpdate(); // 쿼리문 실행 -->리턴값으로 성공갯수
//			System.out.println(count + "건이 등록 되었습니다.");
//
//			// 4.결과처리
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		Close();
//		return count;
//	}

	public int Delete(int pId) {
		count += sqlSession.delete("phonebook.Delete", pId);
		return count;
	}

//	public int Delete(int personId) {
//		try {
//			getConnection();
//
//			// 3. SQL문 준비 / 바인딩 / 실행
//
//			// SQL문 준비
//			String query = "";
//			query += "delete from person ";
//			query += "where person_id = ?";
//
//			// 바인딩
//			pstmt = conn.prepareStatement(query); // 문자열을 쿼리로 만들기
//			pstmt.setInt(1, personId);
//
//			// 실행
//			count = pstmt.executeUpdate(); // 쿼리문 실행 -->리턴값으로 성공갯수
//			System.out.println(count + "건이 삭제 되었습니다.");
//
//			// 4.결과처리
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		Close();
//		return count;
//	}

	public List<PersonVo> SelectAll() {
		List<PersonVo> pList = sqlSession.selectList("phonebook.SelectAll");
		System.out.println(pList);
		return pList;
	}

//	public List<PersonVo> SelectAll() {
//		List<PersonVo> personList = new ArrayList<PersonVo>();
//		try {
//			getConnection();
//
//			String query = "";
//			query += "select ";
//			query += "person_id, name, hp, company ";
//			query += "from person ";
//			query += "order by person_id ";
//
//			pstmt = conn.prepareStatement(query);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				int personId = rs.getInt(1);
//				String name = rs.getString(2);
//				String hp = rs.getString(3);
//				String company = rs.getString(4);
//				PersonVo personVo = new PersonVo(personId, name, hp, company);
//				personList.add(personVo);
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		Close();
//		return personList;
//	}

	public PersonVo Select(int pId) {
		PersonVo pVo = sqlSession.selectOne("phonebook.Select", pId);
		return pVo;
	}

//	public PersonVo Select(int personId) {
//		PersonVo personVo = new PersonVo();
//		try {
//			getConnection();
//
//			String query = "";
//			query += "select ";
//			query += "person_id, name, hp, company ";
//			query += "from person ";
//			query += "where person_id = ? ";
//			query += "order by person_id ";
//
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, personId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				int personId2 = rs.getInt(1);
//				String name = rs.getString(2);
//				String hp = rs.getString(3);
//				String company = rs.getString(4);
//				personVo = new PersonVo(personId2, name, hp, company);
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		Close();
//		return personVo;
//	}

	public int Update(PersonVo pVo) {
		count += sqlSession.update("phonebook.Update", pVo);
		return count;
	}

//	public int Update(PersonVo personVo) {
//		try {
//			getConnection();
//			// 3. SQL문 준비 / 바인딩 / 실행
//
//			// SQL문 준비
//			String query = "";
//			query += "update person ";
//			query += "set name = ? ";
//			query += ",hp = ? ";
//			query += ",company = ? ";
//			query += "where person_id = ? ";
//
//			// 바인딩
//			pstmt = conn.prepareStatement(query); // 문자열을 쿼리로 만들기
//			pstmt.setString(1, personVo.name);
//			pstmt.setString(2, personVo.hp);
//			pstmt.setString(3, personVo.company);
//			pstmt.setInt(4, personVo.personId);
//
//			// 실행
//			count = pstmt.executeUpdate(); // 쿼리문 실행 -->리턴값으로 성공갯수
//			System.out.println(count + "건이 수정 되었습니다.");
//
//			// 4.결과처리
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		Close();
//		return count;
//	}

	public List<PersonVo> Search(PersonVo pVo) {
		List<PersonVo> sList = sqlSession.selectList("phonebook.Search", pVo);
		return sList;
	}

//	public List<PersonVo> Search(PersonVo pVo) {
//		List<PersonVo> personList = new ArrayList<PersonVo>();
//		try {
//			getConnection();
//
//			// 3. SQL문 준비 / 바인딩 / 실행
//
//			// SQL문 준비
//			String query = "select person_id " + "    ,name " + "    ,hp " + "    ,company " + "from person "
//					+ "where name like '%'||? " + "and (hp like '%'||? " + "or hp like '%'||?||'%' "
//					+ "or hp like ?||'%') " + "and (company like '%'||? " + "or company like '%'||?||'%' "
//					+ "or company like ?||'%') " + "or name like '%'||?||'%' " + "and (hp like '%'||? "
//					+ "or hp like '%'||?||'%' " + "or hp like ?||'%') " + "and (company like '%'||? "
//					+ "or company like '%'||?||'%' " + "or company like ?||'%') " + "or name like ?||'%' "
//					+ "and (hp like '%'||? " + "or hp like '%'||?||'%' " + "or hp like ?||'%') "
//					+ "and (company like '%'||? " + "or company like '%'||?||'%' " + "or company like ?||'%') ";
//
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, pVo.name);
//			pstmt.setString(2, pVo.hp);
//			pstmt.setString(3, pVo.hp);
//			pstmt.setString(4, pVo.hp);
//			pstmt.setString(5, pVo.company);
//			pstmt.setString(6, pVo.company);
//			pstmt.setString(7, pVo.company);
//			pstmt.setString(8, pVo.name);
//			pstmt.setString(9, pVo.hp);
//			pstmt.setString(10, pVo.hp);
//			pstmt.setString(11, pVo.hp);
//			pstmt.setString(12, pVo.company);
//			pstmt.setString(13, pVo.company);
//			pstmt.setString(14, pVo.company);
//			pstmt.setString(15, pVo.name);
//			pstmt.setString(16, pVo.hp);
//			pstmt.setString(17, pVo.hp);
//			pstmt.setString(18, pVo.hp);
//			pstmt.setString(19, pVo.company);
//			pstmt.setString(20, pVo.company);
//			pstmt.setString(21, pVo.company);
//			
//			System.out.println(query);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				int personId = rs.getInt(1);
//				String name = rs.getString(2);
//				String hp = rs.getString(3);
//				String company = rs.getString(4);
//				PersonVo personVo = new PersonVo(personId, name, hp, company);
//				personList.add(personVo);
//			}
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		Close();
//		return personList;
//	}

}

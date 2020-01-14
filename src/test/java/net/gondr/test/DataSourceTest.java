package net.gondr.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-context.xml" })
public class DataSourceTest {
	@Autowired
	private DataSource ds;
	@Autowired
	private SqlSessionFactory factory;

	@Test
	public void testFactory() {
		// 공장이 잘 설정 되어있는지 체크
		System.out.println(factory);
	}

	@Test
	public void testSession() {
		// 공장에서 찍어내는 세션이 잘 되어있는지 체크
		try {
			// 풀 안에 있는 놀고 있는 연결을 반납함.
			SqlSession session = factory.openSession();
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testConnection() throws Exception {
		try {
			Connection con = ds.getConnection();
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

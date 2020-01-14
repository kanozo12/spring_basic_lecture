package net.gondr.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.gondr.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession session;

	private static final String namespace = "net.gondr.mappers.UserMapper";

	@Override
	public void insertUser(UserVO user) {
		session.insert(namespace + ".insertUser", user);
	}
	
}
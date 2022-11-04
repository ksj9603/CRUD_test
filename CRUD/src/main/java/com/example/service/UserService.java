package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.vo.UserVO;


@Service
@Transactional
//@RequiredArgsConstructor
public class UserService {

//	@Autowired
//	private final DataSource dataSource;
//	
//	@Autowired
//	private final JdbcTemplate jdbcTemplate;
//	
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	UserMapper userMapper;
	
	public void save(UserVO userVO) {
//		try(Connection connection = sqlSessionFactory.openSession().getConnection()) {
			
			
			System.out.println("세션성공");
//			jdbcTemplate.execute("insert into account values('3','3','3')");
			userMapper.save(userVO);
			
			
//			String id = userVO.getId();
//			String pwd = userVO.getPwd();
//			String name = userVO.getName();
//			System.out.println(connection.getMetaData().getUserName());
//			System.out.println(connection.getMetaData().getURL());
//			System.out.println(dataSource.getClass());
//			System.out.println(id+pwd+name);
//			jdbcTemplate.execute("insert into account values('"+id+"','"+pwd+"','"+name+"')");
		}
	
	public List<UserVO> getUserList() {
		List<UserVO> userList = userMapper.getList();
		
		return userList;
	}
	
	public boolean checkUser(UserVO userVO) {
		boolean checkU = userMapper.checkId(userVO);
		
		return checkU;
	}
	
	
	public void delUser(String id) {
		userMapper.delUser(id);
	}
	
	public List<UserVO> alterUser(String id) {
		List<UserVO> alterUser = userMapper.alterUser(id);
		
		return alterUser;
	}
	
	public void changeUser(UserVO userVO) {
		userMapper.changeUser(userVO);
	}
		
	}

package ymw.delivery.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ymw.delivery.dto.Join;

@Repository
public class UserDAOlmp implements UserDAO {

	@Autowired
	private SqlSession sql;
	
	@Override
	public void join(Join join) {
		sql.insert("user.join" , join);	
	}
	
	@Override
	public int overlapCheck(String value, String valueType) {
		Map<String, String> map = new HashMap<>();
		map.put("value", value);
		map.put("valueType", valueType);
		
		return sql.selectOne("user.overlapCheck" ,map);
	}

}
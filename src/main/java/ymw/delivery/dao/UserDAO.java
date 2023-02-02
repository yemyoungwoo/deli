package ymw.delivery.dao;

import ymw.delivery.dto.Join;

public interface UserDAO {
	void join(Join join);

	int overlapCheck(String value, String valueType);
}
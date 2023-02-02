package ymw.delivery.service;

import ymw.delivery.dto.Join;

public interface UserService {
	void join(Join join);

	int overlapCheck(String value, String valueType);
}

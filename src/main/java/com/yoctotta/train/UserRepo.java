package com.yoctotta.train;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
	public static final String login = "select * from user where user_name=?1 and password=?2";

	@Query(value = login, nativeQuery = true)
	public String login(String username, String password);

}

package com.yoctotta.train;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
	public static final String sendMessage = "select  content from message where msg_id=?1";
	public static final String receiveMessage = "select  content from message where msg_id=?1";
	public static final String findAllMessagesSendToUniqueUser = "select * from message where from_user_id=?1 and to_user_id=?2";
	public static final String findAllMessagesSendFromUniqueUser = "select * from message where to_user_id=?1 and from_user_id=?2";
	public static final String findContentSendToUniqueUser = "select content from message where from_user_id=?1 and to_user_id=?2";
	public static final String findContentSendFromUniqueUser = "select content from message where to_user_id=?1 and from_user_id=?2";
	public static final String findAllMessagesBetweenTwoUser = "select * from message where from_user_id=?1 and to_user_id=?2 UNION select * from message where to_user_id=?1 and from_user_id=?2";
	public static final String findAllMessagesBetweenOneUserWithOthers = "select * from message where from_user_id=?1 UNION select * from message where to_user_id=?2";

	@Query(value = sendMessage, nativeQuery = true)
	public String sendMessage(Integer msg_id);

	@Query(value = receiveMessage, nativeQuery = true)
	public String receiveMessage(Integer msg_id);

	@Query(value = findAllMessagesSendToUniqueUser, nativeQuery = true)
	public List<Message> findAllMessagesSendToUniqueUser(Integer from_user_id, Integer to_user_id);

	@Query(value = findAllMessagesSendFromUniqueUser, nativeQuery = true)
	public List<Message> findAllMessagesSendFromUniqueUser(Integer to_user_id, Integer from_user_id);

	@Query(value = findContentSendToUniqueUser, nativeQuery = true)
	public List<String> findContentSendToUniqueUser(Integer from_user_id, Integer to_user_id);

	@Query(value = findContentSendFromUniqueUser, nativeQuery = true)
	public List<String> findContentSendFromUniqueUser(Integer to_user_id, Integer from_user_id);

	@Query(value = findAllMessagesBetweenTwoUser, nativeQuery = true)
	public List<Message> findAllMessagesBetweenTwoUser(Integer from_user_id, Integer to_user_id);

	@Query(value = findAllMessagesBetweenOneUserWithOthers, nativeQuery = true)
	public List<Message> findAllMessagesBetweenOneUserWithOthers(Integer from_user_id, Integer to_user_id);

}

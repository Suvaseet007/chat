package com.yoctotta.train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class ChatController {
	@Autowired
	private UserRepo repo;
	@Autowired
	private MessageRepo messagerepo;
	@Autowired
	private SecurityQuestionRepo questionrepo;

	@Autowired
	private final ImageUploadRepo imageRepo;

	@Autowired
	public ChatController(ImageUploadRepo imageRepo) {
		this.imageRepo = imageRepo;
	}

	@GetMapping(value = "/registration")
	public String saveUser(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("emailID") String emailID, @RequestParam("mob") String mob, @RequestParam("dp") String dp) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmailID(emailID);
		user.setMob(mob);
		user.setDp(dp);
		repo.save(user);
		return "done";
	}

	@GetMapping(value = "/addMessageDetails")
	public String saveMessage(@RequestParam("content") String content, @RequestParam("dateandtime") String dateandtime,
			@RequestParam("future_date") String future_date, @RequestParam("delete_in") String delete_in,
			@RequestParam("type") String type, @RequestParam("from_user_id") int from_user_id,
			@RequestParam("to_user_id") int to_user_id) throws ParseException {
		Message message = new Message();
		message.setContent(content);
		message.setDateandtime(new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(dateandtime).getTime()));
		message.setFuture_date(new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(future_date).getTime()));
		message.setDelete_in(delete_in);
		message.setType(type);
		message.setFrom_user_id(from_user_id);
		message.setTo_user_id(to_user_id);
		messagerepo.save(message);
		return "save";
	}

	@GetMapping(value = "/addQuestions")
	public String saveQuestions(@RequestParam("questions") String questions) {
		SecurityQuestions securityQuestions = new SecurityQuestions();
		securityQuestions.setQuestions(questions);
		questionrepo.save(securityQuestions);
		return "save";
	}

	@GetMapping(value = "/login")
	public String getUsernameAndPassword(@RequestParam("user_name") String userName,
			@RequestParam("password") String password) {
		return repo.login(userName, password);

	}

	@GetMapping(value = "/all")
	public Iterable<User> findAll() {
		return repo.findAll();
	}

	@GetMapping(value = "id/{user_id}")
	public User findByID(@PathVariable("user_id") Integer user_id) {
		return repo.findOne(user_id);
	}

	@GetMapping(value = "/allmessage")
	public Iterable<Message> getAll() {
		return messagerepo.findAll();
	}

	@GetMapping(value = "user_id/{to_user_id}")
	public long findByUserID(@PathVariable("to_user_id") Integer to_user_id) {
		return messagerepo.count();
	}

	@GetMapping(value = "/sendMessage")
	public String findByTo_UserID(@RequestParam("msg_id") Integer msg_id) {
		return messagerepo.sendMessage(msg_id);
	}

	@GetMapping(value = "/receiveMessage")
	public String findByFrom_UserID(@RequestParam("msg_id") Integer msg_id) {
		return messagerepo.receiveMessage(msg_id);
	}

	@GetMapping(value = "/findAllMessagesSendToUniqueUser")
	public List<Message> retrieveDataFromAndTo_Messages(@RequestParam("from_user_id") Integer from_user_id,
			@RequestParam("to_user_id") Integer to_user_id) {
		return messagerepo.findAllMessagesSendToUniqueUser(from_user_id, to_user_id);
	}

	@GetMapping(value = "/findAllMessagesSendFromUniqueUser")
	public List<Message> retrieveDataToAndFrom_Messages(@RequestParam("to_user_id") Integer to_user_id,
			@RequestParam("from_user_id") Integer from_user_id) {
		return messagerepo.findAllMessagesSendFromUniqueUser(to_user_id, from_user_id);
	}

	@GetMapping(value = "/findContentSendToUniqueUser")
	public List<String> printFromAndTo_Content(@RequestParam("from_user_id") Integer from_user_id,
			@RequestParam("to_user_id") Integer to_user_id) {
		return messagerepo.findContentSendToUniqueUser(from_user_id, to_user_id);
	}

	@GetMapping(value = "/findContentSendFromUniqueUser")
	public List<String> printToAndFrom_Content(@RequestParam("to_user_id") Integer to_user_id,
			@RequestParam("from_user_id") Integer from_user_id) {
		return messagerepo.findContentSendFromUniqueUser(to_user_id, from_user_id);
	}

	@GetMapping(value = "/findAllMessagesBetweenTwoUser")
	public List<Message> printAllMessagesBetweenTwoUser(@RequestParam("from_user_id") Integer from_user_id,
			@RequestParam("to_user_id") Integer to_user_id) {
		return messagerepo.findAllMessagesBetweenTwoUser(from_user_id, to_user_id);
	}

	@GetMapping(value = "/findAllMessagesBetweenOneUserWithOthers")
	public List<Message> printAllMessagesBetweenOneUserWithOthers(@RequestParam("from_user_id") Integer from_user_id,
			@RequestParam("to_user_id") Integer to_user_id) {
		return messagerepo.findAllMessagesBetweenOneUserWithOthers(from_user_id, to_user_id);
	}

	@GetMapping("/uploadImg")
	public String listUploadedFiles() {
		return "<html><body><div><form method=\"POST\" enctype=\"multipart/form-data\" action=\"/user/uploadImage\"><table><tr><td>File to upload:</td><td><input type=\"file\" name=\"file\" /></td></tr><tr><td>User Id:</td><td><input type=\"number\" name=\"userId\" /></td></tr><tr><td></td><td><input type=\"submit\" value=\"Upload\" /></td></tr></table></form></div></body></html>";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = imageRepo.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/uploadImage")
	public String imageFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId) {
		User user = repo.findOne(userId);
		if (user == null) {
			return "No user for id : " + userId;
		}
		user.setDp(imageRepo.store(file));
		repo.save(user);
		return "successfully upload";
	}

}
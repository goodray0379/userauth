// package com.devmin.userauth;

// import static org.junit.Assert.assertThat;

// import java.util.List;

// import com.devmin.userauth.domain.UserVo;
// import com.devmin.userauth.repository.UserRepository;

// import org.junit.After;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// import static org.hamcrest.CoreMatchers.is;
// //import static org.junit.Assert.assertThat;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// class UserauthApplicationTests {

// 	@Autowired
// 	UserRepository userRepository;

// 	@Test
// 	void contextLoads() {
// 		UserVo userVo = new UserVo("", "123123", "허영민", "01054240379");
// 		userRepository.save(userVo);

// 		List<UserVo> userList = userRepository.findAll();

// 		UserVo heoym = userList.get(0);
// 		assertThat(heoym.getName(), is("허영민"));
// 		assertThat(heoym.getContact(), is("01054240379"));
// 	}

// 	@After
// 	public void deleteAll(){
// 		userRepository.deleteAll();
// 	}

// }

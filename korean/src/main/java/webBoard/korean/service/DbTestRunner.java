package webBoard.korean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import webBoard.korean.dto.User;
import webBoard.korean.repository.UserRepository;

@Component // 이 클래스를 bean 으로 등록하여 사용하기 위해 클래스에서 사용
public class DbTestRunner implements CommandLineRunner {
    @Autowired//new userRepository를 DI컨테이너에서 사용
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        /*
        User user = new User();
        user.setName("테스트");
        user.setEmail("test@example.com");
        userRepository.save(user);
        */
        /* 조회 및 출력
        userRepository.findAll().forEach(System.out::println);
        */
    }


}


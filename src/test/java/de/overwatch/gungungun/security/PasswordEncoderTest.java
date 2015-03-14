package de.overwatch.gungungun.security;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class PasswordEncoderTest {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Test
    public void encodePassword()throws Exception{

        String encodedPassword = passwordEncoder.encode("admin");
        System.out.println("encoded = "+encodedPassword);
    }
}

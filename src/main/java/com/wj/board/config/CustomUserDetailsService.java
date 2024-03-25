package com.wj.board.config;

import com.wj.board.entity.UserEntity;
import com.wj.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*--- 사용자 정보를 가오는 클래스
      스프링 시큐리티에 기본적으로 내장되고 실행되는
      인터페이스를 구현하여 Custom 클래스를 만들어 사용 ---*/
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null) throw new UsernameNotFoundException("UsernameNotFoundException");
        return new CustomUserDetails(userEntity);
    }
}

package com.weavus.weavus_bankweb.service.users;

import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.repository.users.UsersInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 1. 이 클래스가 서비스 역할을 하고, UserDetailsService를 구현함을 명시합니다.
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UsersInterface usersInterface; // DB에 접근하기 위한 리포지토리

    /**
     * Spring Security가 로그인 요청을 처리할 때 호출하는 메소드입니다.
     * @param username 로그인 폼에서 사용자가 입력한 아이디(username)
     * @return DB에서 찾은 사용자 정보 (UserDetails 타입)
     * @throws UsernameNotFoundException 사용자를 찾지 못했을 경우 발생하는 예외
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 2. 전달받은 username으로 DB에서 사용자를 찾습니다.
        UsersEntity user = usersInterface.FindUserByUsername(username);

        // 3. 만약 사용자를 찾지 못했다면, Spring Security에 예외를 던져 알립니다.
        if (user == null) {
            throw new UsernameNotFoundException("該当するユーザーが見つかりませんでした: " + username);
        }

        // 4. 사용자를 찾았다면, UserDetails 타입으로 구현된 UsersEntity를 반환합니다.
        // Spring Security는 이 객체의 getPassword() 메소드로 비밀번호를 비교합니다.
        return user;
    }
}

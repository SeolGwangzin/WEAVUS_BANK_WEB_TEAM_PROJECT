package com.weavus.weavus_bankweb.service.users;

import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.repository.users.UsersInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UsersInterface usersInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersInterface.FindUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("該当するユーザーが見つかりませんでした: " + username);
        }

        return user;
    }
}

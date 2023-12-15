package com.example.board.jwt;


import com.example.board.user.User;
import com.example.board.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsImpl getUserDetails(String nickname) {
		User user = userRepository.findByNickname(nickname)
			.orElseThrow(() -> new UsernameNotFoundException("Not Found" +nickname));
		return new UserDetailsImpl(user);
	}

}

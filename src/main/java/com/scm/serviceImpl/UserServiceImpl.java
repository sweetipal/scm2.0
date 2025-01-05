package com.scm.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoleList(List.of(AppConstants.ROLE_USER));
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> updateUser(User updatedUser) {
		User oldSavedUser = userRepository.findById(updatedUser.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		oldSavedUser.setName(updatedUser.getName());
		oldSavedUser.setEmail(updatedUser.getEmail());
		oldSavedUser.setPassword(updatedUser.getPassword());
		oldSavedUser.setAbout(updatedUser.getAbout());
		oldSavedUser.setPhoneNumber(updatedUser.getPhoneNumber());
		oldSavedUser.setProfilePic(updatedUser.getProfilePic());
		oldSavedUser.setEnabled(updatedUser.isEnabled());
		oldSavedUser.setEmailVerified(updatedUser.isEmailVerified());
		oldSavedUser.setPhoneVerified(updatedUser.isPhoneVerified());
		oldSavedUser.setProvider(updatedUser.getProvider());
		oldSavedUser.setProviderUserId(updatedUser.getProviderUserId());
		User user = userRepository.save(oldSavedUser);
		return Optional.ofNullable(user);

	}

	@Override
	public String deleteUser(String id) {
		User oldSavedUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(oldSavedUser);
		return "Deleted Successfully...";
	}

	@Override
	public boolean isUserExist(String userId) {
		User user = userRepository.findById(userId).orElse(null);
		return user != null ? true : false;
	}

	@Override
	public boolean isUserExistByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user != null ? true : false;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> user = userRepository.findAll();
		return user;
	}

}

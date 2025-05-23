package com.ums.demo.service;

import com.ums.demo.dto.UserDTO;
import com.ums.demo.entity.User;
import com.ums.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<UserDTO> getAllUsers(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable).map(this::convertToDTO);
    }

    public UserDTO getUsersByEmail(String email) {
        return convertToDTO(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email)));
    }

    public Page<UserDTO> searchUsers(String firstName, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (firstName != null && email != null) {
            return userRepository.findByFirstNameContainingAndEmailContaining(firstName, email, pageable)
                    .map(this::convertToDTO);
        } else if (firstName != null) {
            return userRepository.findByFirstNameContaining(firstName, pageable)
                    .map(this::convertToDTO);
        } else if (email != null) {
            return userRepository.findByEmailContaining(email, pageable)
                    .map(this::convertToDTO);
        }
        return userRepository.findAll(pageable).map(this::convertToDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = convertToEntity(userDTO);
        return convertToDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (!existingUser.getEmail().equals(userDTO.getEmail()) &&
                userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setEmail(userDTO.getEmail());
        return convertToDTO(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public List<User> getAllUserByFirstName(String firstName) {
        return userRepository.getByFirstName(firstName);
    }

    public List<User> getUsersWithExampleDomain() {
        return userRepository.getUsersWithExampleDomain();
    }
}

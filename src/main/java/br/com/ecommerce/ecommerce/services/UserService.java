package br.com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.ecommerce.dtos.request.NewUserDTO;
import br.com.ecommerce.ecommerce.dtos.response.UserDTO;
import br.com.ecommerce.ecommerce.models.UserEntity;
import br.com.ecommerce.ecommerce.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void saveUser(UserEntity user){
        userRepository.save(user);
    }

    public UserDTO createUser(NewUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        UserEntity user = new UserEntity(userDTO);
        saveUser(user);
        UserDTO returnUser = new UserDTO(user.getName(), user.getEmail());
        return returnUser;
    }

    
    public List<UserDTO> getAllUsers(){
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> returnUsers = users.stream().map(user -> new UserDTO(user.getName(), user.getEmail())).toList();
        return returnUsers;
    }

    public UserDTO getUserById(String userId){
        UserEntity user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        UserDTO returnUser = new UserDTO(user.getName(), user.getEmail());
        return returnUser;
    }
    
      public void deleteUserById(String userId){
        UserEntity user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        deleteUser(user);
    }

    private void deleteUser(UserEntity user){
        userRepository.delete(user);
    }
}

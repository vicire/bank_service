package bankservice.demo.service;

import bankservice.demo.entity.User;
import bankservice.demo.exception.NoSuchEntityException;
import bankservice.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new NoSuchEntityException("There are no user with id " + id));
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.getByPhoneNumber(phoneNumber).orElseThrow(()
                -> new NoSuchEntityException("There are no user with phone number " + phoneNumber));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

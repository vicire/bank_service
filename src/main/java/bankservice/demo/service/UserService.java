package bankservice.demo.service;

import bankservice.demo.entity.User;

public interface UserService {
    void create(User user);

    void update(User user);

    User getById(Long id);

    User getByPhoneNumber(String phoneNumber);

    void delete(Long id);
}

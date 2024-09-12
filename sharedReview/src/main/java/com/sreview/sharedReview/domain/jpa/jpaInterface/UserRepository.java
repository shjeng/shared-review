package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickname);

    List<User> findAll();

    Optional<User> findByEmail(String email); // email로 user를 찾음.

    @Query("SELECT u FROM user_entity u WHERE u.nickname = :nickname")
    List<User> findAdminByNickname(@Param("nickname") String userNickname);

    @Query("SELECT u FROM user_entity u WHERE u.email = :email")
    List<User> findAdminByEmail(@Param("email") String userEmail);

    @Query("SELECT u FROM user_entity u WHERE u.email=:email AND u.password=:password")
    Optional<User> passwordCheck(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Query("UPDATE user_entity u SET u.active = false WHERE u.email=:email")
    void findByActiveEmail(@Param("email") String email);
//
//    public void save(User user) {
//        em.persist(user);
//    }
//
//    public User findOne(Long id) {
//        return em.find(User.class, id);
//    }
//
//    public List<User> findAll() {
//        return em.createQuery("select m from User m", User.class)
//                .getResultList();
//    }
//
//    public List<User> findByNickName(String nickname) {
//        return em.createQuery("select m from User m where m.nickname = :nickname", User.class)
//                .setParameter("nickname", nickname)
//                .getResultList();
//    }

}

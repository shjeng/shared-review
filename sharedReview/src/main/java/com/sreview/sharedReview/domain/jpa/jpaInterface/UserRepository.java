package com.sreview.sharedReview.domain.jpa.jpaInterface;

import com.sreview.sharedReview.domain.board.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickName(String nickname);

    Optional<User> findAll(User user);

//    private final EntityManager em;
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

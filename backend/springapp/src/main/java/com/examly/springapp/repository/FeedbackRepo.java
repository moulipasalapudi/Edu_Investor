package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    @Query(value="SELECT f.*,u.username FROM feedback f JOIN user u ON f.user_id = u.user_id WHERE f.user_id = ?1", nativeQuery = true)
    List<Feedback> getFeedbacksByUserId(Long userId);

    @Query(value="SELECT f.* FROM feedback f JOIN user u ON f.user_id = u.user_id", nativeQuery = true)
    List<Feedback> getFeedbacks();
    // @Query(value="SELECT f.*,u.username FROM feedback f JOIN user u ON f.user_id = u.user_id", nativeQuery = true)
    // List<Feedback> getAllFeedbacks();
    @Query(value="SELECT f.*,f.user_id from feedback", nativeQuery = true)
    List<Feedback> getAllFeedbacks();



    @Query("SELECT f FROM Feedback f JOIN FETCH f.user")
    List<Feedback> getAllFeedbacksWithUsers();

}

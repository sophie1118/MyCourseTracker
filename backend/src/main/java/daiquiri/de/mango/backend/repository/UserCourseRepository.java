package daiquiri.de.mango.backend.repository;

import daiquiri.de.mango.backend.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    List<UserCourse> findByUserId(String userId);


    List<UserCourse> findByUserIdAndStatus(String userId, String status);


    boolean existsByUserIdAndCourseCode(String userId, String courseCode);

    UserCourse findByUserIdAndCourseCode(String userId, String courseCode);



}

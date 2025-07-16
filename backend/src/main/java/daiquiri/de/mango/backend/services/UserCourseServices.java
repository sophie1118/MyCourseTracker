package daiquiri.de.mango.backend.services;


import daiquiri.de.mango.backend.model.Course;
import daiquiri.de.mango.backend.model.UserCourse;
import daiquiri.de.mango.backend.repository.CourseRepository;
import daiquiri.de.mango.backend.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseServices {

    @Autowired
    UserCourseRepository userCourseRepository;

    @Autowired
    CourseRepository courseRepository;


    public String enrollCourse(UserCourse userCourse) {

        if (userCourseRepository.existsByUserIdAndCourseCode(userCourse.getUserId(), userCourse.getCourseCode())) {
            return "Error: El curso ya está registrado para este usuario.";
        }

        Course course = courseRepository.findById(userCourse.getCourseCode()).orElse(null);
        if (course == null) return "Error: El curso no existe.";

        List<String> prereqs = course.getPrerequisites();

        for (String prereqCode : prereqs) {
            UserCourse prereqStatus = userCourseRepository.findByUserIdAndCourseCode(userCourse.getUserId(), prereqCode);
            if (prereqStatus == null || !prereqStatus.isApproved()) {
                return "Error: No ha aprobado el prerequisito: " + prereqCode;
            }
        }


        userCourseRepository.save(userCourse);
        return "Curso registrado correctamente.";
    }

    public List<UserCourse> getUserCourses(String userId) {
        return userCourseRepository.findByUserId(userId);
    }

    public List<UserCourse> getCoursesByStatus(String userId, String status) {
        return userCourseRepository.findByUserIdAndStatus(userId, status);
    }

    public String updateGrade(String userId, String courseCode, double grade) {
        UserCourse userCourse = userCourseRepository.findByUserIdAndCourseCode(userId, courseCode);
        if (userCourse == null) return "Error: No se encontró ese curso para el usuario.";

        userCourse.setGrade(grade);
        userCourse.setApproved(grade >= 67.5);
        userCourse.setStatus("anterior");

        userCourseRepository.save(userCourse);
        return "Nota actualizada correctamente.";
    }




}

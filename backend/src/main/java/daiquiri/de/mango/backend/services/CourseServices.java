package daiquiri.de.mango.backend.services;

import daiquiri.de.mango.backend.model.Course;
import daiquiri.de.mango.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServices {

    @Autowired
    CourseRepository courseRepository;


    public String registerCourse(Course course) {
        if (courseRepository.existsById(course.getCode())) {
            return "Error: El curso ya existe.";
        }

        courseRepository.save(course);
        return "Curso registrado correctamente.";
    }

    public Optional<Course> getCourseByCode(String code) {
        return courseRepository.findById(code);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public String deleteCourse(String code) {
        if (!courseRepository.existsById(code)) {
            return "Error: El curso no existe.";
        }

        courseRepository.deleteById(code);
        return "Curso eliminado correctamente.";
    }

}

package daiquiri.de.mango.backend.controller;

import daiquiri.de.mango.backend.model.Course;
import daiquiri.de.mango.backend.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/courses")
public class CourseController {

    @Autowired
    CourseServices courseServices;


    public ResponseEntity<?> registerCourse(@RequestBody Course course) {
        String result = courseServices.registerCourse(course);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable String code) {
        Optional<Course> userOp = this.courseServices.getCourseByCode(code);

        if (userOp.isPresent()){
            return ResponseEntity.ok(userOp);

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El curso no fue encontrado.");

    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseServices.getAllCourses());
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteCourse(@PathVariable String code) {
        String result = courseServices.deleteCourse(code);
        if (result.startsWith("Error")) {
            return ResponseEntity.status(404).body(result);
        }
        return ResponseEntity.ok(result);
    }

}

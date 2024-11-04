package com.example.jtleon.courserating

class CourseRating {
    var courseName = ""
    var instructorName = ""
    var courseType = ""
    var rating = 0

    override fun toString(): String {
        return """
            courseName = $courseName
            instructorName = $instructorName
            courseType = $courseType
            rating = $rating
        """
    }
}
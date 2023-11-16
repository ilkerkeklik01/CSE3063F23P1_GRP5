package Domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TranscriptTest {

    @Test
    void testAddGradeAndUpdateTotalCredits() {

        Course course = new Course(DOLDUR);

        Department.getInstance().getAllCourses().add(KURS KODU, course);

        Transcript transcript = new Transcript();

        transcript.addGrade(KURS KODU, 85);

        List<Grade> grades = transcript.getGrades();

        assertEquals(3, transcript.getCompletedCredits());

        transcript.addGrade(KURS KODU, 45);

        assertEquals(3, transcript.getCompletedCredits());
    }

    @Test
    void testPrintTakenCoursesStatus() {

        Transcript transcript = new Transcript();

        transcript.addGrade(KURS KODU, 85);

        transcript.addGrade(KURS KODU, 45);

        // Buraya okuması için inputları birşey lazım

        transcript.printTakenCoursesStatus();

        //Sıradaki öğrenci için sistemin kendini geriye atması lazım bir şekilde resetlemek lazım

        // Verilen output istenen output a uyuyor mu ona bakılması lazım
    }
}
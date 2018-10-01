package com.msp.mspshoubraapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LecturesTableDao {


    @Insert
    long insertGroups(GroupDaysEntity groupDaysEntity);

    @Insert
    long insertLecture(DayLecturesEntity dayLecturesEntity);

    @Insert
    long insertSection(SectionsEntity sectionsEntity);

    @Query("SELECT dayLectures.id," +
            "dayLectures.groupId," +
            "dayLectures.lectureNum," +
            "dayLectures.startTime," +
            "dayLectures.endTime FROM dayLectures,groupDays WHERE groupDays.id = dayLectures.groupId AND " +
            "groupDays.day = :day AND " +
            "groupDays.groupNum = :groupNum ORDER BY dayLectures.lectureNum ASC")
    LiveData<List<DayLecturesEntity>> loadAllLectures(String groupNum, String day);

    @Query("SELECT sections.id," +
            "sections.dayId," +
            "sections.secNum," +
            "sections.subjectName," +
            "sections.instructor," +
            "sections.place," +
            "sections.lectureNum FROM dayLectures,sections WHERE sections.lectureNum = :lecNum AND " +
            "sections.dayId = dayLectures.id ORDER BY sections.secNum ASC")
    List<SectionsEntity> loadAllSections(int lecNum/*,long dayId*/);

    @Query("DELETE FROM sections")
    void deleteAllSections();

    @Query("DELETE FROM dayLectures")
    void deleteAllLectures();

    @Query("DELETE FROM groupDays")
    void deleteAllGroups();

}
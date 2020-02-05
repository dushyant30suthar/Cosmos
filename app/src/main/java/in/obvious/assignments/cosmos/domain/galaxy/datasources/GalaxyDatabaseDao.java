package in.obvious.assignments.cosmos.domain.galaxy.datasources;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

@Dao
public interface GalaxyDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<Galaxy> galaxyList);

    @Query("SELECT * from galaxy")
    List<Galaxy> getGalaxyList();

    @Query("SELECT * from galaxy WHERE galaxyId = :id")
    Galaxy getGalaxy(String id);

    @Query("DELETE FROM galaxy")
    void removeAllGalaxies();

    @Query("UPDATE galaxy SET isBookmarked = :bookmarkStatus WHERE galaxyId == :id")
    void bookmarkGalaxy(int id, boolean bookmarkStatus);
}

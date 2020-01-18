package in.obvious.assignments.cosmos.framework.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dagger.Module;
import dagger.Provides;
import in.obvious.assignments.cosmos.domain.galaxy.datasources.GalaxyDatabaseDao;
import in.obvious.assignments.cosmos.domain.galaxy.models.Galaxy;

@Module
public class DatabaseModule {

    Application context;

    public DatabaseModule(Application context) {
        this.context = context;
    }

    public GalaxyDatabase getGalaxyDatabase() {
        return Room.databaseBuilder(context,
                GalaxyDatabase.class, "galaxy_database").build();
    }

    @Provides
    public GalaxyDatabaseDao getGalaxyDatabaseDao() {
        return getGalaxyDatabase().galaxyDatabaseDao();
    }

    @Database(entities = {Galaxy.class}, version = 1, exportSchema = false)
    public static abstract class GalaxyDatabase extends RoomDatabase {
        public abstract GalaxyDatabaseDao galaxyDatabaseDao();
    }
}
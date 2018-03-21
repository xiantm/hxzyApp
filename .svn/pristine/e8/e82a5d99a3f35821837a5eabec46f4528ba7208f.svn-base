package hxzy.ptb.hxzyappkit.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import hxzy.ptb.hxzyappkit.domain.LoginHistory;
import hxzy.ptb.hxzyappkit.domain.User;

//数据库配置
@Database(entities = {LoginHistory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //声明需要用到的Dao
    public abstract LoginHistoryDao loginHistoryDao();
}
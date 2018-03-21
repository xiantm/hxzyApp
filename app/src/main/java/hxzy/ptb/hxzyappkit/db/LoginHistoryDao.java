package hxzy.ptb.hxzyappkit.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import hxzy.ptb.hxzyappkit.domain.LoginHistory;

//数据库操作定义在这里
@Dao
public interface LoginHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLoginHistory(LoginHistory... loginHistories);

    @Query("SELECT * FROM LoginHistory")
    List<LoginHistory> getLoginHistory();


}

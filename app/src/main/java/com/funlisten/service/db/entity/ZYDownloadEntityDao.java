package com.funlisten.service.db.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.funlisten.business.download.model.bean.ZYDownloadEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ZYDOWNLOAD_ENTITY".
*/
public class ZYDownloadEntityDao extends AbstractDao<ZYDownloadEntity, String> {

    public static final String TABLENAME = "ZYDOWNLOAD_ENTITY";

    /**
     * Properties of entity ZYDownloadEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property AudioId = new Property(1, int.class, "audioId", false, "AUDIO_ID");
        public final static Property AlbumId = new Property(2, int.class, "albumId", false, "ALBUM_ID");
        public final static Property AlbumName = new Property(3, String.class, "albumName", false, "ALBUM_NAME");
        public final static Property AlbumCoverUrl = new Property(4, String.class, "albumCoverUrl", false, "ALBUM_COVER_URL");
        public final static Property AlbumPublisher = new Property(5, String.class, "albumPublisher", false, "ALBUM_PUBLISHER");
        public final static Property AlbumDownloadedSize = new Property(6, int.class, "albumDownloadedSize", false, "ALBUM_DOWNLOADED_SIZE");
        public final static Property AudioUpatedCount = new Property(7, int.class, "audioUpatedCount", false, "AUDIO_UPATED_COUNT");
        public final static Property AudioDowloadedCount = new Property(8, int.class, "audioDowloadedCount", false, "AUDIO_DOWLOADED_COUNT");
        public final static Property AudioCount = new Property(9, int.class, "audioCount", false, "AUDIO_COUNT");
        public final static Property AudioName = new Property(10, String.class, "audioName", false, "AUDIO_NAME");
        public final static Property AudioCreateTime = new Property(11, String.class, "audioCreateTime", false, "AUDIO_CREATE_TIME");
        public final static Property AudioSort = new Property(12, int.class, "audioSort", false, "AUDIO_SORT");
        public final static Property Total = new Property(13, long.class, "total", false, "TOTAL");
        public final static Property Current = new Property(14, long.class, "current", false, "CURRENT");
        public final static Property Url = new Property(15, String.class, "url", false, "URL");
        public final static Property SavePath = new Property(16, String.class, "savePath", false, "SAVE_PATH");
        public final static Property StateValue = new Property(17, int.class, "stateValue", false, "STATE_VALUE");
    }


    public ZYDownloadEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ZYDownloadEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ZYDOWNLOAD_ENTITY\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"AUDIO_ID\" INTEGER NOT NULL ," + // 1: audioId
                "\"ALBUM_ID\" INTEGER NOT NULL ," + // 2: albumId
                "\"ALBUM_NAME\" TEXT," + // 3: albumName
                "\"ALBUM_COVER_URL\" TEXT," + // 4: albumCoverUrl
                "\"ALBUM_PUBLISHER\" TEXT," + // 5: albumPublisher
                "\"ALBUM_DOWNLOADED_SIZE\" INTEGER NOT NULL ," + // 6: albumDownloadedSize
                "\"AUDIO_UPATED_COUNT\" INTEGER NOT NULL ," + // 7: audioUpatedCount
                "\"AUDIO_DOWLOADED_COUNT\" INTEGER NOT NULL ," + // 8: audioDowloadedCount
                "\"AUDIO_COUNT\" INTEGER NOT NULL ," + // 9: audioCount
                "\"AUDIO_NAME\" TEXT," + // 10: audioName
                "\"AUDIO_CREATE_TIME\" TEXT," + // 11: audioCreateTime
                "\"AUDIO_SORT\" INTEGER NOT NULL ," + // 12: audioSort
                "\"TOTAL\" INTEGER NOT NULL ," + // 13: total
                "\"CURRENT\" INTEGER NOT NULL ," + // 14: current
                "\"URL\" TEXT," + // 15: url
                "\"SAVE_PATH\" TEXT," + // 16: savePath
                "\"STATE_VALUE\" INTEGER NOT NULL );"); // 17: stateValue
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ZYDOWNLOAD_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ZYDownloadEntity entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindLong(2, entity.getAudioId());
        stmt.bindLong(3, entity.getAlbumId());
 
        String albumName = entity.getAlbumName();
        if (albumName != null) {
            stmt.bindString(4, albumName);
        }
 
        String albumCoverUrl = entity.getAlbumCoverUrl();
        if (albumCoverUrl != null) {
            stmt.bindString(5, albumCoverUrl);
        }
 
        String albumPublisher = entity.getAlbumPublisher();
        if (albumPublisher != null) {
            stmt.bindString(6, albumPublisher);
        }
        stmt.bindLong(7, entity.getAlbumDownloadedSize());
        stmt.bindLong(8, entity.getAudioUpatedCount());
        stmt.bindLong(9, entity.getAudioDowloadedCount());
        stmt.bindLong(10, entity.getAudioCount());
 
        String audioName = entity.getAudioName();
        if (audioName != null) {
            stmt.bindString(11, audioName);
        }
 
        String audioCreateTime = entity.getAudioCreateTime();
        if (audioCreateTime != null) {
            stmt.bindString(12, audioCreateTime);
        }
        stmt.bindLong(13, entity.getAudioSort());
        stmt.bindLong(14, entity.getTotal());
        stmt.bindLong(15, entity.getCurrent());
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(16, url);
        }
 
        String savePath = entity.getSavePath();
        if (savePath != null) {
            stmt.bindString(17, savePath);
        }
        stmt.bindLong(18, entity.getStateValue());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ZYDownloadEntity entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
        stmt.bindLong(2, entity.getAudioId());
        stmt.bindLong(3, entity.getAlbumId());
 
        String albumName = entity.getAlbumName();
        if (albumName != null) {
            stmt.bindString(4, albumName);
        }
 
        String albumCoverUrl = entity.getAlbumCoverUrl();
        if (albumCoverUrl != null) {
            stmt.bindString(5, albumCoverUrl);
        }
 
        String albumPublisher = entity.getAlbumPublisher();
        if (albumPublisher != null) {
            stmt.bindString(6, albumPublisher);
        }
        stmt.bindLong(7, entity.getAlbumDownloadedSize());
        stmt.bindLong(8, entity.getAudioUpatedCount());
        stmt.bindLong(9, entity.getAudioDowloadedCount());
        stmt.bindLong(10, entity.getAudioCount());
 
        String audioName = entity.getAudioName();
        if (audioName != null) {
            stmt.bindString(11, audioName);
        }
 
        String audioCreateTime = entity.getAudioCreateTime();
        if (audioCreateTime != null) {
            stmt.bindString(12, audioCreateTime);
        }
        stmt.bindLong(13, entity.getAudioSort());
        stmt.bindLong(14, entity.getTotal());
        stmt.bindLong(15, entity.getCurrent());
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(16, url);
        }
 
        String savePath = entity.getSavePath();
        if (savePath != null) {
            stmt.bindString(17, savePath);
        }
        stmt.bindLong(18, entity.getStateValue());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ZYDownloadEntity readEntity(Cursor cursor, int offset) {
        ZYDownloadEntity entity = new ZYDownloadEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.getInt(offset + 1), // audioId
            cursor.getInt(offset + 2), // albumId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // albumName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // albumCoverUrl
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // albumPublisher
            cursor.getInt(offset + 6), // albumDownloadedSize
            cursor.getInt(offset + 7), // audioUpatedCount
            cursor.getInt(offset + 8), // audioDowloadedCount
            cursor.getInt(offset + 9), // audioCount
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // audioName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // audioCreateTime
            cursor.getInt(offset + 12), // audioSort
            cursor.getLong(offset + 13), // total
            cursor.getLong(offset + 14), // current
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // url
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // savePath
            cursor.getInt(offset + 17) // stateValue
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ZYDownloadEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setAudioId(cursor.getInt(offset + 1));
        entity.setAlbumId(cursor.getInt(offset + 2));
        entity.setAlbumName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAlbumCoverUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAlbumPublisher(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAlbumDownloadedSize(cursor.getInt(offset + 6));
        entity.setAudioUpatedCount(cursor.getInt(offset + 7));
        entity.setAudioDowloadedCount(cursor.getInt(offset + 8));
        entity.setAudioCount(cursor.getInt(offset + 9));
        entity.setAudioName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAudioCreateTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setAudioSort(cursor.getInt(offset + 12));
        entity.setTotal(cursor.getLong(offset + 13));
        entity.setCurrent(cursor.getLong(offset + 14));
        entity.setUrl(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setSavePath(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setStateValue(cursor.getInt(offset + 17));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ZYDownloadEntity entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(ZYDownloadEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ZYDownloadEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

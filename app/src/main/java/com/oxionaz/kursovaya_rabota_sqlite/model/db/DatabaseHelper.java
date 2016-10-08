package com.oxionaz.kursovaya_rabota_sqlite.model.db;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "military.db";
    private static final int SCHEMA = 1;
    //tables
    public static final String TABLE_MILITARY_DISTRICT = "military_district";
    public static final String TABLE_DISLOCATION_PLACE = "dislocation_place";
    public static final String TABLE_ARMY = "army";
    public static final String TABLE_COMMUNITY = "community";
    public static final String TABLE_MILITARY_UNIT = "military_unit";
    public static final String TABLE_COMPANY = "company";
    public static final String TABLE_PLATOON = "platoon";
    public static final String TABLE_DEPARTMENT = "department";
    public static final String TABLE_RANK = "rank";
    public static final String TABLE_MILITARY = "military";
    public static final String TABLE_SPECIALITY = "speciality";
    public static final String TABLE_EQUIPMENT_CATEGORY = "equipment_category";
    public static final String TABLE_EQUIPMENT = "equipment";
    public static final String TABLE_WEAPONRY_CATEGORY = "weaponry_category";
    public static final String TABLE_WEAPONRY = "weaponry";
    public static final String TABLE_BUILDING = "building";
    public static final String TABLE_SUBDIVISION_DISLOCATION = "subdivision_dislocation";
    public static final String TABLE_SPECIALITY_ACCOUNTING = "speciality_accounting";
    public static final String TABLE_EQUIPMENT_ACCOUNTING = "equipment_accounting";
    public static final String TABLE_WEAPONRY_ACCOUNTING = "weaponry_accounting";
    //ID columns
    public static final String COLUMN_MILITARY_DISTRICT_ID = "_id_md";
    public static final String COLUMN_DISLOCATION_PLACE_ID = "_id_dp";
    public static final String COLUMN_ARMY_ID = "_id_army";
    public static final String COLUMN_COMMUNITY_ID = "_id_comm";
    public static final String COLUMN_COMPANY_ID = "_id_cmp";
    public static final String COLUMN_PLATOON_ID = "_id_pln";
    public static final String COLUMN_DEPARTMENT_ID = "_id_dpm";
    public static final String COLUMN_MILITARY_UNIT_ID = "_id_mu";
    public static final String COLUMN_RANK_ID = "_id_rnk";
    public static final String COLUMN_MILITARY_ID = "_id_mlt";
    public static final String COLUMN_SPECIALITY_ID = "_id_spc";
    public static final String COLUMN_EQUIPMENT_CATEGORY_ID = "_id_equ_c";
    public static final String COLUMN_EQUIPMENT_ID = "_id_equ";
    public static final String COLUMN_WEAPONRY_CATEGORY_ID = "_id_wpn_c";
    public static final String COLUMN_WEAPONRY_ID = "_id_wpn";
    public static final String COLUMN_BUILDING_ID = "_id_blg";
    public static final String COLUMN_SUBDIVISION_ID = "_id_subdivision";
    public static final String COLUMN_ACCOUNTING_ID = "_id_accounting";
    //overall columns
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_MILITARY_FIO = "fio";
    public static final String COLUMN_ATT = "att";
    public static final String COLUMN_SURRENDER = "surrender";
    public static final String COLUMN_QUALIFICATION = "qualification";
    public static final String COLUMN_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating military_district table
        db.execSQL("CREATE TABLE " + TABLE_MILITARY_DISTRICT + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE, "
                + COLUMN_COUNTRY + " TEXT NOT NULL UNIQUE);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_MILITARY_DISTRICT + " VALUES " +
                "('БВО','Белорусский военный округ', 'Беларусь')," +
                "('РВО','Российский военный округ', 'Россия')" +
                ";");

        //creating dislocation_place table
        db.execSQL("CREATE TABLE " + TABLE_DISLOCATION_PLACE + "("
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL, "
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_DISTRICT_ID +") REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_DISLOCATION_PLACE + " VALUES " +
                "('Б-МД-000','БВО', 'Минск')," +
                "('Б-МД-001','БВО', 'Брест')," +
                "('Б-МД-002','БВО', 'Гомель')," +
                "('Б-МД-003','БВО', 'Могилев')," +
                "('Б-МД-004','БВО', 'Гродно')," +
                "('Б-МД-005','БВО', 'Витебск')," +
                "('Б-МД-006','БВО', 'Осиповичи')," +
                "('Б-МД-007','БВО', 'Печи')" +
                ";");

        //creating rank table
        db.execSQL("CREATE TABLE " + TABLE_RANK + "("
                + COLUMN_TYPE + " TEXT NOT NULL, "
                + COLUMN_RANK_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_RANK + " VALUES "
                + "('Офицерский состав','ГЕН', 'Генерал'),"
                + "('Офицерский состав','ПОЛ', 'Полковник'),"
                + "('Офицерский состав','ПДП', 'Подполковник'),"
                + "('Офицерский состав','МЙР', 'Майор'),"
                + "('Офицерский состав','КАП', 'Капитан'),"
                + "('Офицерский состав','ЛНТ', 'Лейтенант'),"
                + "('Рядовой состав','РЯД', 'Рядовой'),"
                + "('Сержантский состав','СТР', 'Старшина'),"
                + "('Сержантский состав','СРЖ', 'Сержант'),"
                + "('Сержантский состав','ПРК', 'Прапорщик'),"
                + "('Сержантский состав','ЕФР', 'Ефрейтор')"
                + ";");

        //creating military table
        db.execSQL("CREATE TABLE " + TABLE_MILITARY + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_RANK_ID + " TEXT REFERENCES "+ TABLE_RANK +" ("+ COLUMN_RANK_ID +") ON UPDATE CASCADE ON DELETE SET NULL,"
                + COLUMN_MILITARY_ID + " TEXT NOT NULL UNIQUE,"
                + COLUMN_MILITARY_FIO + " TEXT NOT NULL,"
                + COLUMN_ATT + " TEXT,"
                + "PRIMARY KEY(" + COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + "));");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_MILITARY + " VALUES "
                + "('БВО','ГЕН','Б-000','Врублевский Илья Андреевич','Дата окончания академии: 06.24.2014'),"
                + "('БВО','КАП','Б-001','Сидоров Петр Иванович','Дата окончания академии: 03.23.2010'),"
                + "('БВО','ЛНТ','Б-002','Карпук Григорий Олегович','Дата окончания академии: 03.23.2010'),"
                + "('БВО','ЛНТ','Б-003','Ефстафьевич Сегрей Иванович','Дата окончания училища: 03.23.2010'),"
                + "('РВО','КАП','Р-000','Петров Анатолий Олегович','Дата окончания училища: 03.23.2013'),"
                + "('БВО','ГЕН','Б-005','Кравченко Егор Артемьевич','Дата окончания академии: 03.23.2000'),"
                + "('БВО','КАП','Б-006','Весницов Аркадий Семенович','Дата окончания академии: 03.23.2008'),"
                + "('БВО','КАП','Б-007','Антилевский Андрей Игоревич','Дата окончания училища: 07.01.2012'),"
                + "('БВО','МЙР','Б-008','Кузнецов Валерий Генадьевич','Дата окончания академии: 02.10.2003'),"
                + "('БВО','ГЕН','Б-009','Плюшкин Григорий Олегович','Дата окончания академии: 03.23.2010'),"
                + "('БВО','ПДП','Б-010','Климович Сегрей Иванович','Дата окончания училища: 03.23.2010'),"
                + "('БВО','ПДП','Б-011','Сычев Анатолий Олегович','Дата окончания училища: 03.23.2013'),"
                + "('БВО','ПДП','Б-012','Герасимов Егор Артемьевич','Дата окончания академии: 03.23.2000'),"
                + "('БВО','ПДП','Б-013','Свистунов Аркадий Семенович','Дата окончания академии: 03.23.2008'),"
                + "('БВО','ГЕН','Б-014','Кравчук Андрей Андреевич','Дата окончания академии: 06.24.2014'),"
                + "('БВО','КАП','Б-015','Весарионов Петр Иванович','Дата окончания академии: 03.23.2010'),"
                + "('БВО','ЛНТ','Б-016','Грибоедов Григорий Олегович','Дата окончания академии: 03.23.2010'),"
                + "('БВО','ПДП','Б-017','Козлов Сегрей Иванович','Дата окончания училища: 03.23.2010'),"
                + "('БВО','ПДП','Б-018','Власов Анатолий Олегович','Дата окончания училища: 03.23.2013'),"
                + "('БВО','ПДП','Б-019','Гриб Егор Артемьевич','Дата окончания академии: 03.23.2000'),"
                + "('БВО','ПДП','Б-020','Василевский Аркадий Семенович','Дата окончания академии: 03.23.2008'),"
                + "('БВО','ЛНТ','Б-021','Асташенко Андрей Игоревич','Дата окончания училища: 07.01.2012'),"
                + "('БВО','МЙР','Б-022','Понтелейчук Валерий Генадьевич','Дата окончания академии: 02.10.2003'),"
                + "('БВО','ГЕН','Б-023','Плаксина Андрей Игоревич','Дата окончания училища: 07.01.2012'),"
                + "('БВО','МЙР','Б-024','Стасилевич Валерий Генадьевич','Дата окончания академии: 02.10.2003'),"
                + "('БВО','ЛНТ','Б-025','Азаренко Александр Семенович','Дата окончания академии: 01.08.2002'),"
                + "('БВО','ПДП','Б-026','27-военный фио','Дата окончания академии: 03.23.2000'),"
                + "('БВО','ПДП','Б-027','28-военный фио','Дата окончания академии: 03.23.2008'),"
                + "('БВО','ГЕН','Б-028','29-военный фио','Дата окончания академии: 06.24.2014'),"
                + "('БВО','КАП','Б-029','30-военный фио','Дата окончания академии: 03.23.2010'),"
                + "('БВО','КАП','Б-030','31-военный фио','Дата окончания академии: 03.23.2010'),"
                + "('БВО','ПДП','Б-031','32-военный фио','Дата окончания училища: 03.23.2010'),"
                + "('БВО','ПДП','Б-032','33-военный фио','Дата окончания училища: 03.23.2013'),"
                + "('БВО','ПДП','Б-033','34-военный фио','Дата окончания академии: 03.23.2000'),"
                + "('БВО','ПДП','Б-034','35-военный фио','Дата окончания академии: 03.23.2008'),"
                + "('БВО','КАП','Б-035','36-военный фио','Дата окончания училища: 07.01.2012'),"
                + "('БВО','МЙР','Б-036','37-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-037','38-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-038','39-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-039','40-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-040','41-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-041','42-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-042','43-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-043','44-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-044','45-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-045','46-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-046','47-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-047','48-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-048','49-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-049','50-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','КАП','Б-050','51-военный фио','Дата окончания академии: 03.23.2010'),"
                + "('БВО','КАП','Б-051','52-военный фио','Дата окончания академии: 03.23.2010'),"
                + "('БВО','ПДП','Б-052','53-военный фио','Дата окончания училища: 03.23.2010'),"
                + "('БВО','ПДП','Б-053','54-военный фио','Дата окончания училища: 03.23.2013'),"
                + "('БВО','ПДП','Б-054','55-военный фио','Дата окончания академии: 03.23.2000'),"
                + "('БВО','ПДП','Б-055','56-военный фио','Дата окончания академии: 03.23.2008'),"
                + "('БВО','КАП','Б-056','57-военный фио','Дата окончания училища: 07.01.2012'),"
                + "('БВО','МЙР','Б-057','58-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-058','59-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-059','60-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-060','61-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','МЙР','Б-061','62-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-062','63-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-063','64-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-064','65-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-065','66-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-066','67-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-067','68-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-068','69-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-069','70-военный фио','Дата окончания академии: 02.10.2003'),"
                + "('БВО','РЯД','Б-070','71-военный фио','Дата окончания академии: 02.10.2003')"
                + ";");

        //creating army table
        db.execSQL("CREATE TABLE " + TABLE_ARMY + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_ID + " TEXT UNIQUE, "
                + COLUMN_MILITARY_FIO + " TEXT,"
                + COLUMN_ARMY_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE,"
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_SURRENDER + " INTEGER NOT NULL DEFAULT 6,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") REFERENCES "+ TABLE_MILITARY +" ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") ON UPDATE CASCADE ON DELETE SET NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_ARMY + "(_id_md,_id_mlt,fio,_id_army,name) VALUES "
                + "('БВО','Б-000','Врублевский Илья Андреевич','Б-А-00','1-я танковая армия'),"
                + "('БВО','Б-001','Сидоров Петр Иванович','Б-А-01','2-я гвардейская танковая армия'),"
                + "('БВО','Б-002','Карпук Григорий Олегович','Б-А-02','3-я общевойсковая армия'),"
                + "('БВО','Б-003','Ефстафьевич Сегрей Иванович','Б-А-03','4-я воздушная армия'),"
                + "('РВО','Р-000','Петров Анатолий Олегович','Р-А-00','Первая российская армия')"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_md_army_insert BEFORE INSERT ON " + TABLE_ARMY
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_army_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_md_army_update BEFORE UPDATE ON " + TABLE_ARMY
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "AND NOT EXISTS(SELECT _id_mlt FROM army WHERE _id_mlt = NEW._id_mlt AND _id_army = NEW._id_army) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_army_update'); " +
                "END;");

        //creating community table
        db.execSQL("CREATE TABLE " + TABLE_COMMUNITY + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_ARMY_ID + " TEXT REFERENCES "+ TABLE_ARMY +" ("+ COLUMN_ARMY_ID +") ON UPDATE CASCADE ON DELETE SET NULL,"
                + COLUMN_MILITARY_ID + " TEXT UNIQUE,"
                + COLUMN_MILITARY_FIO + " TEXT,"
                + COLUMN_TYPE + " TEXT NOT NULL,"
                + COLUMN_COMMUNITY_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE,"
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_SURRENDER + " INTEGER NOT NULL DEFAULT 5,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") REFERENCES "+ TABLE_MILITARY +" ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") ON UPDATE CASCADE ON DELETE SET NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_COMMUNITY + "(_id_md,_id_army,_id_mlt,fio,type,_id_comm,name) VALUES "
                + "('БВО','Б-А-00','Б-005','Кравченко Егор Артемьевич','Дивизия','Б-О-Д00','1-я гвардейская танковая Краснознамённая дивизия'),"
                + "('БВО','Б-А-00','Б-006','Весницов Аркадий Семенович','Дивизия','Б-О-Д01','2-я танковая Знаменская дивизия'),"
                + "('БВО','Б-А-00','Б-007','Антилевский Андрей Игоревич','Дивизия','Б-О-Д02','3-я танковая ордена Ленина дивизия'),"
                + "('БВО','Б-А-01','Б-008','Кузнецов Валерий Генадьевич','Дивизия','Б-О-Д03','4-я танковая ордена Суворова дивизия '),"
                + "('БВО','Б-А-01','Б-009','Плюшкин Григорий Олегович','Дивизия','Б-О-Д04','5-я танковая Краснознамённая дивизия'),"
                + "('БВО','Б-А-02','Б-010','Климович Сегрей Иванович','Дивизия','Б-О-Д05','6-я пехотная дивизия'),"
                + "('БВО','Б-А-02','Б-011','Сычев Анатолий Олегович','Дивизия','Б-О-Д06','7-я стрелковая дивизия'),"
                + "('БВО','Б-А-02','Б-012','Герасимов Егор Артемьевич','Дивизия','Б-О-Д07','8-я гвардейская мотострелковая ордена Суворова дивизия'),"
                + "('БВО','Б-А-02','Б-013','Свистунов Аркадий Семенович','Дивизия','Б-О-Д08','9-я танковая дивизия'),"
                + "('БВО','Б-А-03','Б-014','Кравчук Андрей Андреевич','Дивизия','Б-О-Д09','10-я авиационная дивизия'),"
                + "('БВО','Б-А-00','Б-015','Весарионов Петр Иванович','Корпус','Б-ОК-00','1-й корпус ПВО'),"
                + "('БВО','Б-А-01','Б-016','Грибоедов Григорий Олегович','Корпус','Б-ОК-01','2-й армейский корпус'),"
                + "('БВО','Б-А-01','Б-017','Козлов Сегрей Иванович','Корпус','Б-ОК-02','3-й стрелковый корпус'),"
                + "('БВО','Б-А-02','Б-018','Власов Анатолий Олегович','Корпус','Б-ОК-03','4-й мотостредковый корпус'),"
                + "('БВО','Б-А-02','Б-019','Гриб Егор Артемьевич','Корпус','Б-ОК-04','5-й пехотный корпус'),"
                + "('БВО','Б-А-02','Б-020','Василевский Аркадий Семенович','Корпус','Б-ОК-05','6-й стрелковый корпус'),"
                + "('БВО','Б-А-02','Б-021','Асташенко Андрей Игоревич','Корпус','Б-ОК-06','7-й армейский корпус'),"
                + "('БВО','Б-А-03','Б-022','Понтелейчук Валерий Генадьевич','Корпус','Б-ОК-07','8-й ракетный корпус'),"
                + "('БВО','Б-А-03','Б-023','Плаксина Андрей Игоревич','Корпус','Б-ОК-08','9-й корпус ПВО'),"
                + "('БВО','Б-А-00','Б-024','Стасилевич Валерий Генадьевич','Бригада','Б-ОБ-00','1-я зенитно-артиллерийская бригада'),"
                + "('БВО','Б-А-01','Б-025','Азаренко Александр Семенович','Бригада','Б-ОБ-01','2-я отдельная бригада'),"
                + "('БВО','Б-А-02','Б-026','27-военный фио','Бригада','Б-ОБ-02','3-я инженерная бригада'),"
                + "('БВО','Б-А-02','Б-027','28-военный фио','Бригада','Б-ОБ-03','4-я радиотехническая бригада'),"
                + "('БВО','Б-А-03','Б-028','29-военный фио','Бригада','Б-ОБ-04','5-я зенитная ракетная бригада')"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_md_comm_insert BEFORE INSERT ON " + TABLE_COMMUNITY
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_comm_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_md_comm_update BEFORE UPDATE ON " + TABLE_COMMUNITY
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "AND NOT EXISTS(SELECT _id_mlt FROM community WHERE _id_mlt = NEW._id_mlt AND _id_comm = NEW._id_comm) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_comm_update'); " +
                "END;");

        //creating military_unit table
        db.execSQL("CREATE TABLE " + TABLE_MILITARY_UNIT + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT NOT NULL REFERENCES "+ TABLE_DISLOCATION_PLACE +" ("+ COLUMN_DISLOCATION_PLACE_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_COMMUNITY_ID + " TEXT REFERENCES "+ TABLE_COMMUNITY +" ("+ COLUMN_COMMUNITY_ID +") ON UPDATE CASCADE ON DELETE SET NULL,"
                + COLUMN_MILITARY_ID + " TEXT UNIQUE,"
                + COLUMN_MILITARY_FIO + " TEXT,"
                + COLUMN_MILITARY_UNIT_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE,"
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_SURRENDER + " INTEGER NOT NULL DEFAULT 4,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") REFERENCES "+ TABLE_MILITARY +" ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") ON UPDATE CASCADE ON DELETE SET NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_MILITARY_UNIT + "(_id_md,_id_dp,_id_comm,_id_mlt,fio,_id_mu,name) VALUES "
                + "('БВО','Б-МД-000','Б-О-Д00','Б-029','30-военный фио','Б-ВЧ-00','1-я военная часть'),"
                + "('БВО','Б-МД-001','Б-О-Д01','Б-030','31-военный фио','Б-ВЧ-01','2-я военная часть'),"
                + "('БВО','Б-МД-002','Б-О-Д02','Б-031','32-военный фио','Б-ВЧ-02','3-я военная часть'),"
                + "('БВО','Б-МД-003','Б-О-Д03','Б-032','33-военный фио','Б-ВЧ-03','4-я военная часть'),"
                + "('БВО','Б-МД-004','Б-О-Д04','Б-033','34-военный фио','Б-ВЧ-04','5-я военная часть'),"
                + "('БВО','Б-МД-005','Б-О-Д05','Б-034','35-военный фио','Б-ВЧ-05','6-я военная часть'),"
                + "('БВО','Б-МД-006','Б-О-Д06','Б-035','36-военный фио','Б-ВЧ-06','7-я военная часть'),"
                + "('БВО','Б-МД-007','Б-О-Д07','Б-036','37-военный фио','Б-ВЧ-07','8-я военная часть'),"
                + "('БВО','Б-МД-000','Б-О-Д08','Б-037','38-военный фио','Б-ВЧ-08','9-я военная часть'),"
                + "('БВО','Б-МД-001','Б-О-Д09','Б-038','39-военный фио','Б-ВЧ-09','10-я военная часть'),"
                + "('БВО','Б-МД-002','Б-ОК-00','Б-039','40-военный фио','Б-ВЧ-10','11-я военная часть'),"
                + "('БВО','Б-МД-003','Б-ОК-01','Б-040','41-военный фио','Б-ВЧ-11','12-я военная часть'),"
                + "('БВО','Б-МД-004','Б-ОК-02','Б-041','42-военный фио','Б-ВЧ-12','13-я военная часть'),"
                + "('БВО','Б-МД-004','Б-ОК-03','Б-042','43-военный фио','Б-ВЧ-13','14-я военная часть'),"
                + "('БВО','Б-МД-005','Б-ОК-04','Б-043','44-военный фио','Б-ВЧ-14','15-я военная часть'),"
                + "('БВО','Б-МД-005','Б-ОК-05','Б-044','45-военный фио','Б-ВЧ-15','16-я военная часть'),"
                + "('БВО','Б-МД-006','Б-ОК-06','Б-045','46-военный фио','Б-ВЧ-16','17-я военная часть'),"
                + "('БВО','Б-МД-007','Б-ОК-07','Б-046','47-военный фио','Б-ВЧ-17','18-я военная часть'),"
                + "('БВО','Б-МД-007','Б-ОК-08','Б-047','48-военный фио','Б-ВЧ-18','19-я военная часть'),"
                + "('БВО','Б-МД-003','Б-ОБ-00','Б-048','49-военный фио','Б-ВЧ-19','20-я военная часть'),"
                + "('БВО','Б-МД-001','Б-ОБ-01','Б-049','50-военный фио','Б-ВЧ-20','21-я военная часть'),"
                + "('БВО','Б-МД-002','Б-ОБ-02','Б-050','51-военный фио','Б-ВЧ-21','22-я военная часть'),"
                + "('БВО','Б-МД-003','Б-ОБ-03','Б-051','52-военный фио','Б-ВЧ-22','23-я военная часть'),"
                + "('БВО','Б-МД-004','Б-ОБ-04','Б-052','53-военный фио','Б-ВЧ-23','24-я военная часть'),"
                + "('БВО','Б-МД-004','Б-О-Д02','Б-053','54-военный фио','Б-ВЧ-24','25-я военная часть'),"
                + "('БВО','Б-МД-005','Б-О-Д06','Б-054','55-военный фио','Б-ВЧ-25','26-я военная часть'),"
                + "('БВО','Б-МД-005','Б-ОК-05','Б-055','56-военный фио','Б-ВЧ-26','27-я военная часть'),"
                + "('БВО','Б-МД-006','Б-ОБ-03','Б-056','57-военный фио','Б-ВЧ-27','28-я военная часть'),"
                + "('БВО','Б-МД-003','Б-ОБ-00','Б-057','58-военный фио','Б-ВЧ-28','29-я военная часть'),"
                + "('БВО','Б-МД-005','Б-О-Д00','Б-058','59-военный фио','Б-ВЧ-29','30-я военная часть')"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_md_mu_insert BEFORE INSERT ON " + TABLE_MILITARY_UNIT
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "OR NEW._id_md != (SELECT _id_md FROM community WHERE _id_comm = NEW._id_comm) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_mu_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_md_mu_update BEFORE UPDATE ON " + TABLE_MILITARY_UNIT +
                " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "AND NOT EXISTS(SELECT _id_mlt FROM military_unit WHERE _id_mlt = NEW._id_mlt AND _id_mu = NEW._id_mu) " +
                "OR NEW._id_md != (SELECT _id_md FROM community WHERE _id_comm = NEW._id_comm) " +
                "AND NOT EXISTS(SELECT _id_comm FROM military_unit WHERE _id_comm = NEW._id_comm AND _id_mu = NEW._id_mu) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_mu_update'); " +
                "END;");

        //creating company table
        db.execSQL("CREATE TABLE " + TABLE_COMPANY + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT NOT NULL REFERENCES "+ TABLE_DISLOCATION_PLACE +" ("+ COLUMN_DISLOCATION_PLACE_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_UNIT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_UNIT +" ("+ COLUMN_MILITARY_UNIT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_ID + " TEXT UNIQUE,"
                + COLUMN_MILITARY_FIO + " TEXT,"
                + COLUMN_COMPANY_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE,"
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_SURRENDER + " INTEGER NOT NULL DEFAULT 3,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") REFERENCES "+ TABLE_MILITARY +" ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") ON UPDATE CASCADE ON DELETE SET NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_COMPANY + "(_id_md,_id_dp,_id_mu,_id_mlt,fio,_id_cmp,name) VALUES "
                + "('БВО','Б-МД-000','Б-ВЧ-00','Б-059','60-военный фио','Б-Р-00','1-я рота'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01',NULL,NULL,'Б-Р-01','2-я рота'),"
                + "('БВО','Б-МД-002','Б-ВЧ-02',NULL,NULL,'Б-Р-02','3-я рота'),"
                + "('БВО','Б-МД-003','Б-ВЧ-03',NULL,NULL,'Б-Р-03','4-я рота'),"
                + "('БВО','Б-МД-004','Б-ВЧ-04',NULL,NULL,'Б-Р-04','5-я рота'),"
                + "('БВО','Б-МД-005','Б-ВЧ-05',NULL,NULL,'Б-Р-05','6-я рота'),"
                + "('БВО','Б-МД-006','Б-ВЧ-06',NULL,NULL,'Б-Р-06','7-я рота'),"
                + "('БВО','Б-МД-007','Б-ВЧ-07',NULL,NULL,'Б-Р-07','8-я рота'),"
                + "('БВО','Б-МД-000','Б-ВЧ-08',NULL,NULL,'Б-Р-08','9-я рота'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09',NULL,NULL,'Б-Р-09','10-я рота'),"
                + "('БВО','Б-МД-002','Б-ВЧ-10',NULL,NULL,'Б-Р-10','11-я рота'),"
                + "('БВО','Б-МД-003','Б-ВЧ-11',NULL,NULL,'Б-Р-11','12-я рота'),"
                + "('БВО','Б-МД-004','Б-ВЧ-12',NULL,NULL,'Б-Р-12','13-я рота'),"
                + "('БВО','Б-МД-004','Б-ВЧ-13',NULL,NULL,'Б-Р-13','14-я рота'),"
                + "('БВО','Б-МД-005','Б-ВЧ-14',NULL,NULL,'Б-Р-14','15-я рота'),"
                + "('БВО','Б-МД-005','Б-ВЧ-15',NULL,NULL,'Б-Р-15','16-я рота'),"
                + "('БВО','Б-МД-006','Б-ВЧ-16',NULL,NULL,'Б-Р-16','17-я рота'),"
                + "('БВО','Б-МД-007','Б-ВЧ-17',NULL,NULL,'Б-Р-17','18-я рота'),"
                + "('БВО','Б-МД-007','Б-ВЧ-18',NULL,NULL,'Б-Р-18','19-я рота'),"
                + "('БВО','Б-МД-003','Б-ВЧ-19',NULL,NULL,'Б-Р-19','20-я рота'),"
                + "('БВО','Б-МД-001','Б-ВЧ-20',NULL,NULL,'Б-Р-20','21-я рота'),"
                + "('БВО','Б-МД-002','Б-ВЧ-21',NULL,NULL,'Б-Р-21','22-я рота'),"
                + "('БВО','Б-МД-003','Б-ВЧ-22',NULL,NULL,'Б-Р-22','23-я рота'),"
                + "('БВО','Б-МД-004','Б-ВЧ-23',NULL,NULL,'Б-Р-23','24-я рота'),"
                + "('БВО','Б-МД-004','Б-ВЧ-24',NULL,NULL,'Б-Р-24','25-я рота'),"
                + "('БВО','Б-МД-005','Б-ВЧ-25',NULL,NULL,'Б-Р-25','26-я рота'),"
                + "('БВО','Б-МД-005','Б-ВЧ-26',NULL,NULL,'Б-Р-26','27-я рота'),"
                + "('БВО','Б-МД-006','Б-ВЧ-27',NULL,NULL,'Б-Р-27','28-я рота'),"
                + "('БВО','Б-МД-003','Б-ВЧ-28',NULL,NULL,'Б-Р-28','29-я рота'),"
                + "('БВО','Б-МД-005','Б-ВЧ-29',NULL,NULL,'Б-Р-29','30-я рота')"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_md_cmp_insert BEFORE INSERT ON " + TABLE_COMPANY
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_cmp_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_md_cmp_update BEFORE UPDATE ON " + TABLE_COMPANY
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "AND NOT EXISTS(SELECT _id_mlt FROM company WHERE _id_mlt = NEW._id_mlt AND _id_cmp = NEW._id_cmp) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_cmp_update'); " +
                "END;");

        //creating platoon table
        db.execSQL("CREATE TABLE " + TABLE_PLATOON + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT NOT NULL REFERENCES "+ TABLE_DISLOCATION_PLACE +" ("+ COLUMN_DISLOCATION_PLACE_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_UNIT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_UNIT +" ("+ COLUMN_MILITARY_UNIT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_COMPANY_ID + " TEXT NOT NULL REFERENCES "+ TABLE_COMPANY +" ("+ COLUMN_COMPANY_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_ID + " TEXT UNIQUE,"
                + COLUMN_MILITARY_FIO + " TEXT,"
                + COLUMN_PLATOON_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE,"
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_SURRENDER + " INTEGER NOT NULL DEFAULT 2,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") REFERENCES "+ TABLE_MILITARY +" ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") ON UPDATE CASCADE ON DELETE SET NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_PLATOON + "(_id_md,_id_dp,_id_mu,_id_cmp,_id_mlt,fio,_id_pln,name) VALUES "
                + "('БВО','Б-МД-000','Б-ВЧ-00','Б-Р-00','Б-069','70-военный фио','Б-В-00','1-й взвод'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','Б-Р-01',NULL,NULL,'Б-В-01','2-й взвод'),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','Б-Р-02',NULL,NULL,'Б-В-02','3-й взвод'),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','Б-Р-03',NULL,NULL,'Б-В-03','4-й взвод'),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','Б-Р-04',NULL,NULL,'Б-В-04','5-й взвод'),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','Б-Р-05',NULL,NULL,'Б-В-05','6-й взвод'),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','Б-Р-06',NULL,NULL,'Б-В-06','7-й взвод'),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','Б-Р-07',NULL,NULL,'Б-В-07','8-й взвод'),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','Б-Р-08',NULL,NULL,'Б-В-08','9-й взвод'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','Б-Р-09',NULL,NULL,'Б-В-09','10-й взвод'),"
                + "('БВО','Б-МД-002','Б-ВЧ-10','Б-Р-10',NULL,NULL,'Б-В-10','11-й взвод'),"
                + "('БВО','Б-МД-003','Б-ВЧ-11','Б-Р-11',NULL,NULL,'Б-В-11','12-й взвод'),"
                + "('БВО','Б-МД-004','Б-ВЧ-12','Б-Р-12',NULL,NULL,'Б-В-12','13-й взвод'),"
                + "('БВО','Б-МД-004','Б-ВЧ-13','Б-Р-13',NULL,NULL,'Б-В-13','14-й взвод'),"
                + "('БВО','Б-МД-005','Б-ВЧ-14','Б-Р-14',NULL,NULL,'Б-В-14','15-й взвод'),"
                + "('БВО','Б-МД-005','Б-ВЧ-15','Б-Р-15',NULL,NULL,'Б-В-15','16-й взвод'),"
                + "('БВО','Б-МД-006','Б-ВЧ-16','Б-Р-16',NULL,NULL,'Б-В-16','17-й взвод'),"
                + "('БВО','Б-МД-007','Б-ВЧ-17','Б-Р-17',NULL,NULL,'Б-В-17','18-й взвод'),"
                + "('БВО','Б-МД-007','Б-ВЧ-18','Б-Р-18',NULL,NULL,'Б-В-18','19-й взвод'),"
                + "('БВО','Б-МД-003','Б-ВЧ-19','Б-Р-19',NULL,NULL,'Б-В-19','20-й взвод'),"
                + "('БВО','Б-МД-001','Б-ВЧ-20','Б-Р-20',NULL,NULL,'Б-В-20','21-й взвод'),"
                + "('БВО','Б-МД-002','Б-ВЧ-21','Б-Р-21',NULL,NULL,'Б-В-21','22-й взвод'),"
                + "('БВО','Б-МД-003','Б-ВЧ-22','Б-Р-22',NULL,NULL,'Б-В-22','23-й взвод'),"
                + "('БВО','Б-МД-004','Б-ВЧ-23','Б-Р-23',NULL,NULL,'Б-В-23','24-й взвод'),"
                + "('БВО','Б-МД-004','Б-ВЧ-24','Б-Р-24',NULL,NULL,'Б-В-24','25-й взвод'),"
                + "('БВО','Б-МД-005','Б-ВЧ-25','Б-Р-25',NULL,NULL,'Б-В-25','26-й взвод'),"
                + "('БВО','Б-МД-005','Б-ВЧ-26','Б-Р-26',NULL,NULL,'Б-В-26','27-й взвод'),"
                + "('БВО','Б-МД-006','Б-ВЧ-27','Б-Р-27',NULL,NULL,'Б-В-27','28-й взвод'),"
                + "('БВО','Б-МД-003','Б-ВЧ-28','Б-Р-28',NULL,NULL,'Б-В-28','29-й взвод'),"
                + "('БВО','Б-МД-005','Б-ВЧ-29','Б-Р-29',NULL,NULL,'Б-В-29','30-й взвод')"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_md_pln_insert BEFORE INSERT ON " + TABLE_PLATOON
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_pln_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_md_pln_update BEFORE UPDATE ON " + TABLE_PLATOON
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "AND NOT EXISTS(SELECT _id_mlt FROM platoon WHERE _id_mlt = NEW._id_mlt AND _id_pln = NEW._id_pln) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_pln_update'); " +
                "END;");

        //creating department table
        db.execSQL("CREATE TABLE " + TABLE_DEPARTMENT + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT NOT NULL REFERENCES "+ TABLE_DISLOCATION_PLACE +" ("+ COLUMN_DISLOCATION_PLACE_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_UNIT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_UNIT +" ("+ COLUMN_MILITARY_UNIT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_COMPANY_ID + " TEXT NOT NULL REFERENCES "+ TABLE_COMPANY +" ("+ COLUMN_COMPANY_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_PLATOON_ID + " TEXT NOT NULL REFERENCES "+ TABLE_PLATOON +" ("+ COLUMN_PLATOON_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_ID + " TEXT UNIQUE,"
                + COLUMN_MILITARY_FIO + " TEXT,"
                + COLUMN_DEPARTMENT_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE,"
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_SURRENDER + " INTEGER NOT NULL DEFAULT 1,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") REFERENCES "+ TABLE_MILITARY +" ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") ON UPDATE CASCADE ON DELETE SET NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_DEPARTMENT + "(_id_md,_id_dp,_id_mu,_id_cmp,_id_pln,_id_mlt,fio,_id_dpm,name) VALUES "
                + "('БВО','Б-МД-000','Б-ВЧ-00','Б-Р-00','Б-В-00','Б-070','71-военный фио','Б-О-00','1-ое отделение'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','Б-Р-01','Б-В-01',NULL,NULL,'Б-О-01','2-ое отделение'),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','Б-Р-02','Б-В-02',NULL,NULL,'Б-О-02','3-ое отделение'),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','Б-Р-03','Б-В-03',NULL,NULL,'Б-О-03','4-ое отделение'),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','Б-Р-04','Б-В-04',NULL,NULL,'Б-О-04','5-ое отделение'),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','Б-Р-05','Б-В-05',NULL,NULL,'Б-О-05','6-ое отделение'),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','Б-Р-06','Б-В-06',NULL,NULL,'Б-О-06','7-ое отделение'),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','Б-Р-07','Б-В-07',NULL,NULL,'Б-О-07','8-ое отделение'),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','Б-Р-08','Б-В-08',NULL,NULL,'Б-О-08','9-ое отделение'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','Б-Р-09','Б-В-09',NULL,NULL,'Б-О-09','10-ое отделение'),"
                + "('БВО','Б-МД-002','Б-ВЧ-10','Б-Р-10','Б-В-10',NULL,NULL,'Б-О-10','11-ое отделение'),"
                + "('БВО','Б-МД-003','Б-ВЧ-11','Б-Р-11','Б-В-11',NULL,NULL,'Б-О-11','12-ое отделение'),"
                + "('БВО','Б-МД-004','Б-ВЧ-12','Б-Р-12','Б-В-12',NULL,NULL,'Б-О-12','13-ое отделение'),"
                + "('БВО','Б-МД-004','Б-ВЧ-13','Б-Р-13','Б-В-13',NULL,NULL,'Б-О-13','14-ое отделение'),"
                + "('БВО','Б-МД-005','Б-ВЧ-14','Б-Р-14','Б-В-14',NULL,NULL,'Б-о-14','15-ое отделение'),"
                + "('БВО','Б-МД-005','Б-ВЧ-15','Б-Р-15','Б-В-15',NULL,NULL,'Б-О-15','16-ое отделение'),"
                + "('БВО','Б-МД-006','Б-ВЧ-16','Б-Р-16','Б-В-16',NULL,NULL,'Б-О-16','17-ое отделение'),"
                + "('БВО','Б-МД-007','Б-ВЧ-17','Б-Р-17','Б-В-17',NULL,NULL,'Б-О-17','18-ое отделение'),"
                + "('БВО','Б-МД-007','Б-ВЧ-18','Б-Р-18','Б-В-18',NULL,NULL,'Б-О-18','19-ое отделение'),"
                + "('БВО','Б-МД-003','Б-ВЧ-19','Б-Р-19','Б-В-19',NULL,NULL,'Б-О-19','20-ое отделение'),"
                + "('БВО','Б-МД-001','Б-ВЧ-20','Б-Р-20','Б-В-20',NULL,NULL,'Б-О-20','21-ое отделение'),"
                + "('БВО','Б-МД-002','Б-ВЧ-21','Б-Р-21','Б-В-21',NULL,NULL,'Б-О-21','22-ое отделение'),"
                + "('БВО','Б-МД-003','Б-ВЧ-22','Б-Р-22','Б-В-22',NULL,NULL,'Б-о-22','23-ое отделение'),"
                + "('БВО','Б-МД-004','Б-ВЧ-23','Б-Р-23','Б-В-23',NULL,NULL,'Б-О-23','24-ое отделение'),"
                + "('БВО','Б-МД-004','Б-ВЧ-24','Б-Р-24','Б-В-24',NULL,NULL,'Б-О-24','25-ое отделение'),"
                + "('БВО','Б-МД-005','Б-ВЧ-25','Б-Р-25','Б-В-25',NULL,NULL,'Б-О-25','26-ое отделение'),"
                + "('БВО','Б-МД-005','Б-ВЧ-26','Б-Р-26','Б-В-26',NULL,NULL,'Б-О-26','27-ое отделение'),"
                + "('БВО','Б-МД-006','Б-ВЧ-27','Б-Р-27','Б-В-27',NULL,NULL,'Б-О-27','28-ое отделение'),"
                + "('БВО','Б-МД-003','Б-ВЧ-28','Б-Р-28','Б-В-28',NULL,NULL,'Б-О-28','29-ое отделение'),"
                + "('БВО','Б-МД-005','Б-ВЧ-29','Б-Р-29','Б-В-29',NULL,NULL,'Б-О-29','30-ое отделение')"
                + " ;");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_md_dpm_insert BEFORE INSERT ON " + TABLE_DEPARTMENT
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_dpm_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_md_dpm_update BEFORE UPDATE ON " + TABLE_DEPARTMENT
                + " WHEN NEW._id_md != (SELECT _id_md FROM military WHERE _id_mlt = NEW._id_mlt) " +
                "AND NOT EXISTS(SELECT _id_mlt FROM department WHERE _id_mlt = NEW._id_mlt AND _id_dpm = NEW._id_dpm) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_md_dpm_update'); " +
                "END;");

        //creating speciality table
        db.execSQL("CREATE TABLE " + TABLE_SPECIALITY + "("
                + COLUMN_SPECIALITY_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE, "
                + COLUMN_QUALIFICATION + " TEXT NOT NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_SPECIALITY + " VALUES " +
                "('2-37 04 01','Техническая эксплуатация воздушных судов и двигателей государственной авиации', 'Техник')," +
                "('2-37 04 02-01 03','Автоматические системы и электрооборудование воздушных судов государственной авиации', 'Техник')," +
                "('2-37 04 02-01 04','Десантно-транспортное и специальное оборудование воздушных судов государственной авиации', 'Техник')," +
                "('2-37 04 02-02 03','Радиоэлектронное оборудование воздушных судов государственной авиации', 'Техник')," +
                "('1-95 02 11','Техническая эксплуатация средств наземного обеспечения полетов', 'Инженер, специалист по управлению')," +
                "('1-37 04 03-01-01','Техническая эксплуатация беспилотных авиационных комплексов', 'Инженер, специалист по управлению')," +
                "('1-37 04 03-01-02','Технологическая эксплуатация беспилотных авиационных комплексов', 'Инженер, специалист по управлению');");

        //creating equipment_category table
        db.execSQL("CREATE TABLE " + TABLE_EQUIPMENT_CATEGORY + "("
                + COLUMN_EQUIPMENT_CATEGORY_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_EQUIPMENT_CATEGORY + " VALUES " +
                "('КТ-00', 'БМП')," +
                "('КТ-01', 'Тягачи')," +
                "('КТ-02', 'Автотранспорт')," +
                "('КТ-03', 'БТР')" +
                ";");

        //creating weaponry_category table
        db.execSQL("CREATE TABLE " + TABLE_WEAPONRY_CATEGORY + "("
                + COLUMN_WEAPONRY_CATEGORY_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL UNIQUE);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_WEAPONRY_CATEGORY + " VALUES " +
                "('КВ-00', 'Карабины')," +
                "('КВ-01', 'Ракетное вооружение')," +
                "('КВ-02', 'Автоматическое оружие')," +
                "('КВ-03', 'Артиллерия')," +
                "('КВ-04', 'Танки')" +
                ";");

        //creating equipment table
        db.execSQL("CREATE TABLE " + TABLE_EQUIPMENT + "("
                + COLUMN_EQUIPMENT_CATEGORY_ID + " TEXT REFERENCES "+ TABLE_EQUIPMENT_CATEGORY +" ("+ COLUMN_EQUIPMENT_CATEGORY_ID +") ON UPDATE CASCADE ON DELETE SET NULL,"
                + COLUMN_TYPE + " TEXT NOT NULL,"
                + COLUMN_EQUIPMENT_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_ATT + " TEXT NOT NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_EQUIPMENT + " VALUES " +
                "('КТ-02','Транспортная техника','ТТХ-000','Зил','Вес: 3,5т')," +
                "('КТ-00','Военная техника','ВТХ-000','Пума','Вес: 18,3т')," +
                "('КТ-00','Военная техника','ВТХ-001','БРМ-1К','Вес: 18,3т')," +
                "('КТ-00','Военная техника','ВТХ-002','БМД-1','Вес: 14,3т')," +
                "('КТ-03','Военная техника','ВТХ-003','БТР-Д','Вес: 16,2т')," +
                "('КТ-03','Военная техника','ВТХ-004','МТ-ЛБ','Вес: 19,7т')," +
                "('КТ-03','Военная техника','ВТХ-005','БТР-70','Вес: 18,3т')," +
                "('КТ-03','Военная техника','ВТХ-006','БТР-80','Вес: 19,3т')," +
                "('КТ-02','Транспортная техника','ТТХ-001','ЗИС-151','Вес: 13,3т')," +
                "('КТ-02','Транспортная техника','ТТХ-002','БРЭМ','Вес: 12,3т')" +
                ";");

        //creating weaponry table
        db.execSQL("CREATE TABLE " + TABLE_WEAPONRY + "("
                + COLUMN_WEAPONRY_CATEGORY_ID + " TEXT REFERENCES "+ TABLE_WEAPONRY_CATEGORY +" ("+ COLUMN_WEAPONRY_CATEGORY_ID +") ON UPDATE CASCADE ON DELETE SET NULL,"
                + COLUMN_WEAPONRY_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL,"
                + COLUMN_ATT + " TEXT NOT NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_WEAPONRY + " VALUES " +
                "('КВ-00','ВР-000','Сайга-410','Калибр - 410\" (10,4 мм); Масса - 3,4кг')," +
                "('КВ-02','ВР-001','HK MP7A1','Магазин - 40п; Масса - 1,8кг')," +
                "('КВ-01','ВР-002','ПТРК «Фагот»','Ракеты 9М111 (9М111-2) в транспортно-пусковых контейнерах')," +
                "('КВ-00','ВР-003','Снайперская винтовка Драгунова (СВД)','Калибр - 7,62 мм; Магазин - 10п; Масса - 4,30кг')," +
                "('КВ-02','ВР-004','Ручной пулемет Калашникова (РПК-74)','Калибр - 5,45 мм; Магазин - 45п; Масса - 5,46кг')," +
                "('КВ-01','ВР-005','Ручной противотанковый гранатомет (РПГ-7В)','Калибр - 40; Масса - 6,7кг')," +
                "('КВ-00','ВР-006','АВТОМАТ КАЛАШНИКОВА (АКС-74)','Калибр - 5,45 мм; Магазин - 30п, Масса - 3,2кг')," +
                "('КВ-03','ВР-007','РСЗО 9К58 \"Смерч\"','Калибр - 300 мм; Магазин - 12с, Масса - 43,7т')," +
                "('КВ-03','ВР-008','2С5 «Гиацинт-С»','152-мм САУ')," +
                "('КВ-04','ВР-009','Т-80','Масса - 23,8т')" +
                ";");

        //creating building table
        db.execSQL("CREATE TABLE " + TABLE_BUILDING + "("
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT NOT NULL REFERENCES "+ TABLE_DISLOCATION_PLACE +" ("+ COLUMN_DISLOCATION_PLACE_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_UNIT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_UNIT +" ("+ COLUMN_MILITARY_UNIT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_BUILDING_ID + " TEXT PRIMARY KEY NOT NULL UNIQUE, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_ATT + " TEXT NOT NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_BUILDING + " VALUES "
                + "('БВО','Б-МД-000','Б-ВЧ-00','ЗД-00','Здание-1','Этажи 1'),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ЗД-01','Здание-2','Этажи 2'),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ЗД-02','Здание-3','Этажи 5'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ЗД-03','Здание-1','Этажи 1'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ЗД-04','Здание-2','Этажи 1'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ЗД-05','Здание-3','Этажи 3'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ЗД-06','Здание-4','Этажи 3'),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ЗД-07','Здание-5','Этажи 8'),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ЗД-08','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ЗД-09','Здание-2','Этажи 5'),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ЗД-10','Здание-1','Этажи 1'),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ЗД-11','Здание-2','Этажи 1'),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ЗД-12','Здание-3','Этажи 1'),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ЗД-13','Здание-4','Этажи 1'),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ЗД-14','Здание-1','Этажи 3'),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ЗД-15','Здание-2','Этажи 2'),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ЗД-16','Здание-3','Этажи 1'),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ЗД-17','Здание-1','Этажи 6'),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ЗД-18','Здание-2','Этажи 7'),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ЗД-19','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ЗД-20','Здание-2','Этажи 5'),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ЗД-21','Здание-3','Этажи 5'),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ЗД-22','Здание-4','Этажи 5'),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ЗД-23','Здание-1','Этажи 3'),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ЗД-24','Здание-2','Этажи 1'),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ЗД-25','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ЗД-26','Здание-2','Этажи 2'),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ЗД-27','Здание-3','Этажи 6'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ЗД-28','Здание-1','Этажи 1'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ЗД-29','Здание-2','Этажи 6'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ЗД-30','Здание-3','Этажи 2'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ЗД-31','Здание-4','Этажи 8'),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ЗД-32','Здание-5','Этажи 9'),"
                + "('БВО','Б-МД-002','Б-ВЧ-10','ЗД-33','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-003','Б-ВЧ-11','ЗД-34','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-004','Б-ВЧ-12','ЗД-35','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-004','Б-ВЧ-13','ЗД-36','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-005','Б-ВЧ-14','ЗД-37','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-005','Б-ВЧ-15','ЗД-38','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-006','Б-ВЧ-16','ЗД-39','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-007','Б-ВЧ-17','ЗД-40','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-007','Б-ВЧ-18','ЗД-41','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-003','Б-ВЧ-19','ЗД-42','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-001','Б-ВЧ-20','ЗД-43','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-002','Б-ВЧ-21','ЗД-44','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-003','Б-ВЧ-22','ЗД-45','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-004','Б-ВЧ-23','ЗД-46','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-004','Б-ВЧ-24','ЗД-47','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-005','Б-ВЧ-25','ЗД-48','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-005','Б-ВЧ-26','ЗД-49','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-006','Б-ВЧ-27','ЗД-50','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-003','Б-ВЧ-28','ЗД-51','Здание-1','Этажи 5'),"
                + "('БВО','Б-МД-005','Б-ВЧ-29','ЗД-52','Здание-1','Этажи 5')"
                + ";");

        //creating subdivision_dislocation table
        db.execSQL("CREATE TABLE " + TABLE_SUBDIVISION_DISLOCATION + "("
                + COLUMN_BUILDING_ID + " TEXT NOT NULL REFERENCES "+ TABLE_BUILDING +" ("+ COLUMN_BUILDING_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_SUBDIVISION_ID + " TEXT NOT NULL UNIQUE);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_SUBDIVISION_DISLOCATION + " VALUES "
                + "('ЗД-00','Б-В-00'),"
                + "('ЗД-01','Б-Р-00'),"
                + "('ЗД-02','Б-О-00'),"
                + "('ЗД-03','Б-В-01'),"
                + "('ЗД-04','Б-Р-01'),"
                + "('ЗД-05','Б-О-01'),"
                + "('ЗД-08','Б-В-02'),"
                + "('ЗД-08','Б-Р-02'),"
                + "('ЗД-09','Б-О-02'),"
                + "('ЗД-10','Б-В-03'),"
                + "('ЗД-11','Б-Р-03'),"
                + "('ЗД-12','Б-О-03'),"
                + "('ЗД-14','Б-В-04'),"
                + "('ЗД-15','Б-Р-04'),"
                + "('ЗД-16','Б-О-04'),"
                + "('ЗД-17','Б-В-05'),"
                + "('ЗД-18','Б-Р-05'),"
                + "('ЗД-18','Б-О-05'),"
                + "('ЗД-19','Б-В-06'),"
                + "('ЗД-20','Б-Р-06'),"
                + "('ЗД-21','Б-О-06'),"
                + "('ЗД-24','Б-В-07'),"
                + "('ЗД-24','Б-Р-07'),"
                + "('ЗД-24','Б-О-07'),"
                + "('ЗД-25','Б-В-08'),"
                + "('ЗД-26','Б-Р-08'),"
                + "('ЗД-27','Б-О-08'),"
                + "('ЗД-28','Б-В-09'),"
                + "('ЗД-29','Б-Р-09'),"
                + "('ЗД-30','Б-О-09'),"
                + "('ЗД-33','Б-В-10'),"
                + "('ЗД-33','Б-Р-10'),"
                + "('ЗД-33','Б-О-10'),"
                + "('ЗД-34','Б-В-11'),"
                + "('ЗД-34','Б-Р-11'),"
                + "('ЗД-34','Б-О-11'),"
                + "('ЗД-38','Б-В-15'),"
                + "('ЗД-40','Б-Р-17'),"
                + "('ЗД-44','Б-О-21'),"
                + "('ЗД-52','Б-В-29')"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_sub_dis_insert BEFORE INSERT ON " + TABLE_SUBDIVISION_DISLOCATION +
                " WHEN NOT EXISTS(SELECT _id_cmp FROM company WHERE _id_cmp = NEW._id_subdivision AND _id_mu = (SELECT _id_mu FROM building WHERE _id_blg = NEW._id_blg)) " +
                "AND NOT EXISTS(SELECT _id_pln FROM platoon WHERE _id_pln = NEW._id_subdivision AND _id_mu = (SELECT _id_mu FROM building WHERE _id_blg = NEW._id_blg)) " +
                "AND NOT EXISTS(SELECT _id_dpm FROM department WHERE _id_dpm = NEW._id_subdivision AND _id_mu = (SELECT _id_mu FROM building WHERE _id_blg = NEW._id_blg)) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_sub_dis_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_sub_dis_update BEFORE UPDATE ON " + TABLE_SUBDIVISION_DISLOCATION +
                " WHEN NOT EXISTS(SELECT _id_cmp FROM company WHERE _id_cmp = NEW._id_subdivision AND _id_mu = (SELECT _id_mu FROM building WHERE _id_blg = NEW._id_blg)) " +
                "AND NOT EXISTS(SELECT _id_pln FROM platoon WHERE _id_pln = NEW._id_subdivision AND _id_mu = (SELECT _id_mu FROM building WHERE _id_blg = NEW._id_blg)) " +
                "AND NOT EXISTS(SELECT _id_dpm FROM department WHERE _id_dpm = NEW._id_subdivision AND _id_mu = (SELECT _id_mu FROM building WHERE _id_blg = NEW._id_blg)) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_sub_dis_update'); " +
                "END;");

        //creating speciality_accounting table
        db.execSQL("CREATE TABLE " + TABLE_SPECIALITY_ACCOUNTING + "("
                + COLUMN_ACCOUNTING_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_ID + " TEXT NOT NULL,"
                + COLUMN_MILITARY_FIO + " TEXT NOT NULL,"
                + COLUMN_SPECIALITY_ID + " TEXT NOT NULL REFERENCES "+ TABLE_SPECIALITY +" ("+ COLUMN_SPECIALITY_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + "FOREIGN KEY ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") REFERENCES "+ TABLE_MILITARY +" ("+ COLUMN_MILITARY_ID + ", " + COLUMN_MILITARY_FIO + ") ON UPDATE CASCADE ON DELETE CASCADE);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_SPECIALITY_ACCOUNTING + "(_id_md,_id_mlt,fio,_id_spc) VALUES "
                + "('БВО','Б-000','Врублевский Илья Андреевич','2-37 04 01'),"
                + "('БВО','Б-000','Врублевский Илья Андреевич','1-95 02 11'),"
                + "('БВО','Б-000','Врублевский Илья Андреевич','1-37 04 03-01-01'),"
                + "('БВО','Б-001','Сидоров Петр Иванович','1-37 04 03-01-01'),"
                + "('БВО','Б-001','Сидоров Петр Иванович','2-37 04 02-01 04'),"
                + "('БВО','Б-002','Карпук Григорий Олегович','2-37 04 01'),"
                + "('БВО','Б-002','Карпук Григорий Олегович','2-37 04 02-01 03'),"
                + "('БВО','Б-002','Карпук Григорий Олегович','1-95 02 11'),"
                + "('БВО','Б-002','Карпук Григорий Олегович','1-37 04 03-01-02'),"
                + "('БВО','Б-003','Ефстафьевич Сегрей Иванович','2-37 04 01'),"
                + "('БВО','Б-003','Ефстафьевич Сегрей Иванович','1-95 02 11'),"
                + "('РВО','Р-000','Петров Анатолий Олегович','2-37 04 01'),"
                + "('БВО','Б-005','Кравченко Егор Артемьевич','2-37 04 01'),"
                + "('БВО','Б-006','Весницов Аркадий Семенович','2-37 04 01'),"
                + "('БВО','Б-006','Весницов Аркадий Семенович','1-37 04 03-01-01'),"
                + "('БВО','Б-006','Весницов Аркадий Семенович','2-37 04 02-01 04'),"
                + "('БВО','Б-007','Антилевский Андрей Игоревич','2-37 04 02-01 04'),"
                + "('БВО','Б-008','Кузнецов Валерий Генадьевич','2-37 04 02-01 04'),"
                + "('БВО','Б-009','Плюшкин Григорий Олегович','2-37 04 02-01 04'),"
                + "('БВО','Б-009','Плюшкин Григорий Олегович','2-37 04 02-01 03'),"
                + "('БВО','Б-010','Климович Сегрей Иванович','2-37 04 02-01 03'),"
                + "('БВО','Б-011','Сычев Анатолий Олегович','2-37 04 02-01 03'),"
                + "('БВО','Б-012','Герасимов Егор Артемьевич','1-37 04 03-01-01'),"
                + "('БВО','Б-012','Герасимов Егор Артемьевич','2-37 04 01'),"
                + "('БВО','Б-012','Герасимов Егор Артемьевич','1-95 02 11'),"
                + "('БВО','Б-012','Герасимов Егор Артемьевич','2-37 04 02-01 03'),"
                + "('БВО','Б-013','Свистунов Аркадий Семенович','1-95 02 11'),"
                + "('БВО','Б-014','Кравчук Андрей Андреевич','1-95 02 11'),"
                + "('БВО','Б-015','Весарионов Петр Иванович','1-95 02 11'),"
                + "('БВО','Б-016','Грибоедов Григорий Олегович','1-95 02 11'),"
                + "('БВО','Б-017','Козлов Сегрей Иванович','1-37 04 03-01-01'),"
                + "('БВО','Б-018','Власов Анатолий Олегович','1-37 04 03-01-01'),"
                + "('БВО','Б-019','Гриб Егор Артемьевич','1-37 04 03-01-01'),"
                + "('БВО','Б-020','Василевский Аркадий Семенович','1-37 04 03-01-01'),"
                + "('БВО','Б-021','Асташенко Андрей Игоревич','1-37 04 03-01-02'),"
                + "('БВО','Б-022','Понтелейчук Валерий Генадьевич','1-37 04 03-01-02'),"
                + "('БВО','Б-023','Плаксина Андрей Игоревич','1-37 04 03-01-02'),"
                + "('БВО','Б-024','Стасилевич Валерий Генадьевич','2-37 04 01'),"
                + "('БВО','Б-025','Азаренко Александр Семенович','2-37 04 01')"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_spc_insert BEFORE INSERT ON " + TABLE_SPECIALITY_ACCOUNTING +
                " WHEN EXISTS(SELECT _id_spc FROM speciality_accounting WHERE _id_spc = NEW._id_spc AND _id_mlt = NEW._id_mlt) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_spc_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_spc_update BEFORE UPDATE ON " + TABLE_SPECIALITY_ACCOUNTING +
                " WHEN EXISTS(SELECT _id_spc FROM speciality_accounting WHERE _id_spc = NEW._id_spc AND _id_mlt = NEW._id_mlt) " +
                "AND NOT EXISTS(SELECT _id_spc FROM speciality_accounting WHERE _id_spc = NEW._id_spc AND _id_accounting = NEW._id_accounting) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_spc_update'); " +
                "END;");

        //creating equipment_accounting table
        db.execSQL("CREATE TABLE " + TABLE_EQUIPMENT_ACCOUNTING + "("
                + COLUMN_ACCOUNTING_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT NOT NULL REFERENCES "+ TABLE_DISLOCATION_PLACE +" ("+ COLUMN_DISLOCATION_PLACE_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_UNIT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_UNIT +" ("+ COLUMN_MILITARY_UNIT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_EQUIPMENT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_EQUIPMENT +" ("+ COLUMN_EQUIPMENT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_QUANTITY + " INTEGER NOT NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_EQUIPMENT_ACCOUNTING + "(_id_md,_id_dp,_id_mu,_id_equ,quantity) VALUES "
                + "('БВО','Б-МД-000','Б-ВЧ-00','ТТХ-000',10),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ТТХ-001',2),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ТТХ-002',20),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВТХ-000',32),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВТХ-001',12),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВТХ-002',2),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВТХ-003',34),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВТХ-004',7),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВТХ-005',19),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВТХ-006',15),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ТТХ-000',2),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ТТХ-001',10),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ТТХ-002',2),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВТХ-000',22),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВТХ-001',19),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВТХ-002',19),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВТХ-003',12),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВТХ-004',8),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВТХ-005',2),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВТХ-006',4),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ТТХ-000',6),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ТТХ-001',5),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ТТХ-002',34),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВТХ-000',1),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВТХ-001',22),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВТХ-002',15),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВТХ-003',17),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВТХ-004',23),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВТХ-005',4),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВТХ-006',66),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ТТХ-000',2),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ТТХ-001',2),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ТТХ-002',2),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВТХ-000',13),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВТХ-001',34),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВТХ-002',43),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВТХ-003',12),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВТХ-004',8),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВТХ-005',1),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВТХ-006',13),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ТТХ-000',6),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ТТХ-001',15),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ТТХ-002',19),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВТХ-000',10),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВТХ-001',10),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВТХ-002',10),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВТХ-003',10),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВТХ-004',10),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВТХ-005',10),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВТХ-006',10),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ТТХ-000',10),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ТТХ-001',10),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ТТХ-002',10),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВТХ-000',122),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВТХ-001',21),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВТХ-002',22),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВТХ-003',1),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВТХ-004',52),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВТХ-005',5),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВТХ-006',3),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ТТХ-000',14),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ТТХ-001',18),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ТТХ-002',11),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВТХ-000',7),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВТХ-001',8),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВТХ-002',9),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВТХ-003',9),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВТХ-004',5),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВТХ-005',4),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВТХ-006',4),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ТТХ-000',10),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ТТХ-001',3),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ТТХ-002',16),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВТХ-000',22),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВТХ-001',42),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВТХ-002',21),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВТХ-003',6),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВТХ-004',9),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВТХ-005',15),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВТХ-006',1),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ТТХ-000',1),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ТТХ-001',16),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ТТХ-002',17),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВТХ-000',1),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВТХ-001',21),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВТХ-002',10),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВТХ-003',9),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВТХ-004',8),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВТХ-005',7),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВТХ-006',6),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ТТХ-000',12),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ТТХ-001',22),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ТТХ-002',43),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВТХ-000',43),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВТХ-001',12),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВТХ-002',11),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВТХ-003',2),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВТХ-004',2),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВТХ-005',33),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВТХ-006',4),"
                + "('БВО','Б-МД-002','Б-ВЧ-10','ТТХ-001',10),"
                + "('БВО','Б-МД-003','Б-ВЧ-11','ТТХ-002',15),"
                + "('БВО','Б-МД-004','Б-ВЧ-12','ТТХ-000',12),"
                + "('БВО','Б-МД-004','Б-ВЧ-13','ВТХ-003',25),"
                + "('БВО','Б-МД-005','Б-ВЧ-14','ВТХ-004',9),"
                + "('БВО','Б-МД-005','Б-ВЧ-15','ТТХ-001',6),"
                + "('БВО','Б-МД-006','Б-ВЧ-16','ВТХ-005',1),"
                + "('БВО','Б-МД-007','Б-ВЧ-17','ВТХ-006',2),"
                + "('БВО','Б-МД-007','Б-ВЧ-18','ТТХ-000',3),"
                + "('БВО','Б-МД-003','Б-ВЧ-19','ТТХ-002',4),"
                + "('БВО','Б-МД-001','Б-ВЧ-20','ВТХ-001',5),"
                + "('БВО','Б-МД-002','Б-ВЧ-21','ТТХ-002',6),"
                + "('БВО','Б-МД-003','Б-ВЧ-22','ВТХ-003',7),"
                + "('БВО','Б-МД-004','Б-ВЧ-23','ТТХ-001',8),"
                + "('БВО','Б-МД-004','Б-ВЧ-24','ВТХ-003',12),"
                + "('БВО','Б-МД-005','Б-ВЧ-25','ТТХ-000',15),"
                + "('БВО','Б-МД-005','Б-ВЧ-26','ВТХ-003',24),"
                + "('БВО','Б-МД-006','Б-ВЧ-27','ВТХ-004',3),"
                + "('БВО','Б-МД-003','Б-ВЧ-28','ВТХ-005',21),"
                + "('БВО','Б-МД-005','Б-ВЧ-29','ВТХ-006',5)"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_equ_insert BEFORE INSERT ON " + TABLE_EQUIPMENT_ACCOUNTING +
                " WHEN EXISTS(SELECT _id_equ FROM equipment_accounting WHERE _id_equ = NEW._id_equ AND _id_mu = NEW._id_mu) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_equ_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_equ_update BEFORE UPDATE ON " + TABLE_EQUIPMENT_ACCOUNTING +
                " WHEN EXISTS(SELECT _id_equ FROM equipment_accounting WHERE _id_equ = NEW._id_equ AND _id_mu = NEW._id_mu) " +
                "AND NOT EXISTS(SELECT _id_equ FROM equipment_accounting WHERE _id_equ = NEW._id_equ AND _id_accounting = NEW._id_accounting) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_equ_update'); " +
                "END;");

        //creating weaponry_accounting table
        db.execSQL("CREATE TABLE " + TABLE_WEAPONRY_ACCOUNTING + "("
                + COLUMN_ACCOUNTING_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + COLUMN_MILITARY_DISTRICT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_DISTRICT +" ("+ COLUMN_MILITARY_DISTRICT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_DISLOCATION_PLACE_ID + " TEXT NOT NULL REFERENCES "+ TABLE_DISLOCATION_PLACE +" ("+ COLUMN_DISLOCATION_PLACE_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_MILITARY_UNIT_ID + " TEXT NOT NULL REFERENCES "+ TABLE_MILITARY_UNIT +" ("+ COLUMN_MILITARY_UNIT_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_WEAPONRY_ID + " TEXT NOT NULL REFERENCES "+ TABLE_WEAPONRY +" ("+ COLUMN_WEAPONRY_ID +") ON UPDATE CASCADE ON DELETE CASCADE,"
                + COLUMN_QUANTITY + " INTEGER NOT NULL);");
        //adding first data
        db.execSQL("INSERT INTO "+ TABLE_WEAPONRY_ACCOUNTING + "(_id_md,_id_dp,_id_mu,_id_wpn,quantity) VALUES "
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-000',2),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-001',3),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-002',4),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-003',5),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-004',6),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-005',7),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-006',8),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-007',10),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-008',9),"
                + "('БВО','Б-МД-000','Б-ВЧ-00','ВР-009',10),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-000',8),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-001',5),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-002',6),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-003',4),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-004',21),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-005',25),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-006',12),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-007',17),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-008',11),"
                + "('БВО','Б-МД-001','Б-ВЧ-01','ВР-009',15),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-000',1),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-001',2),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-002',3),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-003',4),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-004',5),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-005',6),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-006',7),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-007',8),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-008',9),"
                + "('БВО','Б-МД-002','Б-ВЧ-02','ВР-009',10),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-000',12),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-001',13),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-002',14),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-003',15),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-004',16),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-005',17),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-006',18),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-007',19),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-008',20),"
                + "('БВО','Б-МД-003','Б-ВЧ-03','ВР-009',21),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-000',22),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-001',23),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-002',24),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-003',25),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-004',24),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-005',23),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-006',22),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-007',21),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-008',20),"
                + "('БВО','Б-МД-004','Б-ВЧ-04','ВР-009',19),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-000',18),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-001',17),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-002',16),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-003',15),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-004',14),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-005',13),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-006',12),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-007',10),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-008',9),"
                + "('БВО','Б-МД-005','Б-ВЧ-05','ВР-009',8),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-000',7),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-001',6),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-002',5),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-003',4),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-004',3),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-005',2),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-006',1),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-007',1),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-008',2),"
                + "('БВО','Б-МД-006','Б-ВЧ-06','ВР-009',3),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-000',4),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-001',5),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-002',6),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-003',7),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-004',8),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-005',9),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-006',10),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-007',11),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-008',12),"
                + "('БВО','Б-МД-007','Б-ВЧ-07','ВР-009',13),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-000',14),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-001',15),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-002',16),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-003',17),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-004',18),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-005',19),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-006',20),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-007',21),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-008',22),"
                + "('БВО','Б-МД-000','Б-ВЧ-08','ВР-009',23),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-000',24),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-001',25),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-002',24),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-003',23),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-004',22),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-005',21),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-006',20),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-007',19),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-008',18),"
                + "('БВО','Б-МД-001','Б-ВЧ-09','ВР-009',17),"
                + "('БВО','Б-МД-002','Б-ВЧ-10','ВР-000',16),"
                + "('БВО','Б-МД-003','Б-ВЧ-11','ВР-001',15),"
                + "('БВО','Б-МД-004','Б-ВЧ-12','ВР-002',14),"
                + "('БВО','Б-МД-004','Б-ВЧ-13','ВР-003',13),"
                + "('БВО','Б-МД-005','Б-ВЧ-14','ВР-004',12),"
                + "('БВО','Б-МД-005','Б-ВЧ-15','ВР-005',11),"
                + "('БВО','Б-МД-006','Б-ВЧ-16','ВР-006',10),"
                + "('БВО','Б-МД-007','Б-ВЧ-17','ВР-007',9),"
                + "('БВО','Б-МД-007','Б-ВЧ-18','ВР-008',8),"
                + "('БВО','Б-МД-003','Б-ВЧ-19','ВР-009',7),"
                + "('БВО','Б-МД-001','Б-ВЧ-20','ВР-008',6),"
                + "('БВО','Б-МД-002','Б-ВЧ-21','ВР-007',5),"
                + "('БВО','Б-МД-003','Б-ВЧ-22','ВР-006',4),"
                + "('БВО','Б-МД-004','Б-ВЧ-23','ВР-005',3),"
                + "('БВО','Б-МД-004','Б-ВЧ-24','ВР-004',2),"
                + "('БВО','Б-МД-005','Б-ВЧ-25','ВР-003',1),"
                + "('БВО','Б-МД-005','Б-ВЧ-26','ВР-002',2),"
                + "('БВО','Б-МД-006','Б-ВЧ-27','ВР-001',3),"
                + "('БВО','Б-МД-003','Б-ВЧ-28','ВР-000',4),"
                + "('БВО','Б-МД-005','Б-ВЧ-29','ВР-001',5)"
                + ";");
        //adding trigger
        db.execSQL("CREATE TRIGGER check_wpn_insert BEFORE INSERT ON " + TABLE_WEAPONRY_ACCOUNTING +
                " WHEN EXISTS(SELECT _id_wpn FROM weaponry_accounting WHERE _id_wpn = NEW._id_wpn AND _id_mu = NEW._id_mu) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_wpn_insert'); " +
                "END;");
        db.execSQL("CREATE TRIGGER check_wpn_update BEFORE UPDATE ON " + TABLE_WEAPONRY_ACCOUNTING +
                " WHEN EXISTS(SELECT _id_wpn FROM weaponry_accounting WHERE _id_wpn = NEW._id_wpn AND _id_mu = NEW._id_mu) " +
                "AND NOT EXISTS(SELECT _id_wpn FROM weaponry_accounting WHERE _id_wpn = NEW._id_wpn AND _id_accounting = NEW._id_accounting) " +
                "BEGIN " +
                " SELECT RAISE(ABORT,'check_wpn_update'); " +
                "END;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MILITARY_DISTRICT);
        onCreate(db);
    }
}
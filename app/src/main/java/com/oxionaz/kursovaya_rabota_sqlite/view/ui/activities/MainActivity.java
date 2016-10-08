package com.oxionaz.kursovaya_rabota_sqlite.view.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.oxionaz.kursovaya_rabota_sqlite.R;
import com.oxionaz.kursovaya_rabota_sqlite.model.DataManager;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.ArmyFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.BuildingFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.CommunityFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.CompanyFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.DepartmentFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.EquipmentAccountingFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.EquipmentCategoryFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.EquipmentFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.MilitaryFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.MilitaryUnitFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.PlatoonFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.RankFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.DislocationPlaceFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.MilitaryDistrictFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.SpecialityAccountingFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.SpecialityFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.SubdivisionDislocationFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_1Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_2Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_3Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_4Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_5Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_6Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_7Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_8Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_9Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_10Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_11Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_12Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.Task_13Fragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.WeaponryAccountingFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.WeaponryCategoryFragment_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments.WeaponryFragment_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static MenuItem checked;

    @ViewById
    public static Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MilitaryDistrictFragment_())
                    .commit();
        }
    }

    @AfterViews
    void ready(){
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        checked = navigationView.getMenu().findItem(R.id.nav_military_district_table).setChecked(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataManager.db.close();
        DataManager.userCursor.close();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (checked != null) checked.setChecked(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.nav_military_district_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new MilitaryDistrictFragment_())
                    .commit();
                break;
            case R.id.nav_dislocation_place_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new DislocationPlaceFragment_())
                        .commit();
                break;
            case R.id.nav_army_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new ArmyFragment_())
                        .commit();
                break;
            case R.id.nav_community_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new CommunityFragment_())
                        .commit();
                break;
            case R.id.nav_military_unit_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new MilitaryUnitFragment_())
                        .commit();
                break;
            case R.id.nav_company_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new CompanyFragment_())
                        .commit();
                break;
            case R.id.nav_platoon_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new PlatoonFragment_())
                        .commit();
                break;
            case R.id.nav_department_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new DepartmentFragment_())
                        .commit();
                break;
            case R.id.nav_rank_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new RankFragment_())
                        .commit();
                break;
            case R.id.nav_military_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new MilitaryFragment_())
                        .commit();
                break;
            case R.id.nav_speciality_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new SpecialityFragment_())
                        .commit();
                break;
            case R.id.nav_equipment_category_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new EquipmentCategoryFragment_())
                        .commit();
                break;
            case R.id.nav_weaponry_category_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new WeaponryCategoryFragment_())
                        .commit();
                break;
            case R.id.nav_equipment_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new EquipmentFragment_())
                        .commit();
                break;
            case R.id.nav_weaponry_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new WeaponryFragment_())
                        .commit();
                break;
            case R.id.nav_building_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new BuildingFragment_())
                        .commit();
                break;
            case R.id.nav_subdivision_dislocation_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new SubdivisionDislocationFragment_())
                        .commit();
                break;
            case R.id.nav_speciality_accounting_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new SpecialityAccountingFragment_())
                        .commit();
                break;
            case R.id.nav_equipment_accounting_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new EquipmentAccountingFragment_())
                        .commit();
                break;
            case R.id.nav_weaponry_accounting_table:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new WeaponryAccountingFragment_())
                        .commit();
                break;
            case R.id.nav_task_1_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_1Fragment_())
                        .commit();
                break;
            case R.id.nav_task_2_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_2Fragment_())
                        .commit();
                break;
            case R.id.nav_task_3_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_3Fragment_())
                        .commit();
                break;
            case R.id.nav_task_4_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_4Fragment_())
                        .commit();
                break;
            case R.id.nav_task_5_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_5Fragment_())
                        .commit();
                break;
            case R.id.nav_task_6_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_6Fragment_())
                        .commit();
                break;
            case R.id.nav_task_7_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_7Fragment_())
                        .commit();
                break;
            case R.id.nav_task_8_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_8Fragment_())
                        .commit();
                break;
            case R.id.nav_task_9_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_9Fragment_())
                        .commit();
                break;
            case R.id.nav_task_10_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_10Fragment_())
                        .commit();
                break;
            case R.id.nav_task_11_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_11Fragment_())
                        .commit();
                break;
            case R.id.nav_task_12_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_12Fragment_())
                        .commit();
                break;
            case R.id.nav_task_13_query:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new Task_13Fragment_())
                        .commit();
                break;
        }
        checked = item;
        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
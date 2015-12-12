package uzap.com.ua.twohourdelivery.activity;

import android.content.Context;
import android.net.MailTo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import uzap.com.ua.twohourdelivery.AppContext;
import uzap.com.ua.twohourdelivery.R;
import uzap.com.ua.twohourdelivery.callback.OnLoadOrderListListener;
import uzap.com.ua.twohourdelivery.callback.OrderListListener;
import uzap.com.ua.twohourdelivery.data.Order;
import uzap.com.ua.twohourdelivery.fragment.CommonFragment;
import uzap.com.ua.twohourdelivery.fragment.FrgCurrentOrder;
import uzap.com.ua.twohourdelivery.fragment.FrgInfo;
import uzap.com.ua.twohourdelivery.fragment.FrgOpenOrder;
import uzap.com.ua.twohourdelivery.fragment.FrgProfile;
import uzap.com.ua.twohourdelivery.task.GetAllOrderTask;
import uzap.com.ua.twohourdelivery.task.TestTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OrderListListener {

    private static final String SELECTED_ITEM_ID = "selected_item_id";

    public static FragmentManager fm;
    private static NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fab;
    private Context context;
    private int mItemSelected;

    public static void showCurrentFragment() {
        fm.beginTransaction().replace(R.id.content_frame, new FrgCurrentOrder()).commit();
        setSelectItemDrawer(1);
    }

    public static void showOpenFragment() {
        fm.beginTransaction().replace(R.id.content_frame, new FrgOpenOrder()).commit();
        setSelectItemDrawer(0);
    }

    public static void setSelectItemDrawer(int i) {
        navigationView.getMenu().getItem(i).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fm = getSupportFragmentManager();
        context = this;

        replaceFragment(new FrgOpenOrder());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                new TestTask(context, MainActivity.this).execute();
//            }
//        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //  mItemSelected = savedInstanceState == null ? 0 : savedInstanceState.getInt(SELECTED_ITEM_ID);
        setSelectItemDrawer(0);
//        mItemSelected = R.id.navigation_item_1;
//        navigate(mItemSelected);
    }

    public void clickFabButton(final OnLoadOrderListListener listListener) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAllOrderTask(listListener, MainActivity.this).execute();
            }
        });
    }

    @Override
    public void orderListener(ArrayList<Order> list) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void navigate(int mSelectedId) {
        if (mSelectedId == R.id.navigation_item_1) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            replaceFragment(new FrgOpenOrder());
        }
        if (mSelectedId == R.id.navigation_item_2) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            replaceFragment(new FrgCurrentOrder());
        }
        if (mSelectedId == R.id.navigation_item_3) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            replaceFragment(new FrgProfile());
        }
        if (mSelectedId == R.id.navigation_item_4) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            replaceFragment(new FrgInfo());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        int id = item.getItemId();
        navigate(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addFragment(CommonFragment f) {
        treatFragment(f, true);
    }

    public void replaceFragment(CommonFragment f) {
        treatFragment(f, false);
    }

    public Fragment getTopFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    private void treatFragment(Fragment f, boolean addToBackStack) {
        String tag = f.getClass().getSimpleName();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.content_frame, f, tag);
        if (addToBackStack) ft.addToBackStack(tag);
        ft.commit();
    }

    public void popFragment() {
        getSupportFragmentManager().popBackStack();
    }

    public void popAllFragments() {
        getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void goneFab() {
        fab.setVisibility(View.GONE);
    }

    public void showFab() {
        fab.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppContext.getWritableDatabase().deleteOrderTable();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mItemSelected);
    }
}

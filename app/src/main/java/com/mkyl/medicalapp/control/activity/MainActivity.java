package com.mkyl.medicalapp.control.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.mkyl.medicalapp.R;
import com.mkyl.medicalapp.control.fragment.DoctorFragment;
import com.mkyl.medicalapp.control.fragment.HomeFragment;
import com.mkyl.medicalapp.control.fragment.HospitalFragment;
import com.mkyl.medicalapp.control.fragment.MyFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_home)
    RadioButton mainHome;
    @BindView(R.id.main_doctor)
    RadioButton mainDoctor;
    @BindView(R.id.main_hospital)
    RadioButton mainHospital;
    @BindView(R.id.main_me)
    RadioButton mainMe;
    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    private Fragment fg;
    HomeFragment homeFragment;
    DoctorFragment doctorFragment;
    HospitalFragment hospitalFragment;
    MyFragment myFragment;
    private RadioGroup mRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        addFragment(homeFragment);
        initView();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.main_home:
                        showFragment(homeFragment);
                        fg = homeFragment;
                        break;
                    case R.id.main_doctor:
                        if (doctorFragment == null) {
                            doctorFragment = new DoctorFragment();
                            addFragment(doctorFragment);
                        }
                        showFragment(doctorFragment);
                        fg = doctorFragment;
                        break;
                    case R.id.main_hospital:
                        if (hospitalFragment == null) {
                            hospitalFragment = new HospitalFragment();
                            addFragment(hospitalFragment);
                        }
                        showFragment(hospitalFragment);
                        fg = hospitalFragment;
                        break;
                    case R.id.main_me:
                        if (myFragment == null) {
                            myFragment = new MyFragment();
                            addFragment(myFragment);
                        }
                        showFragment(myFragment);
                        fg = myFragment;
                        break;
                }
            }
        });
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_group);
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame, fragment);
        transaction.commit();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fg != null) {
            transaction.hide(fg);
        }
        transaction.show(fragment);
        transaction.commit();
        fg = null;
    }
}

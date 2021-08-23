package com.unizen.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class GPACalculatorFragment extends Fragment {

    /**
     * Fragment corresponding to GPA Calculator item in Navigation Drawer
     **/

    LinearLayout layoutList;
    Button buttonAdd, buttonCalc;

    List<String> courseList = new ArrayList<>();
    ArrayList<Course> courseArrayList = new ArrayList<>();

    List<EditText> namesList = new ArrayList<EditText>();
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gpa_calculator, container, false);
        layoutList = view.findViewById(R.id.layout_list);
        buttonAdd = view.findViewById(R.id.button_add);
        buttonCalc = view.findViewById(R.id.button_calc);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndCalculate();
            }
        });

        return view;
    }

    private void validateAndCalculate() {
        // Validate text entry fields and calculate gpa
        courseArrayList.clear();
        boolean result = true;

        for(int i=0; i<layoutList.getChildCount(); i++) {
            View courseView = layoutList.getChildAt(i);

            EditText courseName = (EditText) courseView.findViewById(R.id.edit_course_name);
            EditText courseCredits = (EditText) courseView.findViewById(R.id.edit_course_credit);
            AppCompatSpinner spinnerGrade = (AppCompatSpinner) courseView.findViewById(R.id.spinner_grade);

            Course course = new Course();

            if(!courseName.getText().toString().equals("")) {
                course.setCourseName(courseName.getText().toString());
            }
            else
            {
                result = false;
                break;
            }

            if(!courseCredits.getText().toString().equals("")) {
                course.setCourseCredits(Integer.parseInt(courseCredits.getText().toString()));
            }
            else {
                result = false;
                break;
            }

            if(spinnerGrade.getSelectedItemPosition()!=0) {
                course.setGrade(spinnerGrade.getSelectedItem().toString());
            }
            else {
                result = false;
                break;
            }

            courseArrayList.add(course);
        }

        if(layoutList.getChildCount()==0) {
            result = false;
            Toast.makeText(getActivity(), "Please add a course", Toast.LENGTH_SHORT).show();
        }
        else if (!result) {
            Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            int sumCredits = 0;
            int weightedPoints = 0;
            for(int i=0; i<courseArrayList.size(); i++) {
                String name = courseArrayList.get(i).getCourseName();
                int credits = courseArrayList.get(i).getCourseCredits();
                String grade = courseArrayList.get(i).getGrade();
                int points = 0;
                switch(grade) {
                    case "S":
                        points = 10;
                        break;
                    case "A":
                        points = 9;
                        break;
                    case "B":
                        points = 8;
                        break;
                    case "C":
                        points = 7;
                        break;
                    case "D":
                        points = 6;
                        break;
                    case "E":
                        points = 5;
                        break;
                    case "F":
                        points = 0;
                        credits = 0;
                        break;
                }
                sumCredits += credits;
                weightedPoints += credits*points;
            }

            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Your GPA is " + Math.round((float) weightedPoints/sumCredits *100.0)/100.0);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DISMISS",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    private void addView() {
        // Add a course dynamically to list of courses
        View courseView = getLayoutInflater().inflate(R.layout.row_add_course, null, false);

        EditText courseName = (EditText) courseView.findViewById(R.id.edit_course_name);
        EditText courseCredits = (EditText) courseView.findViewById(R.id.edit_course_credit);
        AppCompatSpinner spinnerGrade = (AppCompatSpinner) courseView.findViewById(R.id.spinner_grade);
        ImageView imageClose = (ImageView) courseView.findViewById(R.id.image_remove);

        String[] items = new String[]{
                " ", "S", "A", "B", "C", "D", "E", "F",
        };

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        spinnerGrade.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            // Remove a course from list of courses
            @Override
            public void onClick(View view) {
                removeView(courseView);
            }
        });

        layoutList.addView(courseView);

    }

    private void removeView(View view) {
        layoutList.removeView(view);
    }
}

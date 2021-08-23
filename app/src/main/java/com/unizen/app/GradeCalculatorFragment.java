package com.unizen.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class GradeCalculatorFragment extends Fragment {

    /**
     * Fragment corresponding to Grade Calculator item in Navigation Drawer
     **/

    EditText test1, test2, test3, quiz1, quiz2, quiz3, el, lab;
    Button calcGrade;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade_calculator, container, false);
        test1 = view.findViewById(R.id.test1);
        test2 = view.findViewById(R.id.test2);
        test3 = view.findViewById(R.id.test3);
        quiz1 = view.findViewById(R.id.quiz1);
        quiz2 = view.findViewById(R.id.quiz2);
        quiz3 = view.findViewById(R.id.quiz3);
        el = view.findViewById(R.id.el_marks);
        lab = view.findViewById(R.id.lab_marks);
        calcGrade = view.findViewById(R.id.calc_grade);

        calcGrade.setOnClickListener(new View.OnClickListener() {
            // Logic to calculate grades upon clicking calculate button
            @Override
            public void onClick(View view) {
                if(test1.getText().toString().equals("") || test2.getText().toString().equals("") || test3.getText().toString().equals("") || quiz1.getText().toString().equals("") || quiz2.getText().toString().equals("") || quiz3.getText().toString().equals("") || el.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if(lab.getText().toString().equals("")) {

                    float score = (Float.parseFloat(test1.getText().toString()) + Float.parseFloat(test2.getText().toString()) +
                            Float.parseFloat(test3.getText().toString()))/3 + Float.parseFloat(quiz1.getText().toString()) +
                            Float.parseFloat(quiz2.getText().toString()) +
                            Float.parseFloat(quiz3.getText().toString()) + Float.parseFloat(el.getText().toString());

                    char grade;
                    if(score>=90.0 && score<=100.0)
                        grade = 'S';
                    else if(score>=80.0 && score<90.0)
                        grade = 'A';
                    else if(score>=70.0 && score<80.0)
                        grade = 'B';
                    else if(score>=60.0 && score<70.0)
                        grade = 'C';
                    else if(score>=50.0 && score<60.0)
                        grade = 'D';
                    else if(score>=40.0 && score<50.0)
                        grade = 'E';
                    else
                        grade = 'F';

                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Your grade is " + grade);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DISMISS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    float score = (Float.parseFloat(test1.getText().toString()) + Float.parseFloat(test2.getText().toString()) +
                            Float.parseFloat(test3.getText().toString()))/3 + Float.parseFloat(quiz1.getText().toString()) +
                            Float.parseFloat(quiz2.getText().toString()) + Float.parseFloat(quiz3.getText().toString()) +
                            Float.parseFloat(el.getText().toString()) + Float.parseFloat(lab.getText().toString());

                    char grade;
                    if(score>=135.0 && score<=150.0)
                        grade = 'S';
                    else if(score>=120.0 && score<135.0)
                        grade = 'A';
                    else if(score>=105.0 && score<120.0)
                        grade = 'B';
                    else if(score>=90.0 && score<105.0)
                        grade = 'C';
                    else if(score>=75.0 && score<90.0)
                        grade = 'D';
                    else if(score>=60.0 && score<75.0)
                        grade = 'E';
                    else
                        grade = 'F';

                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Your grade is " + grade);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DISMISS",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        return view;
    }
}

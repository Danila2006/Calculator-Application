package com.example.calculatorapp;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.ExpressionBuilder;

import org.mariuszgromada.math.mxparser.Expression;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity {

    private ImageButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonMinus, buttonPlus, buttonC, buttonEqual, buttonDivide, buttonPercent, buttonDelLeft, buttonDot, buttonMenu, buttonMultiply, buttonRoot, buttonLeftBracket, buttonRightBracket;
    private TextView textResult, textSolution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSolution = findViewById(R.id.text_solution);
        textResult = findViewById(R.id.text_result);

        button0 = findViewById(R.id.button_zero);
        button1 = findViewById(R.id.button_one);
        button2 = findViewById(R.id.button_two);
        button3 = findViewById(R.id.button_three);
        button4 = findViewById(R.id.button_four);
        button5 = findViewById(R.id.button_five);
        button6 = findViewById(R.id.button_six);
        button7 = findViewById(R.id.button_seven);
        button8 = findViewById(R.id.button_eight);
        button9 = findViewById(R.id.button_nine);
        buttonMinus = findViewById(R.id.button_minus);
        buttonPlus = findViewById(R.id.button_plus);
        buttonC = findViewById(R.id.button_c);
        buttonEqual = findViewById(R.id.button_equal);
        buttonDivide = findViewById(R.id.button_divide);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonPercent = findViewById(R.id.button_percent);
        buttonDelLeft = findViewById(R.id.button_left);
        buttonDot = findViewById(R.id.button_dot);
        buttonMenu = findViewById(R.id.button_menu);


        buttonRoot = findViewById(R.id.button_root);
        buttonLeftBracket = findViewById(R.id.button_left_bracket);
        buttonRightBracket = findViewById(R.id.button_right_bracket);
//numbers
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonRoot.getVisibility() == View.GONE) {
                    buttonRoot.setVisibility(View.VISIBLE);
                } else {
                    buttonRoot.setVisibility(View.GONE);
                }

                if (buttonLeftBracket.getVisibility() == View.GONE) {
                    buttonLeftBracket.setVisibility(View.VISIBLE);
                } else {
                    buttonLeftBracket.setVisibility(View.GONE);
                }

                if (buttonRightBracket.getVisibility() == View.GONE) {
                    buttonRightBracket.setVisibility(View.VISIBLE);
                } else {
                    buttonRightBracket.setVisibility(View.GONE);
                }

                float density = getResources().getDisplayMetrics().density;
                RelativeLayout.LayoutParams paramsC = (RelativeLayout.LayoutParams) buttonC.getLayoutParams();
                RelativeLayout.LayoutParams paramsDelLeft = (RelativeLayout.LayoutParams) buttonDelLeft.getLayoutParams();
                RelativeLayout.LayoutParams paramsPercent = (RelativeLayout.LayoutParams) buttonPercent.getLayoutParams();
                RelativeLayout.LayoutParams paramsDivide = (RelativeLayout.LayoutParams) buttonDivide.getLayoutParams();

                RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) button4.getLayoutParams();
                RelativeLayout.LayoutParams params5 = (RelativeLayout.LayoutParams) button5.getLayoutParams();
                RelativeLayout.LayoutParams params6 = (RelativeLayout.LayoutParams) button6.getLayoutParams();
                RelativeLayout.LayoutParams paramsMinus = (RelativeLayout.LayoutParams) buttonMinus.getLayoutParams();

                RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) button1.getLayoutParams();
                RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) button2.getLayoutParams();
                RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) button3.getLayoutParams();
                RelativeLayout.LayoutParams paramsPlus = (RelativeLayout.LayoutParams) buttonPlus.getLayoutParams();

                if (buttonRoot.getVisibility() == View.VISIBLE) {
                    paramsC.addRule(RelativeLayout.BELOW, R.id.button_root);
                    paramsC.topMargin = 0;

                    paramsDelLeft.addRule(RelativeLayout.BELOW, R.id.button_root);
                    paramsDelLeft.topMargin = 0;

                    paramsPercent.addRule(RelativeLayout.BELOW, R.id.button_root);
                    paramsPercent.topMargin = 0;

                    paramsDivide.addRule(RelativeLayout.BELOW, R.id.button_root);
                    paramsDivide.topMargin = 0;

                    buttonC.setLayoutParams(paramsC);
                    buttonDelLeft.setLayoutParams(paramsDelLeft);
                    buttonPercent.setLayoutParams(paramsPercent);
                    buttonDivide.setLayoutParams(paramsDivide);
                } else if (buttonRoot.getVisibility() == View.GONE) {
                    paramsC.removeRule(RelativeLayout.BELOW);
                    paramsC.topMargin = (int) (70 * density);

                    paramsDelLeft.removeRule(RelativeLayout.BELOW);
                    paramsDelLeft.topMargin = (int) (70 * density);

                    paramsPercent.removeRule(RelativeLayout.BELOW);
                    paramsPercent.topMargin = (int) (70 * density);

                    paramsDivide.removeRule(RelativeLayout.BELOW);
                    paramsDivide.topMargin = (int) (70 * density);

                    buttonC.setLayoutParams(paramsC);
                    buttonDelLeft.setLayoutParams(paramsDelLeft);
                    buttonPercent.setLayoutParams(paramsPercent);
                    buttonDivide.setLayoutParams(paramsDivide);
                }
            }
        });

        buttonRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "√(";
                textSolution.setText(newText);
            }
        });

        buttonLeftBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "(";
                textSolution.setText(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        buttonRightBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + ")";
                textSolution.setText(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = "";
                textSolution.setText(newText);
                textResult.setText(newText);
            }
        });

        buttonDelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();

                if(!currentText.isEmpty()) {
                    String newText = currentText.substring(0, currentText.length() - 1);
                    textSolution.setText(newText);
                }
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleArithmetic(textSolution.getText().toString(), ".");
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleArithmetic(textSolution.getText().toString(), "+");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleArithmetic(textSolution.getText().toString(), "-");
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleArithmetic(textSolution.getText().toString(), "÷");
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleArithmetic(textSolution.getText().toString(), "×");
            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doubleArithmetic(textSolution.getText().toString(), "%");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String solution = textSolution.getText().toString();
                    if (solution.contains("√")) {
                        String newText = "";
                        preliminaryResult(textSolution.getText().toString());
                        String result = textResult.getText().toString();
                        textSolution.setText(ifThereIsDot(result));
                        textResult.setText(newText);

                        ifGood(textSolution.getText().toString());
                    } else {
                        String newText = "";
                        String currentText = textResult.getText().toString();
                        textSolution.setText(currentText);
                        textResult.setText(newText);

                        ifGood(textSolution.getText().toString());
                    }
                } catch (Exception e) {
                    String text = "Something went wrong";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "0";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "1";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "2";
                textSolution.setText(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "3";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "4";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "5";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "6";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "7";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "8";
                textSolution.setText(newText);
                //getResult(newText);
                //preliminaryResult(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textSolution.getText().toString();
                String newText = currentText + "9";
                textSolution.setText(newText);
                ifThereIsAroot(textSolution.getText().toString());
            }
        });
    }

    public void preliminaryResult(String nums) {
        try {
            boolean hasRoot = nums.contains("√");

            if (nums.endsWith("+") || nums.endsWith("-") || nums.endsWith("×") || nums.endsWith("÷")) {
                textResult.setText("");
                return;
            }

            if (hasRoot) {
                thereIsAroot(nums);
            } else {
                textResult.setText(ifThereIsDot(calculationResult(nums)));
            }
        } catch (ArithmeticException ae) {
            String text = "Something went wrong";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    }

    public String calculationResult(String nums) {
        nums = nums.replaceAll("×", "*");
        nums = nums.replaceAll("÷", "/");
        Expression exp = new Expression(nums);
        String result = String.valueOf(exp.calculate());
        return result;
    }

    public String result(String expression) {
        try {
            net.objecthunter.exp4j.Expression e = new ExpressionBuilder(expression).build();
            double result = e.evaluate();
            textResult.setText(String.valueOf(result));
            return String.valueOf(result);
        } catch (ArithmeticException ex) {
            textResult.setText("Error");
            return "Error";
        }
    }

    public void thereIsAroot(String line) {
        line = line.replaceAll("√", "sqrt");
        result(line);
    }

   public void ifThereIsAroot(String nums) {
       boolean hasRoot = nums.contains("√");

       if (!hasRoot) {
           preliminaryResult(nums);
       } else {
           String newText = "";
           textResult.setText(newText);
       }

       ifGood(nums);
   }

   public String ifThereIsDot(String nums) {
        if (nums.contains(".")) {
            char[] charArr = nums.toCharArray();
            int indexDot = 0;
            for (int i = 0; i < charArr.length; i++) {
                if (charArr[i] == '.') {
                    indexDot = i;
                    break;
                }
            }

            int counOfNums = charArr.length - indexDot;
            if (counOfNums > 10) {
                BigDecimal bd = new BigDecimal(nums);
                bd = bd.setScale(10, RoundingMode.HALF_UP);
                return bd.toString();
            } else {
                return nums;
            }
        } else {
            return nums;
        }
   }

   public void ifGood(String nums) {
       Paint paint = new Paint();
       paint.set(textSolution.getPaint());

       float textSize = textSolution.getTextSize();
       int widthSol = textSolution.getWidth();

       int widthNums = (int) paint.measureText(nums);

       if (widthNums > widthSol) {
           while (widthNums > widthSol) {
               textSize -= 1;
               textSolution.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
               paint.setTextSize(textSize);
               widthNums = (int) paint.measureText(nums);
           }
       } else {
           float textSizeDp = 45f;
           float textSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, textSizeDp, textSolution.getResources().getDisplayMetrics());
           textSolution.setTextSize(textSizePx);
       }
   }

   public void doubleArithmetic(String nums, String sign) {
        char[] charArr = nums.toCharArray();
        int znak = charArr.length - 1;
        if (charArr.length > 0 && (charArr[znak] == '+' || charArr[znak] == '-' || charArr[znak] == '×' || charArr[znak] == '÷')) {
            String newText = nums + "";
            textSolution.setText(newText);

            ifGood(textSolution.getText().toString());
        } else {
            String newText = nums + sign;
            textSolution.setText(newText);
            ifThereIsAroot(textSolution.getText().toString());
        }
   }
}
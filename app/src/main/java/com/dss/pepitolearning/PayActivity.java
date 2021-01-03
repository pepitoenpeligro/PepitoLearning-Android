package com.dss.pepitolearning;

import android.animation.Animator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.dss.pepitolearning.ui.homeUI.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PayActivity extends AppCompatActivity {

    LottieAnimationView animacionPay;
    AppCompatButton proced_to_pay;


    private ImageView goBack;


    private Calendar globalCalendar;
    private EditText dateEdit;
    private EditText nameEdit;
    private EditText numberEdit;
    private EditText cvvEdit;

    private TextSwitcher nameView,numberView,cvvView,dateView;

    private EditText[] editTexts;
    private TextSwitcher[] textSwitchers;
    private DatePickerDialog.OnDateSetListener actualDatePicked;
    private ConstraintLayout cardFront,cardBack;

    private String cardNumber;
    private String cvv;

    private int expiryMonth;
    private int expiryYear;

    private int count = 0;

    private boolean dateFilled = false;
    private boolean cvcFilled = false;

    public void initWindowColor(){
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.button_color));
    }

    public void initUI(){
        initWindowColor();
        globalCalendar = Calendar.getInstance();
        dateEdit = findViewById(R.id.date);
        nameEdit = findViewById(R.id.name);
        numberEdit = findViewById(R.id.number);
        cvvEdit = findViewById(R.id.cvv);

        cvvEdit.setOnFocusChangeListener(cvvFocus);

        cardFront = findViewById(R.id.card_front);
        cardBack = findViewById(R.id.card_back);


        nameView = findViewById(R.id.card_holder);
        numberView = findViewById(R.id.card_number);
        cvvView = findViewById(R.id.card_cvv);
        dateView = findViewById(R.id.card_date);

        editTexts = new EditText[] {nameEdit,numberEdit,cvvEdit,dateEdit};
        textSwitchers = new TextSwitcher[] {nameView,numberView,cvvView,dateView};


        for(EditText editText : editTexts){
            editText.addTextChangedListener(textWatcher(editText));
        }


        for(TextSwitcher textSwitcher : textSwitchers){
            textSwitcher.setInAnimation(this, R.anim.slide_up_card);
            textSwitcher.setOutAnimation(this, R.anim.slide_down_card);
        }


        actualDatePicked = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                globalCalendar.set(Calendar.YEAR, year);
                globalCalendar.set(Calendar.MONTH, monthOfYear);
                globalCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                dateFilled = true;

            }

        };

        dateEdit.setOnClickListener(popUpCalender);


        goBack = findViewById(R.id.iv_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)v.getContext()).onBackPressed();
                HomeFragment.bajaPanel();
            }
        });




        proced_to_pay = findViewById(R.id.proced_to_pay);

        proced_to_pay.setClickable(false);
        proced_to_pay.setAlpha((float)0.3);

        proced_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir modal, mostart que ha ido bien la operacion y volver atras

                Dialog mDialog = new Dialog(((Activity)v.getContext()));
                mDialog.setContentView(R.layout.popup);

                LottieAnimationView animation_popup = (LottieAnimationView) mDialog.findViewById(R.id.animationcheck);
                LottieCompositionFactory.fromAsset(((Activity)v.getContext()).getApplicationContext(), "tpv.json").addListener(new LottieListener<LottieComposition>() {
                    @Override
                    public void onResult(LottieComposition result) {

                        mDialog.show();

                        animation_popup.setComposition(result);
                        animation_popup.setVisibility(View.VISIBLE);
                        animation_popup.playAnimation();

                        animation_popup.addAnimatorListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                                animation_popup.setVisibility(View.VISIBLE);
                                mDialog.show();
                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                animation_popup.setVisibility(View.GONE);
                                mDialog.dismiss();

                                /*if(resultadoOperacionLogin){
                                    Intent goToHome = new Intent(activity, NavigationDrawerActivity.class);
                                    goToHome.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    activity.startActivity(goToHome);
                                }*/

                                ((Activity)v.getContext()).onBackPressed();
                                HomeFragment.bajaPanel();


                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });


                    }
                });



            }
        });
    }

    View.OnClickListener popUpCalender = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            cvvEdit.clearFocus();
            new DatePickerDialog(PayActivity.this, getDate(), getCalendar()
                    .get(Calendar.YEAR), getCalendar().get(Calendar.MONTH),
                    getCalendar().get(Calendar.DAY_OF_MONTH)).show();
        }
    };


    private void updateLabel() {

        String myFormat = "MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateEdit.setText(sdf.format(globalCalendar.getTime()));
    }


    View.OnFocusChangeListener cvvFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            rotateCard(hasFocus);
        }
    };

    private void rotateCard(boolean isBackTurn){
        if (isBackTurn) {
            cardBack.setAlpha(1);
            DebitCardAnimatorHelper.flipView(getApplicationContext(), cardBack, cardFront, true);
        } else {
            cardFront.setAlpha(1);
            DebitCardAnimatorHelper.flipView(getApplicationContext(), cardBack, cardFront, false);
        }

    }

    private TextWatcher textWatcher(final EditText editText){
        return new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(editText==numberEdit){
                    setCardNumber();

                    TextView tv = (TextView) numberView.getCurrentView();


                    numberView.setText(String.format("%s",s.length() > 0 ? s.toString() : "#### #### #### ####"));

                }else if(editText==dateEdit){
                    dateView.setText(String.format("%s",s.length() > 0 ? s : "MM/YY"));
                    if(s.length() > 0){
                        String[] mthYr = s.toString().split("/");
                        setExpiryMonth(Integer.valueOf(mthYr[0]));
                        setExpiryYear(Integer.valueOf(mthYr[1]));
                    }
                }else if(editText==nameEdit){
                    nameView.setText(String.format("%s",s.length() > 0 ? s : "Card Name"));
                }else if(editText==cvvEdit){
                    cvvView.setText(String.format("%s",s.length() > 0 ? s : "000"));
                    setCvv(s.toString());
                    if(s.length()>2){
                        cvcFilled = true;
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {

                if(editText==numberEdit){
                    if (count <= editText.getText().toString().length()
                            &&(editText.getText().toString().length()==4
                            ||editText.getText().toString().length()==9
                            ||editText.getText().toString().length()==14
                            ||editText.getText().toString().length()==19)){
                        editText.setText(String.format("%s ",editText.getText().toString()));
                        int pos = editText.getText().length();
                        editText.setSelection(pos);
                    }else if (count >= editText.getText().toString().length()
                            &&(editText.getText().toString().length()==4
                            ||editText.getText().toString().length()==9
                            ||editText.getText().toString().length()==14
                            ||editText.getText().toString().length()==19)){
                        editText.setText(editText.getText().toString().substring(0,editText.getText().toString().length()-1));
                        int pos = editText.getText().length();
                        editText.setSelection(pos);
                    }
                    count = editText.getText().toString().length();
                }

                if(dateFilled && cvcFilled){
                    Log.d("[onTextChanged]", "Ya podemos habilitar el boton de pago");
                    proced_to_pay.setAlpha((float)1);
                    proced_to_pay.setClickable(true);
                }

            }
        };
    }

    public Calendar getCalendar() {
        return globalCalendar;
    }

    public DatePickerDialog.OnDateSetListener getDate() {
        return actualDatePicked;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber() {
        String cardNo = numberEdit.getText().toString().replace(" ", "");

        this.cardNumber = String.format("%-16s", cardNo ).replace(' ', '0');
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();










        /*animacionPay = findViewById(R.id.animation_go_to_pay2);

        LottieCompositionFactory.fromAsset(getApplicationContext(), "card_to_pay.json").addListener(new LottieListener<LottieComposition>() {
            @Override
            public void onResult(LottieComposition result) {



                animacionPay.setComposition(result);
                animacionPay.setVisibility(View.VISIBLE);
                animacionPay.playAnimation();


                animacionPay.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                        animacionPay.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        animacionPay.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }
        });*/

    }
}
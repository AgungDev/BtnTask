package fun5i.module.radiobtndaily;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RadioBtnDaily extends FrameLayout {


    public interface OnChecked{
        void pressed(int checked);
    }
    private OnChecked onChecked;
    public void setChackedBtn(OnChecked c){
        onChecked = c;
    }

    private static final String TAG = "RadioBtnDaily";
    private static final int CLEAR_ANIMATION_TIMER = 1000;

    private int ANIMATION_DELAY = 200;
    private int PILIHAN = KOSONG; //null
    private String TEXT = "Daily Checked";

    public static final  int KOSONG = -1;
    public static final  int NANTI = 1;
    public static final  int BATAL = 2;
    public static final  int OK = 3;


    public void setText(String text){
        TEXT = text;
        tv.setText(text);
    }

    public String[] TEXT_CHEKED = {
            "Nanti",
            "Batal",
            "Ok"
    };

    public void setTextColor(int color){
        tv.setTextColor(color);
    }

    public void showCheck(){
        cb.setVisibility(VISIBLE);
        cb.setChecked(false);
        PILIHAN = KOSONG;
        tv.setText(TEXT);
        ObjectAnimator animXTv = ObjectAnimator.ofFloat(tv, "x", cb.getWidth()+(cb.getWidth()/10));
        animXTv.start();
    }

    private View view;
    private CheckBox cb;
    private TextView tv;
    private ImageView nanti, close, check;
    private RelativeLayout relativeLayout;

    private int SIZE = 25;

    public RadioBtnDaily(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new FrameLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        ));

        view = getRootView();
        view = inflate(context,R.layout.checkbox_layout, null);

        cb = view.findViewById(R.id.cb);
        tv = view.findViewById(R.id.tv);
        nanti = view.findViewById(R.id.nanti);
        close = view.findViewById(R.id.close);
        check = view.findViewById(R.id.check);
        relativeLayout = view.findViewById(R.id.rel);

        tv.setText(TEXT);

        addView(view);


        cb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxClicked((int) cb.getX());
            }
        });


    }

    private void animObjectClose(ImageView object){
        ObjectAnimator animXIcn = ObjectAnimator.ofFloat(object, "x", 0);
        animXIcn.start();

        ObjectAnimator animXTv = ObjectAnimator.ofFloat(tv, "x", cb.getWidth()+(cb.getWidth()/10));
        animXTv.start();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                object.setVisibility(GONE);
                ObjectAnimator animXTv = ObjectAnimator.ofFloat(tv, "x", 0);
                animXTv.start();
            }
        }, CLEAR_ANIMATION_TIMER);*/
    }

    private void onOpenAllObject(){
        nanti.setVisibility(VISIBLE);
        close.setVisibility(VISIBLE);
        check.setVisibility(VISIBLE);

        post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator n,cl,ch,tt;
                int marginA = cb.getWidth();
                int marginB = (marginA/2)+marginA/5;
                n = ObjectAnimator.ofFloat(nanti, "x", marginA);
                cl = ObjectAnimator.ofFloat(close, "x", marginA);
                ch = ObjectAnimator.ofFloat(check, "x", marginA);
                tt = ObjectAnimator.ofFloat(tv, "x", marginA+marginB+marginB+marginA);

                n.start();
                cl.start();
                ch.start();
                tt.start();

                //1
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator cl1, ch1;

                        cl1 = ObjectAnimator.ofFloat(close, "x", marginA+marginB);
                        ch1 = ObjectAnimator.ofFloat(check, "x", marginA+marginB);

                        cl1.start();
                        ch1.start();
                    }
                },ANIMATION_DELAY/4);

                //; 2
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator ch2 = ObjectAnimator.ofFloat(check, "x", marginA+marginB+marginB);
                        ch2.start();
                    }
                }, ANIMATION_DELAY);

            }
        });



    }

    private void onCloseAllObject(){
        post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator n,cl,ch,tt;
                int marginA = cb.getWidth();
                int marginB = (marginA/2)+marginA/5;
                n = ObjectAnimator.ofFloat(nanti, "x", 0);
                cl = ObjectAnimator.ofFloat(close, "x", 0);
                ch = ObjectAnimator.ofFloat(check, "x", 0);
                tt = ObjectAnimator.ofFloat(tv, "x", marginA);

                n.start();
                cl.start();
                ch.start();
                tt.start();

                nanti.setVisibility(GONE);
                close.setVisibility(GONE);
                check.setVisibility(GONE);
            }
        });

    }

    public boolean kondisi(int checked){
        if (checked == NANTI || checked == BATAL || checked == OK){
            return true;
        }else{
            return false;
        }
    }

    private void checkBoxClicked(int x){
        if (cb.isChecked()){

            onOpenAllObject();
            nanti.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.setVisibility(GONE);
                    close.setVisibility(GONE);
                    check.setVisibility(GONE);

                    animObjectClose(nanti);
                    PILIHAN = NANTI;
                    onChecked.pressed(PILIHAN);
                }
            });

            close.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.setVisibility(GONE);
                    nanti.setVisibility(GONE);
                    check.setVisibility(GONE);

                    animObjectClose(close);
                    PILIHAN = BATAL;
                    onChecked.pressed(PILIHAN);
                }
            });

            check.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.setVisibility(GONE);
                    close.setVisibility(GONE);
                    nanti.setVisibility(GONE);

                    animObjectClose(check);
                    PILIHAN = OK;
                    onChecked.pressed(PILIHAN);
                }
            });

        }else{
            onCloseAllObject();
        }
    }

    public int isChecked(){
        return PILIHAN;
    }

}

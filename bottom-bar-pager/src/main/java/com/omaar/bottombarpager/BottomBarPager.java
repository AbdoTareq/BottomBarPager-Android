package com.omaar.bottombarpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import static android.graphics.Color.parseColor;

/**
 * The Class that inflate the UI and accepts the data in the form of BottomBarPagerDataHolder object
 */
@RequiresApi(23)
public class BottomBarPager extends LinearLayout {

    // material_deep_teal_500
    static final int DEFAULT_TEXT = parseColor("#000000");
    static final int DEFAULT_TEXT_SELECTED = parseColor("#FFFFFF");
    static final int DEFAULT_BACKGROUND = parseColor("#EEEEEE");
    static final int DEFAULT_BACKGROUND_SELECTED = parseColor("#CCCCCC");

    //core fields
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    //create int for the styles attribute given from the xml file
    private int bar_background;
    private int selected_tab_background;
    private int text_color;
    private int selected_text_color;

    //create relative layouts for each tab
    private RelativeLayout tab1;
    private RelativeLayout tab2;
    private RelativeLayout tab3;

    //create text views for each tab
    private TextView tab1_textview;
    private TextView tab2_textview;
    private TextView tab3_textview;

    //create image views for each tab
    private ImageView tab1_imageview;
    private ImageView tab2_imageview;
    private ImageView tab3_imageview;

    //create animated vector drawables for each tab
    private AnimatedVectorDrawable tab1_vector;
    private AnimatedVectorDrawable tab2_vector;
    private AnimatedVectorDrawable tab3_vector;

    //create the view pager object
    private ViewPager viewPager;

    //first default constructor
    public BottomBarPager(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    //second default constructor
    public BottomBarPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        initView();
    }

    //third default constructor
    public BottomBarPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    /**
     * A method that accepts a BottomBarPagerDataHolder object that contains all the data for this
     * bottom app bar including(vectors, titles, viewpager and fragments ), called to initialize the bar with these values
     */
    public void initializeAppBar(BottomBarPagerDataHolder dataHolder) {

        /**initialize the vectors**/

        if (dataHolder.getTab1_vector() == null)
            throw new NullPointerException("No Animated Vector found for first tab");
        else
            tab1_vector = dataHolder.getTab1_vector();

        if (dataHolder.getTab2_vector() == null)
            throw new NullPointerException("No Animated Vector found for second tab");
        else
            tab2_vector = dataHolder.getTab2_vector();

        if (dataHolder.getTab3_vector() == null)
            throw new NullPointerException("No Animated Vector found for third tab");
        else
            tab3_vector = dataHolder.getTab3_vector();


        //set the titles

        if (dataHolder.getTab1_title() == null)
            tab1_textview.setVisibility(GONE);
        else
            tab1_textview.setText(dataHolder.getTab1_title());

        if (dataHolder.getTab2_title() == null)
            tab2_textview.setVisibility(GONE);
        else
            tab2_textview.setText(dataHolder.getTab2_title());

        if (dataHolder.getTab3_title() == null)
            tab3_textview.setVisibility(GONE);
        else
            tab3_textview.setText(dataHolder.getTab3_title());

        //create and initialize a view pager adapter object with the three fragments
        ViewPagerAadpter viewPagerAadpter = new ViewPagerAadpter(((AppCompatActivity) mContext).getSupportFragmentManager(),
                dataHolder.getFragment1(), dataHolder.getFragment2(), dataHolder.getFragment3());

        //setup the viewpager with the adapter
        viewPager.setAdapter(viewPagerAadpter);

        //initialize the listeners for tabs click and view pager current fragment changes
        initListeners();

        //initialize with the first fragment
        viewPager.setCurrentItem(0);

        //initialize tabs backgrounds according to the selected tab (tab 0)
        setSelectedTabBackground(0);

        //initialize tabs text colors according to the selected tab (tab 0)
        setTextColor(0);

        //initialize and reset icons according to the selected tab (tab 0)
        animateIcons(0);

    }

    /**
     * A method called in the constructor to initialize the custom views
     */
    private void initView() {
        this.view = this;

        //Inflating the XML view
        inflate(mContext, R.layout.bottom_bar_pager, this);

        //initialize the view pager
        viewPager = findViewById(R.id.viewpager);

        //initialize the tabs
        tab1 = findViewById(R.id.layout1);
        tab2 = findViewById(R.id.layout2);
        tab3 = findViewById(R.id.layout3);

        //initialize the text views
        tab1_textview = findViewById(R.id.textView1);
        tab2_textview = findViewById(R.id.textView2);
        tab3_textview = findViewById(R.id.textView3);

        //initialize the image views
        tab1_imageview = findViewById(R.id.imageView1);
        tab2_imageview = findViewById(R.id.imageView2);
        tab3_imageview = findViewById(R.id.imageView3);

        //get the style arrays
        TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.BottomBarPager,
                styleAttr, 0);

        //get the bar background color
        try {
            bar_background = arr.getColor(R.styleable.BottomBarPager_bar_background, DEFAULT_BACKGROUND);

        } catch (NullPointerException e) {
            bar_background = DEFAULT_BACKGROUND;
        }

        //get the selected tab background color
        try {
            selected_tab_background = arr.getColor(R.styleable.BottomBarPager_selected_tab_background, DEFAULT_BACKGROUND_SELECTED);
        } catch (NullPointerException e) {
            selected_tab_background = DEFAULT_BACKGROUND_SELECTED;
        }

        //get the text color
        try {
            text_color = arr.getColor(R.styleable.BottomBarPager_text_color, DEFAULT_TEXT);
        } catch (NullPointerException e) {
            text_color = DEFAULT_TEXT;
        }

        //get the selected text color
        try {
            selected_text_color = arr.getColor(R.styleable.BottomBarPager_selected_text_color, DEFAULT_TEXT_SELECTED);
        } catch (NullPointerException e) {
            selected_text_color = DEFAULT_BACKGROUND_SELECTED;
        }
        //recycle
        arr.recycle();

    }

    /**
     * A method called to set the tabs backgrounds according to the selected one
     */
    private void setSelectedTabBackground(int index) {

        //if a tab is selected, set it's background color to selected color and the others to the default color

        if (index == 0) {

            tab1.setBackgroundColor(selected_tab_background);
            tab2.setBackgroundColor(bar_background);
            tab3.setBackgroundColor(bar_background);

        } else if (index == 1) {

            tab1.setBackgroundColor(bar_background);
            tab2.setBackgroundColor(selected_tab_background);
            tab3.setBackgroundColor(bar_background);

        } else if (index == 2) {

            tab1.setBackgroundColor(bar_background);
            tab2.setBackgroundColor(bar_background);
            tab3.setBackgroundColor(selected_tab_background);

        }

    }

    /**
     * A method called to set the tabs text colors according to the selected tab
     */
    private void setTextColor(int index) {

        //if a tab is selected, set it's text color to selected color and the others to the default color

        if (index == 0) {

            tab1_textview.setTextColor(selected_text_color);
            tab2_textview.setTextColor(text_color);
            tab3_textview.setTextColor(text_color);

        } else if (index == 1) {

            tab1_textview.setTextColor(text_color);
            tab2_textview.setTextColor(selected_text_color);
            tab3_textview.setTextColor(text_color);

        } else if (index == 2) {

            tab1_textview.setTextColor(text_color);
            tab2_textview.setTextColor(text_color);
            tab3_textview.setTextColor(selected_text_color);

        }

    }

    /**
     * A method called to animate the icons according to the selected tab
     */
    private void animateIcons(int index) {

        //if a tab is selected, animate it's vector and reset the other vectors

        if (index == 0) {

            //animate vector one
            AnimatedVectorDrawable tempDrawable1 = tab1_vector;
            tab1_imageview.setImageDrawable(tempDrawable1);
            tempDrawable1.start();

            //reset vector two
            AnimatedVectorDrawable tempDrawable2 = tab2_vector;
            tab2_imageview.setImageDrawable(tempDrawable2);
            tempDrawable2.reset();

            //reset vector three
            AnimatedVectorDrawable tempDrawable3 = tab3_vector;
            tab3_imageview.setImageDrawable(tempDrawable3);
            tempDrawable3.reset();

        } else if (index == 1) {

            //reset vector one
            AnimatedVectorDrawable tempDrawable1 = tab1_vector;
            tab1_imageview.setImageDrawable(tempDrawable1);
            tempDrawable1.reset();

            //animate vector two
            AnimatedVectorDrawable tempDrawable2 = tab2_vector;
            tab2_imageview.setImageDrawable(tempDrawable2);
            tempDrawable2.start();

            //reset vector three
            AnimatedVectorDrawable tempDrawable3 = tab3_vector;
            tab3_imageview.setImageDrawable(tempDrawable3);
            tempDrawable3.reset();

        } else if (index == 2) {

            //reset vector one
            AnimatedVectorDrawable tempDrawable1 = tab1_vector;
            tab1_imageview.setImageDrawable(tempDrawable1);
            tempDrawable1.reset();

            //reset vector two
            AnimatedVectorDrawable tempDrawable2 = tab2_vector;
            tab2_imageview.setImageDrawable(tempDrawable2);
            tempDrawable2.reset();

            //animate vector three
            AnimatedVectorDrawable tempDrawable3 = tab3_vector;
            tab3_imageview.setImageDrawable(tempDrawable3);
            tempDrawable3.start();

        }

    }

    /**
     * A method called to initialize the listeners
     * viewPagerChangeListener and the tabs clicks listener
     */
    private void initListeners() {

        //add listener to the view pager changes
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //when selected page changes

                //change tabs backgrounds according to the selected tab
                setSelectedTabBackground(position);

                //change tabs text colors according to the selected tab
                setTextColor(position);

                //animate and reset icons according to the selected tab
                animateIcons(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //add listener to the first tab clicks
        tab1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the selected tab to tab 0
                viewPager.setCurrentItem(0);

                //change tabs backgrounds according to the selected tab (tab 0)
                setSelectedTabBackground(0);

                //change tabs text colors according to the selected tab (tab 0)
                setTextColor(0);

                //animate and reset icons according to the selected tab (tab 0)
                animateIcons(0);
            }
        });

        //add listener to the second tab clicks
        tab2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the selected tab to tab 1
                viewPager.setCurrentItem(1);

                //change tabs backgrounds according to the selected tab (tab 1)
                setSelectedTabBackground(1);

                //change tabs text colors according to the selected tab (tab 1)
                setTextColor(1);

                //animate and reset icons according to the selected tab (tab 1)
                animateIcons(1);
            }
        });

        //add listener to the third tab clicks
        tab3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the selected tab to tab 2
                viewPager.setCurrentItem(2);

                //change tabs backgrounds according to the selected tab (tab 2)
                setSelectedTabBackground(2);

                //change tabs text colors according to the selected tab (tab 2)
                setTextColor(2);

                //animate and reset icons according to the selected tab (tab 2)
                animateIcons(2);
            }
        });

    }

}



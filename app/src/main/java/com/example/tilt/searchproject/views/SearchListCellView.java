package com.example.tilt.searchproject.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tilt.searchproject.R;
import com.example.tilt.searchproject.model.ListingResult;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * TODO: document your custom view class.
 */

@EViewGroup (R.layout.search_list_view_cell)
public class SearchListCellView extends RelativeLayout {

    @ViewById(R.id.amount_rate)
    TextView amountRate;

    @ViewById(R.id.rate_type)
    TextView rateType;

    @ViewById(R.id.listing_name)
    TextView listingName;

    @ViewById(R.id.listing_image)
    ImageView listingImage;

    Context context;

    protected ListingResult listingResult;

    public SearchListCellView(Context context) {
        super(context);
        this.context = context;
    }

    public SearchListCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SearchListCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void bind (ListingResult listingResult) {
        this.listingResult = listingResult;
        amountRate.setText((listingResult.getPricing()));
        listingName.setText(listingResult.getListingName());
        rateType.setText(listingResult.getPricingType());
        Picasso.with(context).load(listingResult.getListingImage()).into(listingImage);
    }


}

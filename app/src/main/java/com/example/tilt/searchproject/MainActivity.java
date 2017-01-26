package com.example.tilt.searchproject;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tilt.searchproject.SearchService.SearchService;
import com.example.tilt.searchproject.adapters.SearchResultsAdapter;
import com.example.tilt.searchproject.model.SearchResults;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@EActivity (R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById (R.id.search_edit_text)
    EditText searchText;

    @ViewById (R.id.check_in_label)
    TextView checkInLabel;

    @ViewById (R.id.check_out_label)
    TextView checkOutLabel;

    @ViewById (R.id.search_button)
    Button searchButton;

    @ViewById (R.id.results_list_view)
    ListView resultsListView;

    @InstanceState
    protected Calendar checkInDate;

    @InstanceState
    protected Calendar checkOutDate;

    @Bean
    SearchService searchService;

    protected SearchResultsAdapter searchResultsAdapter;

    @AfterViews
    protected void afterViews(){
        checkInDate = Calendar.getInstance();
        checkOutDate = Calendar.getInstance();
        checkOutDate.add(Calendar.DATE, 1);
        searchResultsAdapter = new SearchResultsAdapter(new SearchResults(), this);
        resultsListView.setAdapter(searchResultsAdapter);
        refreshSearchParams();
    }

    @Click(R.id.check_in_label)
    protected void onCheckInLabelClick (){
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                if (newDate.before(Calendar.getInstance())){
//                    showError
                } else {
                    checkInDate.set(year, monthOfYear, dayOfMonth);
                    refreshSearchParams();
                }
            }

        },checkInDate.get(Calendar.YEAR), checkInDate.get(Calendar.MONTH), checkInDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Click(R.id.check_out_label)
    protected void onCheckOutLabelClick (){
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                Calendar compareCheckIn = checkInDate;
                compareCheckIn.add(Calendar.DATE, 1);
                if (newDate.before(compareCheckIn)){
//                    showError
                } else {
                    checkOutDate.set(year, monthOfYear, dayOfMonth);
                    refreshSearchParams();
                }
            }

        },checkOutDate.get(Calendar.YEAR), checkOutDate.get(Calendar.MONTH), checkOutDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Click(R.id.search_button)
    protected void onSearchButtonPressed () {
        String location = searchText.getText().toString();
        if (location.isEmpty()) {
            return;
        }
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String checkIn  = dateFormat.format(checkInDate.getTime());
        String checkOut  = dateFormat.format(checkOutDate.getTime());
        performSearch(location, checkIn, checkOut);

    }

    @UiThread
    public void refreshSearchParams (){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        checkInLabel.setText(dateFormat.format(checkInDate.getTime()));
        checkOutLabel.setText(dateFormat.format(checkOutDate.getTime()));
    }

    @Background
    protected void performSearch(String location, String checkIn, String checkOut) {
        SearchResults searchResults = searchService.searchListingsFor(location, checkIn, checkOut);
        refreshWithResults(searchResults);
    }

    @UiThread
    protected void refreshWithResults(SearchResults searchResults) {
        searchResultsAdapter.setSearchResults(searchResults);
        searchResultsAdapter.notifyDataSetChanged();
    }

}

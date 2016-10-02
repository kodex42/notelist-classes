package com.example.a1449877.notelist.model;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 1449877 on 2016-09-29.
 */
public class SpinnerItemClickListener implements AdapterView.OnItemSelectedListener {

    private ListView noteListView;      //  Visually changes dynamically when items are added or removed from the adapter
    private ArrayAdapter<Note> adapter; //  Allows for adding and removing items to and from the ListView

    /**
     * Regular Constructor. Does not have a default constructor.
     * @param noteListView
     * @param adapter
     */
    public SpinnerItemClickListener(ListView noteListView, ArrayAdapter<Note> adapter) {
        this.noteListView = noteListView;
        this.adapter = adapter;
    }

    /**
     * Triggers when the user selects an item in the spinner
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) parent.getItemAtPosition(position); //  Stores the choice made with the spinner

        List<Note> list = new ArrayList<>();    //  Temporary list to sort

        for(int i = 0; i < adapter.getCount(); i++)
            list.add(adapter.getItem(i));   //  Populate the list with the notes in notelist

        /**
         * Each case in this switch implements the Collections.sort() method.
         * The method compares two notes and returns an integer.
         * If the integer is negative, the first note comes before the second in the sort.
         * If the integer is positive, the first note comes after the second in the sort.
         */
        switch(value) {
            case "Creation Date":
                Collections.sort(list, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getCreated().compareTo(o2.getCreated());
                    }
                });
                break;
            case "Category":
                Collections.sort(list, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getCategory() - o2.getCategory();
                    }
                });
                break;
            case "Reminder":
                Collections.sort(list, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        if (o1.isHasReminder() && o2.isHasReminder())   //  Do both notes have reminders set?
                            return o1.getReminder().compareTo(o2.getReminder());
                        else
                            if (o1.isHasReminder()) //  Does only the first note have a reminder set?
                                return -1;   //  Notes without reminders come after those with reminders
                            else
                                if (o2.isHasReminder()) //  Does only the second note have a reminder set?
                                    return 1;  //  Notes without reminders come after those with reminders
                                else    //  Neither notes have reminders set, order by date created.
                                    return o1.getCreated().compareTo(o2.getCreated());
                    }
                });
                break;
            case "Title":
                Collections.sort(list, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                });
                break;
        }

        adapter.clear();        //  Empty the unsorted data
        adapter.addAll(list);   //  Add the sorted data

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }
}

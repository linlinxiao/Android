package com.linlin.jsonapplication;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Person;

/**
 * Created by linlin on 8/17/16.
 */
public class ListViewAdapter extends BaseAdapter {

    private ArrayList<Person> persons;
    private LayoutInflater inflater;

    public ListViewAdapter(ArrayList<Person> persons, LayoutInflater inflater) {
        this.persons = persons;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
        }

        Holder holder = new Holder(convertView);
        holder.setPerson(persons.get(position));

        return convertView;
    }
}

class Holder {
    private Person person;

    private ImageView avatarImgView;
    private TextView nameTV;
    private TextView ageTV;
    private TextView school1TV;
    private TextView school2TV;
    private Handler handler = new Handler();

    private Holder(){}
    public Holder(View view) {
        avatarImgView = (ImageView) view.findViewById(R.id.imageView);
        nameTV = (TextView) view.findViewById(R.id.nameTV);
        ageTV = (TextView) view.findViewById(R.id.ageTV);
        school1TV = (TextView) view.findViewById(R.id.school1TV);
        school2TV = (TextView) view.findViewById(R.id.school2TV);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;

        new HttpImageThread(person.getAvatarUrl(), handler, avatarImgView).start();
        nameTV.setText(person.getName());
        ageTV.setText(""+person.getAge());
        school1TV.setText(person.getSchools().get(0).getName());
        school2TV.setText(person.getSchools().get(1).getName());
    }
}

package com.example.table.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AllTableSummary2Dto {
    public class Series
    {
        private ArrayList<Integer> data = new ArrayList<Integer>();
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Integer> getData() {
            return data;
        }

        public void addNumToData(Integer index)
        {
            data.set(index, data.get(index)+1);
        }
    }

    public class Options
    {
        private class XAxis
        {
            public ArrayList<String> getCategories() {
                return categories;
            }

            private ArrayList<String> categories = new ArrayList<String>();
        }

        public ArrayList<String> getCategories() {
            return this.xaxis.getCategories();
        }

        private XAxis xaxis=new XAxis();
    }

    private ArrayList<Series> series = new ArrayList<Series>();
    private Options options=new Options();

    public AllTableSummary2Dto()
    {
        Series s0=new Series();
        s0.setName("空闲");
        Series s1=new Series();
        s1.setName("占用");
        series.add(s0);
        series.add(s1);
    }
}

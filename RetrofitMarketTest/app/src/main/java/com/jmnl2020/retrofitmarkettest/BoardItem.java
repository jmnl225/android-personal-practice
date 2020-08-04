package com.jmnl2020.retrofitmarkettest;

public class BoardItem {

    int no;
    String name;
    String title;
    String msg;
    String price;
    String file;
    int favor;
    String date;

    public BoardItem() {
    }

    public BoardItem(int no, String name, String title, String msg, String price, String file, int favor, String date) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.msg = msg;
        this.price = price;
        this.file = file;
        this.favor = favor;
        this.date = date;
    }
}

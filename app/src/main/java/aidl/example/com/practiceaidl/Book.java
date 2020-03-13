package aidl.example.com.practiceaidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String name ;
    private String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static Creator<Book> getCREATOR() {
        return CREATOR;
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
    }

    //反序列化
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
    }
}

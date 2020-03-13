package aidl.example.com.practiceaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainService extends Service {

    private List<Book> bookList;

    public IBookInterface.Stub mBinder = new IBookInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void addBook(Book book) throws RemoteException{
            if(bookList != null){
                Log.i("MDL","当前进程:" + android.os.Process.myPid());
                bookList.add(book);
            }
        }

        @Override
        public void printlnBookList() throws RemoteException {
            if(bookList != null){
                for (Book book : bookList){
                    Log.i("MDL","name:" + book.getName() + " author:" + book.getAuthor());
                }
            }
        }


    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MDL","onCreate");
        if(bookList == null){
            bookList = new ArrayList<>();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}

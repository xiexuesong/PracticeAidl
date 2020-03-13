// IBookInterface.aidl
package aidl.example.com.practiceaidl;

// Declare any non-default types here with import statements
import aidl.example.com.practiceaidl.Book;

interface IBookInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void addBook(in Book book);

    void printlnBookList();
}

package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        int schoolBookCount = count();

        if (book != null) {
            SchoolBook[] tempSchoolBooks = new SchoolBook[schoolBookCount + 1];

            System.arraycopy(schoolBooks, 0, tempSchoolBooks, 0, schoolBookCount);
            tempSchoolBooks[schoolBookCount] = book;
            schoolBooks = tempSchoolBooks;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = new SchoolBook[]{};
        int booksCount = 0;

        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                SchoolBook[] tempBooks = new SchoolBook[booksCount + 1];

                System.arraycopy(books, 0, tempBooks, 0, booksCount);
                tempBooks[booksCount] = book;
                books = tempBooks;
                booksCount++;
            }
        }

        return books;
    }

    @Override
    public boolean removeByName(String name) {
        int countBeforeRemove = schoolBooks.length;
        int currentCount = countBeforeRemove;
        int i = 0;

        while (i < currentCount) {
            if (schoolBooks[i].getName().equals(name)) {
                SchoolBook[] tempBooks = new SchoolBook[currentCount - 1];

                System.arraycopy(schoolBooks, 0, tempBooks, 0, i);
                System.arraycopy(schoolBooks, i + 1, tempBooks, i, currentCount - 1 - i);

                schoolBooks = tempBooks;
                currentCount--;
            } else {
                i++;
            }
        }

        return !(currentCount == countBeforeRemove);
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}

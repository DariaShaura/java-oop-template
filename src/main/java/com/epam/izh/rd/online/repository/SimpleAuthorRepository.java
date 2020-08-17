package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author){
        int currentCount = count();

        if(findByFullName(author.getName(), author.getLastName()) == null){
            Author[] tempAuthors = new Author[currentCount + 1];

            System.arraycopy(authors, 0, tempAuthors, 0, currentCount);
            tempAuthors[currentCount] = author;
            authors = tempAuthors;

            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname){
        for(Author author: authors){
            if(author.getName().equals(name) && author.getLastName().equals(lastname)){
                return author;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Author author){
        int authorsCount = authors.length;

        for(int i=0; i < authorsCount; i++){
            if(authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())){
                Author[] tempAuthors = new Author[authorsCount - 1];

                System.arraycopy(authors, 0, tempAuthors, 0, i);
                System.arraycopy(authors, i + 1, tempAuthors, i, authorsCount - 1 - i);

                authors = tempAuthors;

                return true;
            }
        }

        return false;
    }

    @Override
    public int count(){
        return authors.length;
    }
}

package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book){
        Author author = authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName());
        if(author != null){
            schoolBookBookRepository.save(book);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Метод должен находить книгу по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public SchoolBook[] findByName(String name){
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name){
        return findByName(name).length;
    }

    @Override
    public boolean removeByName(String name){
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count(){
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name){
        SchoolBook[] books = findByName(name);
        if(books.length > 0){
            return authorService.findByFullName(books[0].getAuthorName(), books[0].getAuthorLastName());
        }
        else{
            return null;
        }
    }
}

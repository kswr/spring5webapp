package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initEvent();
    }

    private void initEvent() {

        Publisher harper = new Publisher("Harper Collins", "NY, 1234");
        Publisher worx = new Publisher("Worx", "LA, 1234");
        Publisher oReilly = new Publisher("O'Reilly", "NY, 1235");

        Author eric = new Author("Eric", "Evans");
        Author rod = new Author("Rod", "Johnson");
        Author test = new Author("Test", "Test");

        Book ddd = new Book("Domain driven design", "1234", harper);
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        Book hfJava = new Book("Head first Java", "1324", oReilly);

        test.getBooks().add(hfJava);
        hfJava.getAuthors().add(test);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(harper);
        publisherRepository.save(worx);
        publisherRepository.save(oReilly);
        authorRepository.save(eric);
        authorRepository.save(rod);
        authorRepository.save(test);
        bookRepository.save(noEJB);
        bookRepository.save(ddd);
        bookRepository.save(hfJava);

    }
}

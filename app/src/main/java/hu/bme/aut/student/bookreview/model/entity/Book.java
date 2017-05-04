package hu.bme.aut.student.bookreview.model.entity;

import android.content.Context;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

import java.util.UUID;

/**
 * Represents a book entity.
 * Created by Daniel Zolnai on 2017-04-07.
 */
@Table
@Parcel
public class Book {
    @Unique
    protected String _id;
    protected String _title;
    protected String _author;
    protected Integer _publishYear;
    protected String _imageUrl;

    public Book() {
        // Used by ORM.
    }

    private Book(Builder builder) {
        _id = builder._id;
        _title = builder._title;
        _author = builder._author;
        _publishYear = builder._publishYear;
        _imageUrl = builder._imageUrl;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        _author = author;
    }

    public Integer getPublishYear() {
        return _publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        _publishYear = publishYear;
    }

    public String getImageUrl() {
        return _imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        _imageUrl = imageUrl;
    }

    public static class Builder {
        private String _id;
        private String _title;
        private String _author;
        private Integer _publishYear;
        private String _imageUrl;

        public Builder() {
            _id = UUID.randomUUID().toString();
        }

        public Builder setTitle(String title) {
            _title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            _author = author;
            return this;
        }

        public Builder setPublishYear(Integer publishYear) {
            _publishYear = publishYear;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            _imageUrl = imageUrl;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}

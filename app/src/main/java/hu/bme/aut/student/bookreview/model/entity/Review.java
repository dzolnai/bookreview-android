package hu.bme.aut.student.bookreview.model.entity;

import java.util.Date;

/**
 * The entity of a review, which is made by a user, and for a given book.
 *
 * Created by Daniel Zolnai on 2017-04-16.
 */
public class Review {
    private String _username;
    private String _bookId;
    private Date _publishedAt;
    private Integer _rating;
    private String _comment;

    public Review(String username, String bookId, Integer rating, String comment) {
        _username = username;
        _bookId = bookId;
        _publishedAt = new Date();
        _rating = rating;
        _comment = comment;
    }

    public String getUsername() {
        return _username;
    }

    public String getBookId() {
        return _bookId;
    }

    public Date getPublishedAt() {
        return _publishedAt;
    }

    public Integer getRating() {
        return _rating;
    }

    public String getComment() {
        return _comment;
    }
}

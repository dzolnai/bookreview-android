package hu.bme.aut.student.bookreview.model.entity;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.util.Date;

/**
 * The entity of a review, which is made by a user, and for a given book.
 * <p>
 * Created by Daniel Zolnai on 2017-04-16.
 */
@Table
public class Review {
    protected String _username;
    protected String _bookId;
    protected Date _publishedAt;
    protected Integer _rating;
    protected String _comment;

    public Review() {
        // Used by oRM.
    }

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

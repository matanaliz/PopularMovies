package mtn.popularmovies;

/**
 * Created by matan on 08.06.2016.
 */
public class Movie {
    private final String LOG_TAG = Movie.class.getSimpleName();

    private int id;
    private String title;
    private String posterUrl;
    private double voteRating;
    private String overview;
    private String releaseDate;
    private String originalTitle;

    public int getId() {
        return id;
    }

    public Movie setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public Movie setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    public double getVoteRating() {
        return voteRating;
    }

    public Movie setVoteRating(double voteRating) {
        this.voteRating = voteRating;
        return this;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Movie setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public Movie setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Movie setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }



}

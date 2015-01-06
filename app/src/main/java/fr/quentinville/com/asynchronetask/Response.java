package fr.quentinville.com.asynchronetask;

/**
 * Created by quentin on 12/12/2014.
 */
public class Response {

    public Photos photos;
    public String stat;

    public Response(Photos photos, String stat) {
        super();
        this.photos = photos;
        this.stat = stat;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Response [photos=" + photos + ", stat=" + stat + "]";
    }

}


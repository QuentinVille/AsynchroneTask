package fr.quentinville.com.asynchronetask;

/**
 * Created by quentin on 12/12/2014.
 */
public class Response {
        private Photos photos;
        private String stat;

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Photos getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }

    @Override
        public String toString() {
            return "Photos :" + photos + " - " + "Stat :" + stat;
        }
}


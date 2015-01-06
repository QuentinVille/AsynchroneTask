package fr.quentinville.com.asynchronetask;

import java.util.Collection;

/**
 * Created by quentin on 12/12/2014.
 */
public class Photos {
        private int page;
        private int pages;
        private int perpage;
        private String total;
        private Collection<PhotoObject> collectionPhotoObjects;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Collection<PhotoObject> getCollectionPhotoObjects() {
        return collectionPhotoObjects;
    }

    public void setCollectionPhotoObjects(Collection<PhotoObject> collectionPhotoObjects) {
        this.collectionPhotoObjects = collectionPhotoObjects;
    }
}

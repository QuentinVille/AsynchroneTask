package fr.quentinville.com.asynchronetask;

import java.util.List;

/**
 * Created by quentin on 12/12/2014.
 */
public class Photos {
    public Integer page;
    public Long pages;
    public Integer perpage;
    public String total;
    // Collection de Photo
    public List<Photo> photo;


    public Photos(Integer page, Long pages, Integer perpage, String total,
                  List<Photo> photo) {
        super();
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
        this.photo = photo;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Photos [page=" + page + ", pages=" + pages + ", perpage="
                + perpage + ", total=" + total + ", photo=" + photo + "]";
    }




}


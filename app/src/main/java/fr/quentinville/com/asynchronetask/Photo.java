package fr.quentinville.com.asynchronetask;

import com.google.gson.annotations.SerializedName;

/**
 * Created by quentin on 12/12/2014.
 */
public class Photo {

    public String id;
    public String owner;
    public String secret;
    public String server;
    public String title;

    public Integer farm;
    @SerializedName("ispublic")
    public Integer isPublic;
    @SerializedName("isfriend")
    public Integer isFriend;
    @SerializedName("isfamily")
    public Integer isFamily;


    public Photo(String id, String owner, String secret, String server,
            String title, Integer farm, Integer isPublic, Integer isFriend,
            Integer isFamily) {
            super();
            this.id = id;
            this.owner = owner;
            this.secret = secret;
            this.server = server;
            this.title = title;
            this.farm = farm;
            this.isPublic = isPublic;
            this.isFriend = isFriend;
            this.isFamily = isFamily;
            }

    public String getId() {
            return id;
            }

    public void setId(String id) {
            this.id = id;
            }

    public String getOwner() {
            return owner;
            }

    public void setOwner(String owner) {
            this.owner = owner;
            }

    public String getSecret() {
            return secret;
            }

    public void setSecret(String secret) {
            this.secret = secret;
            }

    public String getServer() {
            return server;
            }

    public void setServer(String server) {
            this.server = server;
            }

    public String getTitle() {
            return title;
            }

    public void setTitle(String title) {
            this.title = title;
            }

    public Integer getFarm() {
            return farm;
            }

    public void setFarm(Integer farm) {
            this.farm = farm;
            }

    public Integer getIsPublic() {
            return isPublic;
            }

    public void setIsPublic(Integer isPublic) {
            this.isPublic = isPublic;
            }

    public Integer getIsFriend() {
            return isFriend;
            }

    public void setIsFriend(Integer isFriend) {
            this.isFriend = isFriend;
            }

    public Integer getIsFamily() {
            return isFamily;
            }

    public void setIsFamily(Integer isFamily) {
            this.isFamily = isFamily;
            }

    public String imageUrl()
            {
            String res = "https://farm"+this.getFarm()+".staticflickr.com/"+this.getServer()+"/"+this.getId()+"_"+this.getSecret()+".jpg";
            return res;
            }

    public String imageUrlResolution(String reso)
    {
        String res = "https://farm"+this.getFarm()+".staticflickr.com/"+this.getServer()+"/"+this.getId()+"_"+this.getSecret()+"_"+reso+".jpg";
        return res;
    }


    @Override
    public String toString() {
            return "Photo [id=" + id + ", owner=" + owner + ", secret=" + secret
            + ", server=" + server + ", title=" + title + ", farm=" + farm
            + ", isPublic=" + isPublic + ", isFriend=" + isFriend
            + ", isFamily=" + isFamily + "]";
            }

}
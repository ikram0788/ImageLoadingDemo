package imagecaching.ikram.com.imageloadingdemo.datamodels;

public class Owner {
    private String id;
    private String nsid;
    private String ispro;
    private String can_buy_pro;
    private String iconserver;
    private String iconfarm;
    private ContentValues username;
    private ContentValues realname;
    private ContentValues location;
    private ContentValues description;
    private ContentValues photosurl;
    private ContentValues profileurl;
    private ContentValues mobileurl;

    public String getIconfarm() {
        return iconfarm;
    }

    public void setIconfarm(String iconfarm) {
        this.iconfarm = iconfarm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getIspro() {
        return ispro;
    }

    public void setIspro(String ispro) {
        this.ispro = ispro;
    }

    public String getCan_buy_pro() {
        return can_buy_pro;
    }

    public void setCan_buy_pro(String can_buy_pro) {
        this.can_buy_pro = can_buy_pro;
    }

    public String getIconserver() {
        return iconserver;
    }

    public void setIconserver(String iconserver) {
        this.iconserver = iconserver;
    }

    public ContentValues getUsername() {
        return username;
    }

    public void setUsername(ContentValues username) {
        this.username = username;
    }

    public ContentValues getRealname() {
        return realname;
    }

    public void setRealname(ContentValues realname) {
        this.realname = realname;
    }

    public ContentValues getLocation() {
        return location;
    }

    public void setLocation(ContentValues location) {
        this.location = location;
    }

    public ContentValues getDescription() {
        return description;
    }

    public void setDescription(ContentValues description) {
        this.description = description;
    }

    public ContentValues getPhotosurl() {
        return photosurl;
    }

    public void setPhotosurl(ContentValues photosurl) {
        this.photosurl = photosurl;
    }

    public ContentValues getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(ContentValues profileurl) {
        this.profileurl = profileurl;
    }

    public ContentValues getMobileurl() {
        return mobileurl;
    }

    public void setMobileurl(ContentValues mobileurl) {
        this.mobileurl = mobileurl;
    }
}

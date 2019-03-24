package imagecaching.ikram.com.imageloadingdemo.network.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import imagecaching.ikram.com.imageloadingdemo.datamodels.Photo;

public class PhotoContainer implements Parcelable {
    private int page;
    private int pages;
    private int perpage;
    private int total;
    private List<Photo> photo;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.pages);
        dest.writeInt(this.perpage);
        dest.writeInt(this.total);
        dest.writeTypedList(this.photo);
    }

    public PhotoContainer() {
    }

    protected PhotoContainer(Parcel in) {
        this.page = in.readInt();
        this.pages = in.readInt();
        this.perpage = in.readInt();
        this.total = in.readInt();
        this.photo = in.createTypedArrayList(Photo.CREATOR);
    }

    public static final Parcelable.Creator<PhotoContainer> CREATOR = new Parcelable.Creator<PhotoContainer>() {
        @Override
        public PhotoContainer createFromParcel(Parcel source) {
            return new PhotoContainer(source);
        }

        @Override
        public PhotoContainer[] newArray(int size) {
            return new PhotoContainer[size];
        }
    };
}

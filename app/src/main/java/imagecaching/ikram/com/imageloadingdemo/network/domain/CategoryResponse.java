package imagecaching.ikram.com.imageloadingdemo.network.domain;

public class CategoryResponse extends BaseResponse {
    private PhotoContainer photos;

    public PhotoContainer getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoContainer photos) {
        this.photos = photos;
    }
}

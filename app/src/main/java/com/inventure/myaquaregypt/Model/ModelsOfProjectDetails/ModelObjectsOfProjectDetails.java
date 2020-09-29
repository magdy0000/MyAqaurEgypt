package com.inventure.myaquaregypt.Model.ModelsOfProjectDetails;

import java.util.List;

public class ModelObjectsOfProjectDetails {


    /**
     * id : 1
     * type : department
     * category : residential
     * developer : HDG
     * project : Azad
     * favorite : false
     * min_rooms : 3
     * max_rooms : 3
     * min_bathsrooms : 1
     * max_bathsrooms : 1
     * min_price : 5000
     * max_price : 5000
     * min_area : 80
     * max_area : 80
     * accommodation : null
     * location : New Cairo
     * viewer_360 : http://aquar.me/myaquar_eg/uploads/products/360_2.jpg
     * description : prime location with an attractive payment plan, 10% Downpayment - 10% after 3 Months - 10% on Delivery &  Equal installments over 6 Years
     * slider_images : [{"image_id":2,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_5.jpg"},{"image_id":3,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_7.jpg"},{"image_id":4,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_9.jpg"},{"image_id":5,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_11.jpg"},{"image_id":6,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_31.jpg"},{"image_id":7,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/azad_pqroject.jpg"}]
     * dimension_images : [{"image_id":2,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_5.jpg"},{"image_id":3,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_7.jpg"},{"image_id":4,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_9.jpg"},{"image_id":5,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_11.jpg"},{"image_id":6,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_31.jpg"},{"image_id":7,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/azad_pqroject.jpg"}]
     */

    private int id;
    private String type;
    private String category;
    private String developer;
    private String project;
    private String favorite;
    private String min_rooms;
    private String max_rooms;
    private String min_bathsrooms;
    private String max_bathsrooms;
    private int min_price;
    private int max_price;
    private String min_area;
    private String max_area;
    private Object accommodation;
    private String location;
    private String viewer_360;
    private String description;
    private List<SliderImagesBean> slider_images;
    private List<String> structure_images;
    private String price_label;

    public String getPrice_label() {
        return price_label;
    }

    public void setPrice_label(String price_label) {
        this.price_label = price_label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getMin_rooms() {
        return min_rooms;
    }

    public void setMin_rooms(String min_rooms) {
        this.min_rooms = min_rooms;
    }

    public String getMax_rooms() {
        return max_rooms;
    }

    public void setMax_rooms(String max_rooms) {
        this.max_rooms = max_rooms;
    }

    public String getMin_bathsrooms() {
        return min_bathsrooms;
    }

    public void setMin_bathsrooms(String min_bathsrooms) {
        this.min_bathsrooms = min_bathsrooms;
    }

    public String getMax_bathsrooms() {
        return max_bathsrooms;
    }

    public void setMax_bathsrooms(String max_bathsrooms) {
        this.max_bathsrooms = max_bathsrooms;
    }

    public int getMin_price() {
        return min_price;
    }

    public void setMin_price(int min_price) {
        this.min_price = min_price;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }

    public String getMin_area() {
        return min_area;
    }

    public void setMin_area(String min_area) {
        this.min_area = min_area;
    }

    public String getMax_area() {
        return max_area;
    }

    public void setMax_area(String max_area) {
        this.max_area = max_area;
    }

    public Object getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Object accommodation) {
        this.accommodation = accommodation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getViewer_360() {
        return viewer_360;
    }

    public void setViewer_360(String viewer_360) {
        this.viewer_360 = viewer_360;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SliderImagesBean> getSlider_images() {
        return slider_images;
    }

    public void setSlider_images(List<SliderImagesBean> slider_images) {
        this.slider_images = slider_images;
    }

    public List<String> getStructure_images() {
        return structure_images;
    }

    public void setStructure_images(List<String> structure_images) {
        this.structure_images = structure_images;
    }

    public static class SliderImagesBean {
        /**
         * image_id : 2
         * image_url : http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_5.jpg
         */

        private int image_id;
        private String image_url;

        public int getImage_id() {
            return image_id;
        }

        public void setImage_id(int image_id) {
            this.image_id = image_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}

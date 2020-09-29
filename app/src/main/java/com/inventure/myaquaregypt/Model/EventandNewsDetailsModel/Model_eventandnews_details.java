package com.inventure.myaquaregypt.Model.EventandNewsDetailsModel;

import java.util.List;

public class Model_eventandnews_details {

    /**
     * title : Azad
     * category : residential
     * project : Azad
     * description : TAMEER is launching AZAD community in New Cairo. This 530-apartment development is located within a vibrant and elegant landscaped environment, in the most central location in New Cairo, across the street from the American University campus. AZAD is planned to be delivered in 2019.
     * slider_images : [{"image_id":2,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_5.jpg"},{"image_id":3,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_7.jpg"},{"image_id":4,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_9.jpg"},{"image_id":5,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_11.jpg"},{"image_id":6,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_31.jpg"},{"image_id":7,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/azad_pqroject.jpg"}]
     */

    private String title;
    private String category;
    private String project;
    private String description;
    private List<SliderImagesBean> slider_images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

package sample.ramya.com.exampleapp.pojo;

/**
 * Created by hb on 26/6/17.
 */

public class ReclylerItemModel {

    String type;
    String defect_label;

    public ReclylerItemModel(String type, String defect_label) {
        this.type = type;
        this.defect_label = defect_label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefect_label() {
        return defect_label;
    }

    public void setDefect_label(String defect_label) {
        this.defect_label = defect_label;
    }

}

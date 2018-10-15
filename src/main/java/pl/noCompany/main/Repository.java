package pl.noCompany.main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Repository {

    private StringProperty name;
    private StringProperty time;


    Repository(String name, String time){
        this.name = new SimpleStringProperty(name);
        this.time = new SimpleStringProperty(time);
    }

    String getName() {
        return name.get();
    }

    String getTime() {
        return time.get();
    }
}
